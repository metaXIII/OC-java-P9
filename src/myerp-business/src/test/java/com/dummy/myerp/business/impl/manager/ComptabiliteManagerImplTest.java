package com.dummy.myerp.business.impl.manager;

import com.dummy.myerp.business.contrat.BusinessProxy;
import com.dummy.myerp.business.impl.AbstractBusinessManager;
import com.dummy.myerp.business.impl.TransactionManager;
import com.dummy.myerp.consumer.dao.contrat.DaoProxy;
import com.dummy.myerp.consumer.dao.impl.db.dao.ComptabiliteDaoImpl;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;
import com.dummy.myerp.technical.exception.FunctionalException;
import com.dummy.myerp.technical.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.ComparisonFailure;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@Slf4j
@ExtendWith(MockitoExtension.class)
public class ComptabiliteManagerImplTest {


    @Mock
    private static BusinessProxy           businessProxy;
    @Mock
    private static TransactionManager      transactionManager;
    @Mock
    private static DaoProxy                daoProxy;
    @InjectMocks
    private        ComptabiliteManagerImpl comptabiliteManager;
    @Mock
    private        EcritureComptable       ecritureComptable;
    @Mock
    private        JournalComptable        journalComptable;
    @Mock
    private        ComptabiliteDaoImpl     comptabiliteDao;

    @BeforeEach
    public void initTest() {
        log.info("Début du test");
        ecritureComptable = new EcritureComptable();
        AbstractBusinessManager.configure(businessProxy,
                                          daoProxy,
                                          transactionManager);
    }

    @AfterEach
    public void endTest() {
        ecritureComptable = null;
    }

    @Test
    void shouldAddReferenceWhenJournalCodeIsProperlySet() throws Exception {
        journalComptable = new JournalComptable();
        journalComptable.setCode("AB");
        ecritureComptable.setReference(null);
        ecritureComptable.setJournal(journalComptable);
        ecritureComptable.setDate(new Date());
        comptabiliteManager.addReference(ecritureComptable);
        Assert.assertEquals("AB-2020/00001", ecritureComptable.getReference());
    }

    @Test
    void shouldThrowfunctionalExceptionErrorWhenBadCodeIsProvided() throws ComparisonFailure, NotFoundException {
        journalComptable = new JournalComptable();
        journalComptable.setCode("AB");
        ecritureComptable.setReference(null);
        ecritureComptable.setJournal(journalComptable);
        ecritureComptable.setDate(new Date());
        comptabiliteManager.addReference(ecritureComptable);
        assertNotEquals("AC-2020/00001", ecritureComptable.getReference());
    }

    @Test
    public void shouldThrowFunctionalExceptionWhenBadYearIsSpecified() throws NotFoundException {
        Date     date     = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.YEAR, -1);
        date = calendar.getTime();
        ecritureComptable.setDate(date);
        ecritureComptable.setReference(null);
        comptabiliteManager.addReference(ecritureComptable);
        assertThrows(FunctionalException.class,
                     () -> comptabiliteManager.checkEcritureComptableUnit(ecritureComptable));
    }

    @Test
    public void shouldAddOneReferencePlusOneWhenAResultIsProvidedOnAddReference() throws NotFoundException {
        when(daoProxy.getComptabiliteDao()).thenReturn(comptabiliteDao);
        when(comptabiliteDao.getListEcritureComptable()).thenReturn(mockListEcritureComptable());
        journalComptable = new JournalComptable();
        journalComptable.setCode("AB");
        ecritureComptable.setReference(null);
        ecritureComptable.setJournal(journalComptable);
        ecritureComptable.setDate(new Date());
        comptabiliteManager.addReference(ecritureComptable);
        assertEquals("AB-2020/00002", ecritureComptable.getReference());
    }


    @Test
    public void checkEcritureComptableUnit() throws Exception {
        ecritureComptable.setJournal(new JournalComptable("BQ", "Achat"));
        ecritureComptable.setDate(new Date());
        ecritureComptable.setReference("BQ-2020/00001");
        ecritureComptable.setLibelle("Libelle");
        ecritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                null, new BigDecimal(123),
                                                                                null));
        ecritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                                                                                null, null,
                                                                                new BigDecimal(123)));
        comptabiliteManager.checkEcritureComptableUnit(ecritureComptable);
    }

    @Test
    public void shouldThrowExceptionWhenReferenceDoesNotContainsCodeJournal() throws Exception {
        ecritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        ecritureComptable.setDate(new Date());
        ecritureComptable.setReference("BQ-2020/00001");
        ecritureComptable.setLibelle("Libelle");
        ecritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                null, new BigDecimal(123),
                                                                                null));
        ecritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                                                                                null, null,
                                                                                new BigDecimal(123)));
        assertThrows(FunctionalException.class, () ->comptabiliteManager.checkEcritureComptableUnit(ecritureComptable));
    }


    @Test
    public void shouldThrowExceptionWhenBadYearIsProvidedInsideReference() throws Exception {
        ecritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        ecritureComptable.setDate(new Date());
        ecritureComptable.setReference("BQ-2019/00001");
        ecritureComptable.setLibelle("Libelle");
        ecritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                null, new BigDecimal(123),
                                                                                null));
        ecritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                                                                                null, null,
                                                                                new BigDecimal(123)));
        assertThrows(Exception.class, () ->comptabiliteManager.checkEcritureComptableUnit(ecritureComptable));
    }

    @Test
    public void shouldThrowFunctionalExceptionWhenEmptyJournalIsProvided() throws Exception {
        ecritureComptable.setJournal(new JournalComptable());
        ecritureComptable.setDate(new Date());
        ecritureComptable.setReference("BQ-2019/00001");
        ecritureComptable.setLibelle("Libelle");
        ecritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                null, new BigDecimal(123),
                                                                                null));
        ecritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                                                                                null, null,
                                                                                new BigDecimal(123)));
        assertThrows(FunctionalException.class, () ->comptabiliteManager.checkEcritureComptableUnit(ecritureComptable));
    }


    @Test
    public void checkEcritureComptableUnitViolation() {
        assertThrows(FunctionalException.class,
                            () -> comptabiliteManager.checkEcritureComptableUnit(ecritureComptable));
    }

    @Test
    public void checkEcritureComptableUnitRG2() {
        ecritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        ecritureComptable.setDate(new Date());
        ecritureComptable.setLibelle("Libelle");
        //Débit
        ecritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                null, new BigDecimal(123),
                                                                                null));
        //Crédit
        ecritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                                                                                null, null,
                                                                                new BigDecimal(1234)));
        Assert.assertThrows(FunctionalException.class,
                            () -> comptabiliteManager.checkEcritureComptableUnit(ecritureComptable));
    }

    @Test
    public void checkEcritureComptableUnitRG3() {
        ecritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        ecritureComptable.setDate(new Date());
        ecritureComptable.setLibelle("Libelle");
        ecritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                null, new BigDecimal(123),
                                                                                null));
        ecritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                null, new BigDecimal(123),
                                                                                null));
        Assert.assertThrows(FunctionalException.class,
                            () -> comptabiliteManager.checkEcritureComptableUnit(ecritureComptable));
    }

    @Test
    public void shouldThrowsErrorWhenOnlyOneEcritureComptableIsGiven() {
        ecritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        ecritureComptable.setDate(new Date());
        ecritureComptable.setLibelle("Libelle");
        ecritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                null, new BigDecimal(123),
                                                                                null));
        Assert.assertThrows(FunctionalException.class,
                            () -> comptabiliteManager.checkEcritureComptableUnit(ecritureComptable));
    }

    @Test
    public void shouldThrowsErrorWhenOnlyOneEcritureComptableWithCreditIsGiven() {
        ecritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        ecritureComptable.setDate(new Date());
        ecritureComptable.setLibelle("Libelle");
        ecritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                null, null,
                                                                                new BigDecimal(123)));
        Assert.assertThrows(FunctionalException.class,
                            () -> comptabiliteManager.checkEcritureComptableUnit(ecritureComptable));
    }

    @Test
    public void shouldThrowsErrorWhenOnlyOneEcritureComptableWithDebitIsGiven() {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                 null, new BigDecimal(123),
                                                                                 null));
        Assert.assertThrows(FunctionalException.class,
                            () -> comptabiliteManager.checkEcritureComptableUnit(vEcritureComptable));
    }

    @Test
    public void checkEcritureComptable() throws NotFoundException {
        ecritureComptable.setDate(new Date());
        ecritureComptable.setJournal(new JournalComptable("AZ", "aze"));
        ecritureComptable.setReference("AZ-2020/00001");
        ecritureComptable.setLibelle("aze");
        ecritureComptable.setId(1);
        ecritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                null, new BigDecimal(123),
                                                                                null));
        ecritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                                                                                null, null,
                                                                                new BigDecimal(123)));
        when(daoProxy.getComptabiliteDao()).thenReturn(comptabiliteDao);
        when(comptabiliteDao.getEcritureComptableByRef(any())).thenReturn(this.mockEcritureComptable());
        assertDoesNotThrow(() -> comptabiliteManager.checkEcritureComptable(ecritureComptable));
    }

    @Test
    public void shouldThrowFunctionnalExceptionWhenEcritureComptableFoundDoesNotContainsId() throws NotFoundException {
        ecritureComptable.setDate(new Date());
        ecritureComptable.setJournal(new JournalComptable("AZ", "aze"));
        ecritureComptable.setReference("AZ-2020/00001");
        ecritureComptable.setLibelle("aze");
        ecritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                null, new BigDecimal(123),
                                                                                null));
        ecritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                                                                                null, null,
                                                                                new BigDecimal(123)));
        when(daoProxy.getComptabiliteDao()).thenReturn(comptabiliteDao);
        when(comptabiliteDao.getEcritureComptableByRef(any())).thenReturn(ecritureComptable);
        assertThrows(FunctionalException.class, () -> comptabiliteManager.checkEcritureComptable(ecritureComptable));
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenListComptableIsAskAndDAOProxIsNotMocked() {
        assertThrows(NullPointerException.class, () -> comptabiliteManager.getListCompteComptable());
    }

    @Test
    public void shouldThrowNullPointerExceptionWhenJournalComptableIsAskAndDAOProxIsNotMocked() {
        assertThrows(NullPointerException.class, () -> comptabiliteManager.getListJournalComptable());
    }

    @Test
    public void shouldReturnListWhenListComptableIsAsked() {
        when(daoProxy.getComptabiliteDao()).thenReturn(comptabiliteDao);
        when(comptabiliteDao.getListCompteComptable()).thenReturn(new ArrayList<>());
        assertDoesNotThrow(() -> comptabiliteManager.getListCompteComptable());
    }

    @Test
    public void shouldReturnListWhenJournalComptableIsAsked() {
        when(daoProxy.getComptabiliteDao()).thenReturn(comptabiliteDao);
        when(comptabiliteDao.getListJournalComptable()).thenReturn(new ArrayList<>());
        assertDoesNotThrow(() -> comptabiliteManager.getListJournalComptable());
    }




    /**
     * @return Ecriture comptable
     * methode pour mocker une nouvelle ecriture comptable qui sera a comparé
     */
    private EcritureComptable mockEcritureComptable() {
        EcritureComptable ecritureComptable = new EcritureComptable();
        ecritureComptable.setId(1);
        ecritureComptable.setReference("BQ-2016/00001");
        return ecritureComptable;
    }

    private List<EcritureComptable> mockListEcritureComptable() {
        List<EcritureComptable> list = new ArrayList<>();
        list.add(mockEcritureComptable());
        return list;
    }
}

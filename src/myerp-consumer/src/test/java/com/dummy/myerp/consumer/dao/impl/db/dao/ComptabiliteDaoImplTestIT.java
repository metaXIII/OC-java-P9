package com.dummy.myerp.consumer.dao.impl.db.dao;

import com.dummy.myerp.consumer.ConsumerHelper;
import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;
import com.dummy.myerp.technical.exception.NotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@Transactional
@Rollback
@ContextConfiguration(locations = "classpath:com/dummy/myerp/consumer/applicationContext.xml")
public class ComptabiliteDaoImplTestIT {
    ComptabiliteDao comptabiliteDao;

    @BeforeEach
    public void init() {
        comptabiliteDao = ConsumerHelper.getDaoProxy().getComptabiliteDao();
    }

    @AfterEach
    public void end() {
        comptabiliteDao = null;
    }


    @Test
    public void shouldGetListComtableWhenAsked() {
        assertNotNull(comptabiliteDao.getListCompteComptable());
    }

    @Test
    public void shouldGetListJournalComptableWhenAsked() {
        assertNotNull(comptabiliteDao.getListJournalComptable());
    }

    @Test
    public void shouldGetListListEcritureComptable() {
        assertNotNull(comptabiliteDao.getListEcritureComptable());
    }

    @Test
    public void shouldGetEcritureComtableById() throws NotFoundException {
        assertNotNull(comptabiliteDao.getEcritureComptable(-1));
    }

    @Test
    public void shouldThrowsNotFoundExceptionWhenGetEcritureByIdNotExist() {
        assertThrows(NotFoundException.class, () -> comptabiliteDao.getEcritureComptable(1));
    }

    @Test
    public void shouldGetEcritureComtableByRef() throws NotFoundException {
        assertNotNull(comptabiliteDao.getEcritureComptableByRef("AC-2016/00001"));
    }

    @Test
    public void shouldThrowsNotFoundExceptionWhenGetEcritureByRefNotExist() {
        assertThrows(NotFoundException.class, () -> comptabiliteDao.getEcritureComptableByRef("empty"));
    }

    @Test
    public void shouldInsertNewEcritureComptable() {
        EcritureComptable ecritureComptable = mockEcritureComptable();
        assertDoesNotThrow(() -> comptabiliteDao.insertEcritureComptable(ecritureComptable));
    }

    @Test
    public void shouldUpdateEcritureComptable() {
        EcritureComptable ecritureComptable = comptabiliteDao.getListEcritureComptable().get(0);
        assertDoesNotThrow(() -> comptabiliteDao.updateEcritureComptable(ecritureComptable));
    }

    @Test
    public void shouldDeleteEcritureComptableById() {
        assertDoesNotThrow(() -> comptabiliteDao.deleteEcritureComptable(-1));
    }


    private EcritureComptable mockEcritureComptable() {
        EcritureComptable ecritureComptable = new EcritureComptable();
        ecritureComptable.setId(0);
        ecritureComptable.setDate(new Date());
        ecritureComptable.setLibelle("Libelle Test");
        ecritureComptable.setJournal(mockJournalComptable());
        ecritureComptable.getListLigneEcriture().add(mockLigneEcritureComptable());
        ecritureComptable.getListLigneEcriture().add(mockLigneEcritureComptable());
        return ecritureComptable;
    }

    private JournalComptable mockJournalComptable() {
        JournalComptable journalComptable = new JournalComptable();
        journalComptable.setCode("AC");
        return journalComptable;
    }

    private LigneEcritureComptable mockLigneEcritureComptable() {
        LigneEcritureComptable ligneEcritureComptable = new LigneEcritureComptable();
        ligneEcritureComptable.setCompteComptable(mocCompteComptable());
        return ligneEcritureComptable;
    }

    private CompteComptable mocCompteComptable() {
        CompteComptable compteComptable = new CompteComptable();
        compteComptable.setNumero(401);
        return compteComptable;
    }
}

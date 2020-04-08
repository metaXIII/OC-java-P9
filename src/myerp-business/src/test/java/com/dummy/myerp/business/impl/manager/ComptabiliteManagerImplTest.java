package com.dummy.myerp.business.impl.manager;

import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;
import com.dummy.myerp.technical.exception.FunctionalException;
import lib.LoggingExtension;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

import java.math.BigDecimal;
import java.util.Date;

@ExtendWith(LoggingExtension.class)
public class ComptabiliteManagerImplTest {
    private Logger                  logger;
    private ComptabiliteManagerImpl manager;

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    @BeforeEach
    public void initTest() {
        logger.info("Début du test");
        manager = new ComptabiliteManagerImpl();
    }

    @AfterEach
    public void endTest() {
        manager = null;
        logger.info("Fin du test");
    }

    @Test
    public void checkEcritureComptableUnit() throws Exception {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                 null, new BigDecimal(123),
                                                                                 null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                                                                                 null, null,
                                                                                 new BigDecimal(123)));
        manager.checkEcritureComptableUnit(vEcritureComptable);
    }

    @Test
    public void checkEcritureComptableUnitViolation() {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        Assert.assertThrows(FunctionalException.class, () -> manager.checkEcritureComptableUnit(vEcritureComptable));
    }

    @Test
    public void checkEcritureComptableUnitRG2() {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
        //Débit
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                 null, new BigDecimal(123),
                                                                                 null));
        //Crédit
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(2),
                                                                                 null, null,
                                                                                 new BigDecimal(1234)));
        Assert.assertThrows(FunctionalException.class, () -> manager.checkEcritureComptableUnit(vEcritureComptable));
    }

    @Test
    public void checkEcritureComptableUnitRG3() {
        EcritureComptable vEcritureComptable;
        vEcritureComptable = new EcritureComptable();
        vEcritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        vEcritureComptable.setDate(new Date());
        vEcritureComptable.setLibelle("Libelle");
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                 null, new BigDecimal(123),
                                                                                 null));
        vEcritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(1),
                                                                                 null, new BigDecimal(123),
                                                                                 null));
        Assert.assertThrows(FunctionalException.class, () -> manager.checkEcritureComptableUnit(vEcritureComptable));
    }

}

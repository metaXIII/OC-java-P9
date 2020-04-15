package com.dummy.myerp.business.impl.manager;

import com.dummy.myerp.business.SpringRegistry;
import com.dummy.myerp.business.contrat.manager.ComptabiliteManager;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;
import com.dummy.myerp.technical.exception.FunctionalException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.assertNotNull;

@ExtendWith(SpringExtension.class)
@Transactional
@Rollback
@ContextConfiguration(locations = "classpath:bootstrapContext.xml")
public class ComptabiliteManagerImplIT {

    private ComptabiliteManager comptabiliteManager = SpringRegistry.getBusinessProxy().getComptabiliteManager();

    private EcritureComptable ecritureComptable;

    @BeforeEach
    public void setupBeforeEach() {
        ecritureComptable = new EcritureComptable();
        ecritureComptable.setId(1);
        ecritureComptable.setJournal(new JournalComptable("AC", "Achat"));
        ecritureComptable.setDate(new Date());
        ecritureComptable.setLibelle("Libelle");
        ecritureComptable.setReference("AC-2020/00001");
        ecritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(606),
                                                                                null, new BigDecimal(123),
                                                                                null));
        ecritureComptable.getListLigneEcriture().add(new LigneEcritureComptable(new CompteComptable(706),
                                                                                null, null,
                                                                                new BigDecimal(123)));
    }

    @AfterEach
    public void endTest() {
        ecritureComptable = null;
    }


    @Test
    public void testInit() {
        SpringRegistry.init();
        assertNotNull(SpringRegistry.getBusinessProxy());
        assertNotNull(SpringRegistry.getTransactionManager());
    }


    @Test
    public void insertEcritureComptableTest() throws FunctionalException {
        comptabiliteManager.insertEcritureComptable(ecritureComptable);
    }

    @Test
    public void updateEcritureComptable() throws FunctionalException {
        ecritureComptable = comptabiliteManager.getListEcritureComptable().get(1);
        ecritureComptable.setLibelle("Un nouveau libelle");
        comptabiliteManager.updateEcritureComptable(ecritureComptable);
    }

    @Test
    public void deleteEcritureComptableTest() {
        comptabiliteManager.deleteEcritureComptable(1);
    }
}

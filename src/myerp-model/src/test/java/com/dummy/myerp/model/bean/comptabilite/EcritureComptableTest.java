package com.dummy.myerp.model.bean.comptabilite;


import org.apache.commons.lang3.ObjectUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

public class EcritureComptableTest {

    private EcritureComptable ecritureComptable;

    @BeforeEach
    public void init() {
        ecritureComptable = new EcritureComptable();
    }

    @AfterEach
    public void end() {
        ecritureComptable = null;
    }

    @Test
    void getId() {
        ecritureComptable.setId(1);
        assertEquals(1, ecritureComptable.getId());
    }

    @Test
    void setId() {
        assertDoesNotThrow(() -> ecritureComptable.setId(any(Integer.class)));
    }

    @Test
    void getJournal() {
        JournalComptable journalComptable = new JournalComptable();
        journalComptable.setCode("aze");
        ecritureComptable.setJournal(journalComptable);
        assertEquals("aze", ecritureComptable.getJournal().getCode());
    }

    @Test
    void setJournal() {
        assertDoesNotThrow(() -> ecritureComptable.setJournal(new JournalComptable()));
    }

    @Test
    void getReference() {
        ecritureComptable.setReference("aze");
        assertEquals("aze", ecritureComptable.getReference());
    }

    @Test
    void setReference() {
        assertDoesNotThrow(() -> ecritureComptable.setReference(anyString()));
    }

    @Test
    void getDate() {
        Date date = new Date();
        ecritureComptable.setDate(date);
        assertEquals(date, ecritureComptable.getDate());
    }

    @Test
    void setDate() {
        assertDoesNotThrow(() -> ecritureComptable.setDate(new Date()));
    }

    @Test
    void getLibelle() {
        ecritureComptable.setLibelle("aze");
        assertEquals("aze", ecritureComptable.getLibelle());
    }

    @Test
    void setLibelle() {
        assertDoesNotThrow(() -> ecritureComptable.setLibelle(anyString()));
    }

    @Test
    void getListLigneEcriture() {
        assertEquals(0, ecritureComptable.getListLigneEcriture().size());
    }

    @Test
    void getTotalDebit() {
        assertEquals(BigDecimal.ZERO, ecritureComptable.getTotalDebit());
    }

    @Test
    void getTotalCredit() {
        assertEquals(BigDecimal.ZERO, ecritureComptable.getTotalCredit());
    }

    @Test
    void testIsEquilibree() {
        ecritureComptable.setLibelle("Equilibrée");
        ecritureComptable.getListLigneEcriture().add(this.createLigne(1, "200.50", null));
        ecritureComptable.getListLigneEcriture().add(this.createLigne(1, "100.50", "33"));
        ecritureComptable.getListLigneEcriture().add(this.createLigne(2, null, "301"));
        ecritureComptable.getListLigneEcriture().add(this.createLigne(2, "40", "7"));
        assertTrue(ecritureComptable.isEquilibree(), ecritureComptable.toString());

        ecritureComptable.getListLigneEcriture().clear();
        ecritureComptable.setLibelle("Non équilibrée");
        ecritureComptable.getListLigneEcriture().add(this.createLigne(1, "10", null));
        ecritureComptable.getListLigneEcriture().add(this.createLigne(1, "20", "1"));
        ecritureComptable.getListLigneEcriture().add(this.createLigne(2, null, "30"));
        ecritureComptable.getListLigneEcriture().add(this.createLigne(2, "1", "2"));
        assertFalse(ecritureComptable.isEquilibree(), ecritureComptable.toString());
    }

    @Test
    void testToString() {
        assertEquals("EcritureComptable{id=null, journal=null, " +
                             "reference='null', date=null, libelle='null', " +
                             "totalDebit=0, totalCredit=0, listLigneEcriture=[" +
                             "\n" + "\n" +
                             "]}", ecritureComptable.toString());
    }

    private LigneEcritureComptable createLigne(Integer pCompteComptableNumero, String pDebit, String pCredit) {
        BigDecimal vDebit  = pDebit == null ? null : new BigDecimal(pDebit);
        BigDecimal vCredit = pCredit == null ? null : new BigDecimal(pCredit);
        String vLibelle = ObjectUtils.defaultIfNull(vDebit, BigDecimal.ZERO)
                .subtract(ObjectUtils.defaultIfNull(vCredit, BigDecimal.ZERO)).toPlainString();
        LigneEcritureComptable vRetour = new LigneEcritureComptable(new CompteComptable(pCompteComptableNumero),
                                                                    vLibelle,
                                                                    vDebit, vCredit);
        return vRetour;
    }
}

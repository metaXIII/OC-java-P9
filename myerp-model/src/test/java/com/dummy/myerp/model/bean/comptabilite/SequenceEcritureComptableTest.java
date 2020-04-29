package com.dummy.myerp.model.bean.comptabilite;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;

class SequenceEcritureComptableTest {
    SequenceEcritureComptable sequenceEcritureComptable;

    @BeforeEach
    public void init() {
        sequenceEcritureComptable = new SequenceEcritureComptable();
    }

    @AfterEach
    public void end() {
        sequenceEcritureComptable = null;
    }

    @Test
    void getAnnee() {
        sequenceEcritureComptable.setAnnee(2020);
        assertEquals(2020, sequenceEcritureComptable.getAnnee());
    }

    @Test
    void setAnnee() {
        assertDoesNotThrow(() -> sequenceEcritureComptable.setAnnee(anyInt()));
    }

    @Test
    void getDerniereValeur() {
        sequenceEcritureComptable.setDerniereValeur(2020);
        assertEquals(2020, sequenceEcritureComptable.getDerniereValeur());
    }

    @Test
    void setDerniereValeur() {
        assertDoesNotThrow(() -> sequenceEcritureComptable.setDerniereValeur(anyInt()));
    }

    @Test
    void testToString() {
        assertEquals("SequenceEcritureComptable{annee=null, derniereValeur=null}", sequenceEcritureComptable.toString());
    }

    @Test
    void getJournalCode() {
        sequenceEcritureComptable.setJournalCode("code");
        assertEquals("code", sequenceEcritureComptable.getJournalCode());
    }

    @Test
    void setJournalCode() {
        assertDoesNotThrow(() -> sequenceEcritureComptable.getJournalCode());
    }
}
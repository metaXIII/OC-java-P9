package com.dummy.myerp.model.bean.comptabilite;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

class LigneEcritureComptableTest {

    LigneEcritureComptable ligneEcritureComptable;

    @BeforeEach
    public void init() {
        ligneEcritureComptable = new LigneEcritureComptable();
    }

    @AfterEach
    public void end() {
        ligneEcritureComptable = null;
    }

    @Test
    void getCompteComptable() {
        CompteComptable compteComptable = new CompteComptable(1, "libelle");
        ligneEcritureComptable.setCompteComptable(compteComptable);
        assertEquals(1, ligneEcritureComptable.getCompteComptable().getNumero());
    }

    @Test
    void setCompteComptable() {
        assertDoesNotThrow(() -> ligneEcritureComptable.setCompteComptable(any(CompteComptable.class)));
    }

    @Test
    void getLibelle() {
        ligneEcritureComptable.setLibelle("aze");
        assertEquals("aze", ligneEcritureComptable.getLibelle());
    }

    @Test
    void setLibelle() {
        assertDoesNotThrow(() -> ligneEcritureComptable.setLibelle(anyString()));
    }

    @Test
    void getDebit() {
        ligneEcritureComptable.setDebit(BigDecimal.valueOf(1234));
        assertEquals(BigDecimal.valueOf(1234), ligneEcritureComptable.getDebit());
    }

    @Test
    void setDebit() {
        assertDoesNotThrow(() -> ligneEcritureComptable.setDebit(any(BigDecimal.class)));
    }

    @Test
    void getCredit() {
        ligneEcritureComptable.setCredit(BigDecimal.valueOf(1234));
        assertEquals(BigDecimal.valueOf(1234), ligneEcritureComptable.getCredit());
    }

    @Test
    void setCredit() {
        assertDoesNotThrow(() -> ligneEcritureComptable.setCredit(any(BigDecimal.class)));
    }

    @Test
    void testToString() {
        assertEquals("LigneEcritureComptable{compteComptable=null, libelle='null', debit=null, credit=null}", ligneEcritureComptable.toString());
    }
}
package com.dummy.myerp.model.bean.comptabilite;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

class CompteComptableTest {

    private static CompteComptable compteComptable;

    @BeforeAll
    public static void initCompteComptable() {
        compteComptable = new CompteComptable();
    }

    @Test
    void getNumero() {
        compteComptable.setNumero(1);
        assertEquals(1, compteComptable.getNumero());
    }

    @Test
    void setNumero() {
        assertDoesNotThrow(() -> compteComptable.setNumero(any(Integer.class)));
    }

    @Test
    void getLibelle() {
        compteComptable.setLibelle("aze");
        assertEquals("aze", compteComptable.getLibelle());
    }

    @Test
    void setLibelle() {
        assertDoesNotThrow(() -> compteComptable.setLibelle(any(String.class)));
    }

    @Test
    void testToString() {
        compteComptable.setNumero(1);
        compteComptable.setLibelle("aze");
        assertEquals(compteComptable.getClass().getSimpleName() + "{numero=" + compteComptable.getNumero() + ", " +
                             "libelle='" + compteComptable.getLibelle() + "'}", compteComptable.toString());
    }

    @Test
    void getByNumero() {
        List<CompteComptable> compteComptableList = new ArrayList<>();
        compteComptableList.add(new CompteComptable(1, "libelle"));
        int i = 1;
        assertEquals(1, compteComptable.getByNumero(compteComptableList, i).getNumero());
    }
}
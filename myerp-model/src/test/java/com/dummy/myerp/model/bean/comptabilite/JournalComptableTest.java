package com.dummy.myerp.model.bean.comptabilite;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

class JournalComptableTest {

    JournalComptable journalComptable;

    @BeforeEach
    public void init() {
        journalComptable = new JournalComptable();
    }

    @AfterEach
    public void end() {
        journalComptable = null;
    }

    @Test
    void getCode() {
        journalComptable.setCode("aze");
        assertEquals("aze", journalComptable.getCode());
    }

    @Test
    void setCode() {
        assertDoesNotThrow(() -> journalComptable.setCode(anyString()));
    }

    @Test
    void getLibelle() {
        journalComptable.setLibelle("aze");
        assertEquals("aze", journalComptable.getLibelle());
    }

    @Test
    void setLibelle() {
        assertDoesNotThrow(() -> journalComptable.setLibelle(anyString()));
    }

    @Test
    void testToString() {
        assertEquals("JournalComptable{code='null', libelle='null'}", journalComptable.toString());
    }

    @Test
    void getByCode() {
        List<JournalComptable> journalComptableList = new ArrayList<>();
        journalComptableList.add(new JournalComptable("code", "libelle"));
        assertEquals("code", journalComptable.getByCode(journalComptableList,
                                                        "code").getCode());
    }
}
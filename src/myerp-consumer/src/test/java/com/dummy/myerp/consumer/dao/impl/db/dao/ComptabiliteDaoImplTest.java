package com.dummy.myerp.consumer.dao.impl.db.dao;

import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.CompteComptableRM;
import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.EcritureComptableRM;
import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.JournalComptableRM;
import com.dummy.myerp.consumer.db.AbstractDbConsumer;
import com.dummy.myerp.consumer.db.DataSourcesEnum;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import com.dummy.myerp.model.bean.comptabilite.EcritureComptable;
import com.dummy.myerp.model.bean.comptabilite.JournalComptable;
import com.dummy.myerp.model.bean.comptabilite.LigneEcritureComptable;
import com.dummy.myerp.technical.exception.NotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ComptabiliteDaoImplTest {
    @InjectMocks
    @Spy
    private ComptabiliteDaoImpl comptabiliteDao;

    @Mock
    private Map<DataSourcesEnum, DataSource> mapDataSource;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Mock
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private String request = "all request included";

    @BeforeEach
    public void init() {
        AbstractDbConsumer.setMapDataSource(mapDataSource);
    }

    @AfterEach
    public void end() {
    }

    @Test
    void getListCompteComptableTest() {
        ReflectionTestUtils.setField(comptabiliteDao, "SQLgetListCompteComptable", request);
        when(jdbcTemplate.query(anyString(), any(CompteComptableRM.class))).thenReturn(new ArrayList<>(Arrays.asList(new CompteComptable[10])));
        assertEquals(10, comptabiliteDao.getListCompteComptable().size());
    }

    @Test
    void getListJournalComptableTest() {
        ReflectionTestUtils.setField(comptabiliteDao, "SQLgetListJournalComptable", request);
        when(jdbcTemplate.query(anyString(), any(JournalComptableRM.class))).thenReturn(new ArrayList<>(Arrays.asList(new JournalComptable[10])));
        assertEquals(10, comptabiliteDao.getListJournalComptable().size());
    }

    @Test
    void getListEcritureComptableTest() {
        ReflectionTestUtils.setField(comptabiliteDao, "SQLgetListEcritureComptable", request);
        when(jdbcTemplate.query(anyString(), any(EcritureComptableRM.class))).thenReturn(new ArrayList<>(Arrays.asList(new EcritureComptable[10])));
        assertEquals(10, comptabiliteDao.getListEcritureComptable().size());
    }

    @Test
    void shouldReturnAnEcritureComptableWhenAskedById() throws NotFoundException {
        ReflectionTestUtils.setField(comptabiliteDao, "SQLgetEcritureComptable", request);
        when(namedParameterJdbcTemplate.queryForObject(anyString(), any(MapSqlParameterSource.class),
                                                       any(EcritureComptableRM.class))).thenReturn(mockEcritureComptable());
        assertEquals(0, comptabiliteDao.getEcritureComptable(1).getId());
    }

    @Test
    void shouldReturnAnNotFoundExceptionWhenEcritureComptableIsAskedById() {
        ReflectionTestUtils.setField(comptabiliteDao, "SQLgetEcritureComptable", request);
        when(namedParameterJdbcTemplate.queryForObject(anyString(), any(MapSqlParameterSource.class),
                                                       any(EcritureComptableRM.class))).thenThrow(EmptyResultDataAccessException.class);
        assertThrows(NotFoundException.class, () -> comptabiliteDao.getEcritureComptable(1));
    }

    @Test
    void shouldReturnAnEcritureComptableWhenAskedByRef() throws NotFoundException {
        ReflectionTestUtils.setField(comptabiliteDao, "SQLgetEcritureComptableByRef", request);
        when(namedParameterJdbcTemplate.queryForObject(anyString(), any(MapSqlParameterSource.class),
                                                       any(EcritureComptableRM.class))).thenReturn(mockEcritureComptable());
        assertEquals(0, comptabiliteDao.getEcritureComptableByRef("aze").getId());
    }

    @Test
    void shouldReturnAnNotFoundExceptionWhenEcritureComptableIsAskedByRef() {
        ReflectionTestUtils.setField(comptabiliteDao, "SQLgetEcritureComptableByRef", request);
        when(namedParameterJdbcTemplate.queryForObject(anyString(), any(MapSqlParameterSource.class),
                                                       any(EcritureComptableRM.class))).thenThrow(EmptyResultDataAccessException.class);
        assertThrows(NotFoundException.class, () -> comptabiliteDao.getEcritureComptableByRef("aze"));
    }

    @Test
    public void shouldLoadListLigneEcriture() {
        ReflectionTestUtils.setField(comptabiliteDao, "SQLloadListLigneEcriture", request);
        comptabiliteDao.loadListLigneEcriture(new EcritureComptable());
        verify(comptabiliteDao, times(1)).loadListLigneEcriture(any());
    }

    @Test
    public void shouldInsertEcritureComptable() {
        comptabiliteDao.insertEcritureComptable(mockEcritureComptable());
    }


    @Test
    public void shouldSetListEcritureCompatbleWhenSetterIsAsked() {
        assertDoesNotThrow(() -> comptabiliteDao.setSQLgetListCompteComptable(request));
        assertDoesNotThrow(() -> comptabiliteDao.setSQLgetListJournalComptable(request));
        assertDoesNotThrow(() -> comptabiliteDao.setSQLgetListEcritureComptable(request));
        assertDoesNotThrow(() -> comptabiliteDao.setSQLgetEcritureComptable(request));
        assertDoesNotThrow(() -> comptabiliteDao.setSQLgetEcritureComptableByRef(request));
        assertDoesNotThrow(() -> comptabiliteDao.setSQLloadListLigneEcriture(request));
        assertDoesNotThrow(() -> comptabiliteDao.setSQLinsertEcritureComptable(request));
        assertDoesNotThrow(() -> comptabiliteDao.setSQLinsertListLigneEcritureComptable(request));
        assertDoesNotThrow(() -> comptabiliteDao.setSQLupdateEcritureComptable(request));
        assertDoesNotThrow(() -> comptabiliteDao.setSQLdeleteEcritureComptable(request));
        assertDoesNotThrow(() -> comptabiliteDao.setSQLdeleteListLigneEcritureComptable(request));
    }

    private EcritureComptable mockEcritureComptable() {
        EcritureComptable ecritureComptable = new EcritureComptable();
        ecritureComptable.setId(0);
        ecritureComptable.setJournal(mockJournalComptable());
        ecritureComptable.getListLigneEcriture().add(mockLigneEcritureComptable());
        ecritureComptable.getListLigneEcriture().add(mockLigneEcritureComptable());
        return ecritureComptable;
    }

    private JournalComptable mockJournalComptable() {
        JournalComptable journalComptable = new JournalComptable();
        journalComptable.setCode("codeJournal");
        return journalComptable;
    }

    private LigneEcritureComptable mockLigneEcritureComptable() {
        LigneEcritureComptable ligneEcritureComptable = new LigneEcritureComptable();
        ligneEcritureComptable.setCompteComptable(mocCompteComptable());
        return ligneEcritureComptable;
    }

    private CompteComptable mocCompteComptable() {
        CompteComptable compteComptable = new CompteComptable();
        compteComptable.setNumero(1);
        return compteComptable;
    }
}

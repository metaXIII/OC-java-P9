package com.dummy.myerp.consumer.dao.impl.db.dao;

import com.dummy.myerp.consumer.dao.impl.db.rowmapper.comptabilite.CompteComptableRM;
import com.dummy.myerp.consumer.db.AbstractDbConsumer;
import com.dummy.myerp.consumer.db.DataSourcesEnum;
import com.dummy.myerp.model.bean.comptabilite.CompteComptable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.util.ReflectionTestUtils;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ComptabiliteDaoImplTest {
    @InjectMocks
    private ComptabiliteDaoImpl comptabiliteDao;

    @Mock
    private Map<DataSourcesEnum, DataSource> mapDataSource;

    @Mock
    private JdbcTemplate jdbcTemplate;

    private String request = "all request included";

    @BeforeEach
    public void init() {
    }

    @Test
    void getListCompteComptableTest() {
        AbstractDbConsumer.setMapDataSource(mapDataSource);
        ReflectionTestUtils.setField(comptabiliteDao, "vJdbcTemplate", jdbcTemplate);
        ReflectionTestUtils.setField(comptabiliteDao, "SQLgetListCompteComptable", request);
        when(jdbcTemplate.query(anyString(), any(CompteComptableRM.class))).thenReturn(new ArrayList<>(Arrays.asList(new CompteComptable[10])));
        assertEquals(10, comptabiliteDao.getListCompteComptable().size());
    }
}

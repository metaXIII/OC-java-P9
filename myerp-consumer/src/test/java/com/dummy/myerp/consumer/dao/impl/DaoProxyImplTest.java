package com.dummy.myerp.consumer.dao.impl;

import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertThrows;

class DaoProxyImplTest {

    @InjectMocks
    private DaoProxyImpl daoProxy;

    @Mock
    private ComptabiliteDao comptabiliteDao;


    @Test
    void getComptabiliteDao() {
        assertThrows(NullPointerException.class, () -> daoProxy.getComptabiliteDao());
    }

    @Test
    void setComptabiliteDao() {
        assertThrows(NullPointerException.class, () -> daoProxy.setComptabiliteDao(comptabiliteDao));
    }
}
package com.dummy.myerp.technical.util.spring;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

class NullFactoryBeanTest {


    @InjectMocks
    private NullFactoryBean nullFactoryBean;

    @Test
    void getObject() throws Exception {
        assertThrows(Exception.class, () -> nullFactoryBean.getObject());
    }

    @Test
    void getObjectType() {
        assertThrows(NullPointerException.class, () -> nullFactoryBean.getObjectType());
    }

    @Test
    void isSingleton() {
        assertThrows(NullPointerException.class, () -> nullFactoryBean.isSingleton());
    }
}
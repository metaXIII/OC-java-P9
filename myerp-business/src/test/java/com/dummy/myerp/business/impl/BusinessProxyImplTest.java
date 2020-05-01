package com.dummy.myerp.business.impl;

import com.dummy.myerp.business.contrat.manager.ComptabiliteManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
class BusinessProxyImplTest {

    @InjectMocks
    private static BusinessProxyImpl businessProxy;

    @Test
    public void getComptabiliteManager() {
        assertDoesNotThrow(() -> businessProxy.getComptabiliteManager());
        assertThat(businessProxy.getComptabiliteManager(), instanceOf(ComptabiliteManager.class));
    }
}
package com.dummy.myerp.consumer.dao.impl.db.dao;

import com.dummy.myerp.consumer.ConsumerHelper;
import com.dummy.myerp.consumer.dao.contrat.ComptabiliteDao;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@Transactional
@Rollback
@ContextConfiguration(locations = "classpath:com/dummy/myerp/consumer/applicationContext.xml")
public class ComptabiliteDaoImplTestIT {
    ComptabiliteDao comptabiliteDao;

    @BeforeEach
    public void init() {
        comptabiliteDao = ConsumerHelper.getDaoProxy().getComptabiliteDao();
    }

    @AfterEach
    public void end() {
        comptabiliteDao = null;
    }


    @Test
    public void aze() {
        assertNotNull(comptabiliteDao.getListCompteComptable());
    }

    @Test
    public void aze2() {
        assertNotNull(comptabiliteDao.getListCompteComptable());
    }


}

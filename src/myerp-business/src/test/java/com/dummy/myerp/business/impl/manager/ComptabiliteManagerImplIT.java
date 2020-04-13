package com.dummy.myerp.business.impl.manager;

import com.dummy.myerp.business.SpringRegistry;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertNotNull;

@ExtendWith(SpringExtension.class)
@Transactional
@Rollback
@ContextConfiguration(locations = "classpath:bootstrapContext.xml")
public class ComptabiliteManagerImplIT {
    @Test
    public void testInit() {
        SpringRegistry.init();
        assertNotNull(SpringRegistry.getBusinessProxy());
        assertNotNull(SpringRegistry.getTransactionManager());
    }
}

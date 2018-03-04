package org.musibondi.events.dal.impl;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.musibondi.events.dal.LogDataDAL;
import org.musibondi.events.model.LogData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.time.LocalDateTime;

@RunWith(SpringJUnit4ClassRunner.class)
@DataMongoTest
@SpringBootTest(classes = {LogDataDalImpl.class, EmbeddedMongoAutoConfiguration.class})
public class LogDataDALImplTest {

    @Autowired
    private LogDataDAL logDataDAL;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void shouldCreateLog(){

        LogData logData = new LogData();
        logData.setUserId("SomeUser");
        logData.setLastUpdateTimestamp(LocalDateTime.now());
        logData.setEventId("SomeIdEvent");

        Assert.assertTrue(logDataDAL.addLogData(logData));

        LogData logDataReturned = mongoTemplate.findOne(new Query(Criteria.where("userId").is("SomeUser")),
                LogData.class, LogDataDalImpl.LOG_DATA_COLECTION_NAME);

        Assert.assertNotNull(logDataReturned);

        Assert.assertEquals(logData.getEventId(), logDataReturned.getEventId());
        Assert.assertEquals(logData.getUserId(), logDataReturned.getUserId());
        Assert.assertEquals(logData.getLastUpdateTimestamp(), logDataReturned.getLastUpdateTimestamp());

    }
}

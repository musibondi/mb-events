package org.musibondi.events.dal.impl;

import org.musibondi.events.Exception.PersistenceException;
import org.musibondi.events.dal.LogDataDAL;
import org.musibondi.events.model.LogData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;


@Repository("logDataDAL")
public class LogDataDalImpl implements LogDataDAL {

    public static final String LOG_DATA_COLECTION_NAME = "logData";

    private static final Logger logger = LoggerFactory
            .getLogger(LogDataDalImpl.class);

    @Autowired
    private MongoOperations mongoOperations;

    @Override
    public Boolean addLogData(LogData logData) throws PersistenceException {

        try {
            mongoOperations.save(logData , LOG_DATA_COLECTION_NAME);
        } catch (Exception e){
            throw new PersistenceException(e);
        }
        return true;
    }
}

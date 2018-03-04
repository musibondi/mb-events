package org.musibondi.events.dal;

import org.musibondi.events.Exception.PersistenceException;
import org.musibondi.events.model.LogData;

public interface LogDataDAL {

    Boolean addLogData(LogData logData) throws PersistenceException;
}

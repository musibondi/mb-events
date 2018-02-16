package org.musibondi.events.Util;

import org.apache.commons.lang3.RandomStringUtils;

public class EventDataUtil {

    public static String  generateEventId(){
        return RandomStringUtils.randomAlphabetic(64);
    }
}

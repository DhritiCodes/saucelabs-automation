package com.swaglabsdemo.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogUtil {
    private static volatile Logger logger;

    private LogUtil(){}

    public static Logger getLogger(Class<?> clazz){
        if(logger == null){
            synchronized (LogUtil.class){
                if(logger == null){
                    logger = LogManager.getLogger(clazz);
                }
            }
        }
        return logger;
    }
}

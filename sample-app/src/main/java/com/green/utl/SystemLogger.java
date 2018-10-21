package com.green.utl;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.util.HashMap;

public class SystemLogger {

    static {
        String path = SystemLogger.class.getClassLoader().getResource("log4j.properties").getPath();
        System.err.println("logger path: " + path);
        PropertyConfigurator.configure(path);
    }

    public static Logger getLogger(Class<?> cls) {
        return getLogger(cls.getName());
    }

    public static Logger getLogger(String name) {

        Logger logger = (Logger) _loggerMap.get(name);
        if (logger == null) {
            synchronized (_loggerMap) {
                logger = Logger.getLogger(name);
                _loggerMap.put(name, logger);
            }
        }
        return logger;
    }

    private static final HashMap<String, Logger> _loggerMap = new HashMap<String, Logger>();

    public static Logger getLoggerForTest(Class<?> cls, String propertyFileName) {
        String path = SystemLogger.class.getClassLoader().getResource(propertyFileName).getPath();
        PropertyConfigurator.configure(path);

        Logger logger = getLogger(cls.getName());

        return logger;
    }

}

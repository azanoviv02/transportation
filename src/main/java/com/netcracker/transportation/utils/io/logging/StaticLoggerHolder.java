package com.netcracker.transportation.utils.io.logging;

public class StaticLoggerHolder {

    private static final Logger logger = new SystemOutLogger(false);

    public static void info(String format, Object...args){
        logger.info(format, args);
    }

    private StaticLoggerHolder() {
    }
}

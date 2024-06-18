package org.ko.log;

import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;

/**
 * <p>模拟日志产生r</p>
 */
public class LoggerGenerator {

    private static Logger logger = Logger.getLogger(LoggerGenerator.class);

    public static void main(String[] args) {
        int index = 0;
        while (true) {
            try {
                logger.info("value: " + index++);
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


    }

}

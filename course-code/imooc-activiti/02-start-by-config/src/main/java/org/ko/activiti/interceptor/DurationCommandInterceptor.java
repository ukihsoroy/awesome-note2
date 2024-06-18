package org.ko.activiti.interceptor;

import org.activiti.engine.impl.interceptor.AbstractCommandInterceptor;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.impl.interceptor.CommandConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * description: 执行事件拦截器 <br>
 * date: 2020/2/10 22:51 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class DurationCommandInterceptor extends AbstractCommandInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(DurationCommandInterceptor.class);

    /**
     *
     * @param config
     * @param command
     * @param <T>
     * @return
     */
    @Override
    public <T> T execute(CommandConfig config, Command<T> command) {
        long start = System.currentTimeMillis();
        try {
            return this.getNext().execute(config, command);
        } finally {
            long end = System.currentTimeMillis();

            long duration = end - start;

            logger.info("{} 执行时长 {} 毫秒", command.getClass().getSimpleName(), duration);
        }
    }
}

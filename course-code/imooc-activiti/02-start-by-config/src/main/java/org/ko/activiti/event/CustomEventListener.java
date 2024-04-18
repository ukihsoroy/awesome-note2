package org.ko.activiti.event;

import org.activiti.engine.delegate.event.ActivitiEvent;
import org.activiti.engine.delegate.event.ActivitiEventListener;
import org.activiti.engine.delegate.event.ActivitiEventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * description: 流程event listener <br>
 * date: 2020/2/10 22:05 <br>
 *
 * @author K.O <br>
 * @version 1.0 <br>
 */
public class CustomEventListener implements ActivitiEventListener {

    private static final Logger logger = LoggerFactory.getLogger(CustomEventListener.class);

    @Override
    public void onEvent(ActivitiEvent event) {
        ActivitiEventType type = event.getType();

        if (ActivitiEventType.CUSTOM.equals(type)) {
            logger.info("监听到自定义事件 {} \t {}", type, event.getProcessInstanceId());
        }
    }

    @Override
    public boolean isFailOnException() {
        return false;
    }
}

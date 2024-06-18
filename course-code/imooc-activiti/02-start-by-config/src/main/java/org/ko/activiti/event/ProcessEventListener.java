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
public class ProcessEventListener implements ActivitiEventListener {

    private static final Logger logger = LoggerFactory.getLogger(ProcessEventListener.class);

    @Override
    public void onEvent(ActivitiEvent event) {
        ActivitiEventType type = event.getType();

        if (ActivitiEventType.PROCESS_STARTED.equals(type)) {
            logger.info("流程启动 {} \t {}", type, event.getProcessInstanceId());
        } else if (ActivitiEventType.PROCESS_COMPLETED.equals(type)) {
            logger.info("流程结束 {} \t {}", type, event.getProcessInstanceId());
        }
    }

    @Override
    public boolean isFailOnException() {
        return false;
    }
}

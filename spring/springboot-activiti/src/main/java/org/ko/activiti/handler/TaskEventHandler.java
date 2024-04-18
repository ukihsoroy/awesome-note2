package org.ko.activiti.handler;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


//@Component
public class TaskEventHandler implements ExecutionListener, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void notify(DelegateExecution delegateExecution) {
        //流程实例ID
        String processInstanceId = delegateExecution.getProcessDefinitionId().split(":")[0];
        applicationContext.getBean(processInstanceId + "Handler");
//        BusinessBaseEventHandler eventHandler = (BusinessBaseEventHandler) BeanUtil.getBean(processInstanceId);
//        boolean resultFlg = eventHandler.doBusinessThing(execution);
//        if (!resultFlg) {
//            throw new BusinessException(ResultEnum.INF00017);
//        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}

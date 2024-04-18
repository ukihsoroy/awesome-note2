package org.ko.activiti.delegate;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MDCErrorDelegate implements JavaDelegate {

    private static final Logger logger = LoggerFactory.getLogger(MDCErrorDelegate.class);

    @Override
    public void execute(DelegateExecution delegateExecution) {
        logger.info("run MDCErrorDelegate");
        throw new RuntimeException("only test.");
    }
}

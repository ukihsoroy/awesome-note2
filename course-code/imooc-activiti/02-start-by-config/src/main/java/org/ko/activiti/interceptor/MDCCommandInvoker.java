package org.ko.activiti.interceptor;

import org.activiti.engine.impl.agenda.AbstractOperation;
import org.activiti.engine.impl.interceptor.DebugCommandInvoker;
import org.activiti.engine.logging.LogMDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 还需要配置，在配置文件
 */
public class MDCCommandInvoker extends DebugCommandInvoker {

    private static final Logger logger = LoggerFactory.getLogger(DebugCommandInvoker.class);

    @Override
    public void executeOperation(Runnable runnable) {
        //mdc是否生效
        boolean mdcEnabled = LogMDC.isMDCEnabled();
        //mdc生效
        LogMDC.setMDCEnabled(true);

        //如果时我们需要的操作引擎
        if (runnable instanceof AbstractOperation) {
            AbstractOperation operation = (AbstractOperation) runnable;

            //将上下文信息传递，用于打印mdc信息
            if (operation.getExecution() != null) {
                LogMDC.putMDCExecution(operation.getExecution());
            }

        }
        super.executeOperation(runnable);

        //清空mdc上下文数据，保持环境整洁
        LogMDC.clear();

        //如果原来不支持mdc, 将其还原成false
        if (!mdcEnabled) {
            LogMDC.setMDCEnabled(false);
        }

    }


}

package io.ukihsoroy.robot.event;

import java.io.IOException;

/**
 * <p></p>
 *
 * @author K.O
 * @email ko.shen@hotmail.com
 */
public interface IEvent<IEventMessage> {

    /**
     * 处理参数
     * @param request
     * @return
     */
    IEventMessage parse(String request) throws IOException;

    /**
     * 执行逻辑
     */
    void executor(String request) throws IOException;

}

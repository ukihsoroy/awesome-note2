package io.ukihsoroy.robot.entity;

import io.ukihsoroy.robot.event.IEventMessage;

/**
 * <p></p>
 *
 * @author K.O
 * @email ko.shen@hotmail.com
 */
public class ReceiveMessage implements IEventMessage {

    private String schema;

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }
}

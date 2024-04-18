package ddd.leave.infrastructure.common.event;

import java.util.Date;

public class DomainEvent {

    String id;
    Date timestamp;
    String source;
    String data;

    public DomainEvent(String id, Date timestamp, String source, String data) {
        this.id = id;
        this.timestamp = timestamp;
        this.source = source;
        this.data = data;
    }

    public DomainEvent() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

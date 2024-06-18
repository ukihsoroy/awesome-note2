package ddd.leave.domain.leave.repository.po;

import ddd.leave.domain.leave.event.LeaveEventType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
public class LeaveEventPO {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid") //这个是hibernate的注解/生成32位UUID
    @GeneratedValue(generator = "idGenerator")
    int id;
    @Enumerated(EnumType.STRING)
    LeaveEventType leaveEventType;
    Date timestamp;
    String source;
    String data;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LeaveEventType getLeaveEventType() {
        return leaveEventType;
    }

    public void setLeaveEventType(LeaveEventType leaveEventType) {
        this.leaveEventType = leaveEventType;
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

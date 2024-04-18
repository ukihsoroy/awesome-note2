package ddd.leave.domain.leave.event;

import com.alibaba.fastjson.JSON;
import ddd.leave.domain.leave.entity.Leave;
import ddd.leave.infrastructure.common.event.DomainEvent;
import ddd.leave.infrastructure.util.IdGenerator;

import java.util.Date;

public class LeaveEvent extends DomainEvent {

    LeaveEventType leaveEventType;

    public static LeaveEvent create(LeaveEventType eventType, Leave leave){
        LeaveEvent event = new LeaveEvent();
        event.setId(IdGenerator.nextId());
        event.setLeaveEventType(eventType);
        event.setTimestamp(new Date());
        event.setData(JSON.toJSONString(leave));
        return event;
    }

    public LeaveEventType getLeaveEventType() {
        return leaveEventType;
    }

    public void setLeaveEventType(LeaveEventType leaveEventType) {
        this.leaveEventType = leaveEventType;
    }
}

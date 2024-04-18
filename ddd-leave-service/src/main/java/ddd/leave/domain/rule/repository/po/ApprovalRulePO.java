package ddd.leave.domain.rule.repository.po;

import ddd.leave.domain.leave.entity.valueobject.LeaveType;
import ddd.leave.domain.person.entity.valueobject.PersonType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class ApprovalRulePO {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    String id;
    @Enumerated(EnumType.STRING)
    LeaveType leaveType;
    @Enumerated(EnumType.STRING)
    PersonType personType;
    long duration;
    String applicantRoleId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LeaveType getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(LeaveType leaveType) {
        this.leaveType = leaveType;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public void setPersonType(PersonType personType) {
        this.personType = personType;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getApplicantRoleId() {
        return applicantRoleId;
    }

    public void setApplicantRoleId(String applicantRoleId) {
        this.applicantRoleId = applicantRoleId;
    }
}

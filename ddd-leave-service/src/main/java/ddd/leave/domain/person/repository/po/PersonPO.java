package ddd.leave.domain.person.repository.po;

import ddd.leave.domain.person.entity.valueobject.PersonStatus;
import ddd.leave.domain.person.entity.valueobject.PersonType;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "person")
public class PersonPO {

    @Id
    String personId;
    String personName;
    String departmentId;
    @Enumerated(EnumType.STRING)
    PersonType personType;
    @Transient
    String leaderId;
    int roleLevel;
    Date createTime;
    Date lastModifyTime;
    @Enumerated(EnumType.STRING)
    PersonStatus status;
    @OneToOne
    RelationshipPO relationshipPO;

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public PersonType getPersonType() {
        return personType;
    }

    public void setPersonType(PersonType personType) {
        this.personType = personType;
    }

    public String getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(String leaderId) {
        this.leaderId = leaderId;
    }

    public int getRoleLevel() {
        return roleLevel;
    }

    public void setRoleLevel(int roleLevel) {
        this.roleLevel = roleLevel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public PersonStatus getStatus() {
        return status;
    }

    public void setStatus(PersonStatus status) {
        this.status = status;
    }

    public RelationshipPO getRelationshipPO() {
        return relationshipPO;
    }

    public void setRelationshipPO(RelationshipPO relationshipPO) {
        this.relationshipPO = relationshipPO;
    }
}

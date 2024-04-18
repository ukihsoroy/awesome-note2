package ddd.leave.domain.person.entity;

import ddd.leave.domain.person.entity.valueobject.PersonStatus;
import ddd.leave.domain.person.entity.valueobject.PersonType;

import java.util.Date;
import java.util.List;

public class Person {

    String personId;
    String personName;
    PersonType personType;
    List<Relationship> relationships;
    int roleLevel;
    Date createTime;
    Date lastModifyTime;
    PersonStatus status;

    public Person create(){
        this.createTime = new Date();
        this.status = PersonStatus.ENABLE;
        return this;
    }

    public Person() {
    }

    public Person(String personId, String personName, PersonType personType, List<Relationship> relationships, int roleLevel, Date createTime, Date lastModifyTime, PersonStatus status) {
        this.personId = personId;
        this.personName = personName;
        this.personType = personType;
        this.relationships = relationships;
        this.roleLevel = roleLevel;
        this.createTime = createTime;
        this.lastModifyTime = lastModifyTime;
        this.status = status;
    }

    public Person enable(){
        this.lastModifyTime = new Date();
        this.status = PersonStatus.ENABLE;
        return this;
    }

    public Person disable(){
        this.lastModifyTime = new Date();
        this.status = PersonStatus.DISABLE;
        return this;
    }

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

    public PersonType getPersonType() {
        return personType;
    }

    public void setPersonType(PersonType personType) {
        this.personType = personType;
    }

    public List<Relationship> getRelationships() {
        return relationships;
    }

    public void setRelationships(List<Relationship> relationships) {
        this.relationships = relationships;
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
}

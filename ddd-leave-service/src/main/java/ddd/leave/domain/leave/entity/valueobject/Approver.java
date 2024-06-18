package ddd.leave.domain.leave.entity.valueobject;

import ddd.leave.domain.person.entity.Person;

public class Approver {

    String personId;
    String personName;
    int level;

    public static Approver fromPerson(Person person){
        Approver approver = new Approver();
        approver.setPersonId(person.getPersonId());
        approver.setPersonName(person.getPersonName());
        approver.setLevel(person.getRoleLevel());
        return approver;
    }

    public Approver(String personId, String personName, int level) {
        this.personId = personId;
        this.personName = personName;
        this.level = level;
    }

    public Approver() {
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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}

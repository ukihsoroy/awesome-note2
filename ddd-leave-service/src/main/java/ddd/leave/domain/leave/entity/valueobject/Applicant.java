package ddd.leave.domain.leave.entity.valueobject;

public class Applicant {

    String personId;
    String personName;
    String personType;

    public Applicant() {
    }

    public Applicant(String personId, String personName, String personType) {
        this.personId = personId;
        this.personName = personName;
        this.personType = personType;
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

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }
}

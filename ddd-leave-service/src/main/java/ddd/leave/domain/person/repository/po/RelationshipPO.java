package ddd.leave.domain.person.repository.po;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RelationshipPO {

    @Id
    String id;
    String personId;
    String leaderId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(String leaderId) {
        this.leaderId = leaderId;
    }
}

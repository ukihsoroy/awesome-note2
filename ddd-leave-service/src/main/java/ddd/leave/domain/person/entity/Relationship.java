package ddd.leave.domain.person.entity;

public class Relationship {

    String id;
    String personId;
    String leaderId;
    int leaderLevel;

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

    public int getLeaderLevel() {
        return leaderLevel;
    }

    public void setLeaderLevel(int leaderLevel) {
        this.leaderLevel = leaderLevel;
    }
}

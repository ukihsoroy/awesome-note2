package ddd.leave.domain.rule.entity;

import ddd.leave.domain.leave.entity.Leave;

public class ApprovalRule {

    String personType;
    String leaveType;
    long duration;
    int maxLeaderLevel;

    public static ApprovalRule getByLeave(Leave leave){
        ApprovalRule rule = new ApprovalRule();
        rule.setPersonType(leave.getApplicant().getPersonType());
        rule.setLeaveType(leave.getType().toString());
        rule.setDuration(leave.getDuration());
        return rule;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public int getMaxLeaderLevel() {
        return maxLeaderLevel;
    }

    public void setMaxLeaderLevel(int maxLeaderLevel) {
        this.maxLeaderLevel = maxLeaderLevel;
    }
}

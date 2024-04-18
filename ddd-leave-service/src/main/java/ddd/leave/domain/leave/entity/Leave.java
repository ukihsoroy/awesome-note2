package ddd.leave.domain.leave.entity;

import ddd.leave.domain.leave.entity.valueobject.Applicant;
import ddd.leave.domain.leave.entity.valueobject.Approver;
import ddd.leave.domain.leave.entity.valueobject.LeaveType;
import ddd.leave.domain.leave.entity.valueobject.Status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 请假单信息
 */
public class Leave {

    String id;
    Applicant applicant;
    Approver approver;
    LeaveType type;
    Status status;
    Date startTime;
    Date endTime;
    long duration;
    //审批领导的最大级别
    int leaderMaxLevel;
    ApprovalInfo currentApprovalInfo;
    List<ApprovalInfo> historyApprovalInfos;

    public long getDuration() {
        return endTime.getTime() - startTime.getTime();
    }

    public Leave addHistoryApprovalInfo(ApprovalInfo approvalInfo) {
        if (null == historyApprovalInfos){
            historyApprovalInfos = new ArrayList<>();
        }
        this.historyApprovalInfos.add(approvalInfo);
        return this;
    }

    public Leave create(){
        this.setStatus(Status.APPROVING);
        this.setStartTime(new Date());
        return this;
    }

    public Leave agree(Approver nextApprover){
        this.setStatus(Status.APPROVING);
        this.setApprover(nextApprover);
        return this;
    }

    public Leave reject(Approver approver){
        this.setApprover(approver);
        this.setStatus(Status.REJECTED);
        this.setApprover(null);
        return this;
    }

    public Leave finish(){
        this.setApprover(null);
        this.setStatus(Status.APPROVED);
        this.setEndTime(new Date());
        this.setDuration(this.getEndTime().getTime() - this.getStartTime().getTime());
        return this;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Applicant getApplicant() {
        return applicant;
    }

    public void setApplicant(Applicant applicant) {
        this.applicant = applicant;
    }

    public Approver getApprover() {
        return approver;
    }

    public void setApprover(Approver approver) {
        this.approver = approver;
    }

    public LeaveType getType() {
        return type;
    }

    public void setType(LeaveType type) {
        this.type = type;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public int getLeaderMaxLevel() {
        return leaderMaxLevel;
    }

    public void setLeaderMaxLevel(int leaderMaxLevel) {
        this.leaderMaxLevel = leaderMaxLevel;
    }

    public ApprovalInfo getCurrentApprovalInfo() {
        return currentApprovalInfo;
    }

    public void setCurrentApprovalInfo(ApprovalInfo currentApprovalInfo) {
        this.currentApprovalInfo = currentApprovalInfo;
    }

    public List<ApprovalInfo> getHistoryApprovalInfos() {
        return historyApprovalInfos;
    }

    public void setHistoryApprovalInfos(List<ApprovalInfo> historyApprovalInfos) {
        this.historyApprovalInfos = historyApprovalInfos;
    }
}

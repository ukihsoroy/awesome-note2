package ddd.leave.domain.leave.entity;

import ddd.leave.domain.leave.entity.valueobject.ApprovalType;
import ddd.leave.domain.leave.entity.valueobject.Approver;

public class ApprovalInfo {

    String approvalInfoId;
    Approver approver;
    ApprovalType approvalType;
    String msg;
    long time;

    public ApprovalInfo(String approvalInfoId, Approver approver, ApprovalType approvalType, String msg, long time) {
        this.approvalInfoId = approvalInfoId;
        this.approver = approver;
        this.approvalType = approvalType;
        this.msg = msg;
        this.time = time;
    }

    public ApprovalInfo() {
    }

    public String getApprovalInfoId() {
        return approvalInfoId;
    }

    public void setApprovalInfoId(String approvalInfoId) {
        this.approvalInfoId = approvalInfoId;
    }

    public Approver getApprover() {
        return approver;
    }

    public void setApprover(Approver approver) {
        this.approver = approver;
    }

    public ApprovalType getApprovalType() {
        return approvalType;
    }

    public void setApprovalType(ApprovalType approvalType) {
        this.approvalType = approvalType;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
}

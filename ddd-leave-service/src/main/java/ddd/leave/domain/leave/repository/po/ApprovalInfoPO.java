package ddd.leave.domain.leave.repository.po;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class ApprovalInfoPO {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    String approvalInfoId;
    String leaveId;
    String applicantId;
    String approverId;
    int approverLevel;
    String approverName;
    String msg;
    long time;

    public String getApprovalInfoId() {
        return approvalInfoId;
    }

    public void setApprovalInfoId(String approvalInfoId) {
        this.approvalInfoId = approvalInfoId;
    }

    public String getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(String leaveId) {
        this.leaveId = leaveId;
    }

    public String getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(String applicantId) {
        this.applicantId = applicantId;
    }

    public String getApproverId() {
        return approverId;
    }

    public void setApproverId(String approverId) {
        this.approverId = approverId;
    }

    public int getApproverLevel() {
        return approverLevel;
    }

    public void setApproverLevel(int approverLevel) {
        this.approverLevel = approverLevel;
    }

    public String getApproverName() {
        return approverName;
    }

    public void setApproverName(String approverName) {
        this.approverName = approverName;
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

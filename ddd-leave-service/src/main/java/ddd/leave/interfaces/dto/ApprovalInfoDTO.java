package ddd.leave.interfaces.dto;

public class ApprovalInfoDTO {

    String approvalInfoId;
    ApproverDTO approverDTO;
    String msg;
    long time;

    public String getApprovalInfoId() {
        return approvalInfoId;
    }

    public void setApprovalInfoId(String approvalInfoId) {
        this.approvalInfoId = approvalInfoId;
    }

    public ApproverDTO getApproverDTO() {
        return approverDTO;
    }

    public void setApproverDTO(ApproverDTO approverDTO) {
        this.approverDTO = approverDTO;
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

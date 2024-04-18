package ddd.leave.interfaces.dto;

import java.util.List;

public class LeaveDTO {

    String leaveId;
    ApplicantDTO applicantDTO;
    ApproverDTO approverDTO;
    String leaveType;
    ApprovalInfoDTO currentApprovalInfoDTO;
    List<ApprovalInfoDTO> historyApprovalInfoDTOList;
    String startTime;
    String endTime;
    long duration;
    String status;

    public String getLeaveId() {
        return leaveId;
    }

    public void setLeaveId(String leaveId) {
        this.leaveId = leaveId;
    }

    public ApplicantDTO getApplicantDTO() {
        return applicantDTO;
    }

    public void setApplicantDTO(ApplicantDTO applicantDTO) {
        this.applicantDTO = applicantDTO;
    }

    public ApproverDTO getApproverDTO() {
        return approverDTO;
    }

    public void setApproverDTO(ApproverDTO approverDTO) {
        this.approverDTO = approverDTO;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public void setLeaveType(String leaveType) {
        this.leaveType = leaveType;
    }

    public ApprovalInfoDTO getCurrentApprovalInfoDTO() {
        return currentApprovalInfoDTO;
    }

    public void setCurrentApprovalInfoDTO(ApprovalInfoDTO currentApprovalInfoDTO) {
        this.currentApprovalInfoDTO = currentApprovalInfoDTO;
    }

    public List<ApprovalInfoDTO> getHistoryApprovalInfoDTOList() {
        return historyApprovalInfoDTOList;
    }

    public void setHistoryApprovalInfoDTOList(List<ApprovalInfoDTO> historyApprovalInfoDTOList) {
        this.historyApprovalInfoDTOList = historyApprovalInfoDTOList;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

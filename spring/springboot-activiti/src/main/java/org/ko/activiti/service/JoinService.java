package org.ko.activiti.service;

import org.activiti.engine.delegate.DelegateExecution;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class JoinService {

    //审批后的操作
    public void joinGroup (DelegateExecution execution) {
        Boolean joinApproved = (Boolean)execution.getVariable("joinApproved");
    }

    //获取审批人
    public List<String> findUsers (DelegateExecution execution) {
        return Arrays.asList("admin","wtr");
    }
}

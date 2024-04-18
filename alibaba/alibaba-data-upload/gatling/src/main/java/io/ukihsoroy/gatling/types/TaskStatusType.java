package io.ukihsoroy.gatling.types;

public enum TaskStatusType {

    /**
     * 初始化任务
     */
    PRE(0),

    /**
     * 测试中
     */
    TESTING(1),

    /**
     * 等待导出报告
     */
    REPORT(2),

    /**
     * 完毕
     */
    END(3)
    ;


    TaskStatusType(Integer status) {
        this.status = status;
    }

    /**
     * 状态
     */
    private Integer status;

    public Integer getStatus() {
        return status;
    }
}

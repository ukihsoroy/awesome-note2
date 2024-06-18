package org.ko.api.constants;

import java.util.Objects;

public class PlanConstants {

    public static final String EXCEL_EN_NAME = "plan.xlsx";
    public static final String EXCEL_ZH_NAME = "计划表数据.xlsx";

    public enum PlanStatus {
        SUBMIT("1", "待提交"),
        START("2", "待开始"),
        UNDER_WAY("3", "进行中"),
        OVER("4", "结束")
        ;

        PlanStatus(String code, String name) {
            this.code = code;
            this.name = name;
        }

        private String code;

        private String name;

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public static String format (String code) {
            for (PlanStatus planStatus : PlanStatus.values()) {
                if (Objects.equals(planStatus.getCode(), code)) {
                    return planStatus.getName();
                }
            }
            return "";
        }
    }
}
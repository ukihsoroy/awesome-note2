package io.ukihsoroy.gatling.types;

/**
 * 目标表类型
 * @author K.O
 */
public enum TableType {

    /**
     * polar增量表
     */
    POLAR_DELTA("polar_delta"),

    /**
     * POLAR全量
     */
    POLAR_ALL("polar_all"),

    /**
     * STREAMX同步的表
     */
    POLAR_ONLINE("polar_online"),

    /**
     * MC离线全量
     */
    MC_ALL("mc_all"),

    /**
     * MC离线增量
     */
    MC_DELTA("mc_delta"),

    /**
     * MC标准化
     */
    MC_STD("mc_std"),

    /**
     * MC实时合并表
     */
    MC_BASE("mc_base"),

    /**
     * MC单库对应LOG表
     */
    MC_LOG("mc_log"),

    /**
     * MC实时增量日志表
     */
    MC_LOG_DELTA("mc_log_delta"),

    /**
     * MC归档表
     */
    MC_ARCHIVE("mc_archive");


    TableType(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }

}

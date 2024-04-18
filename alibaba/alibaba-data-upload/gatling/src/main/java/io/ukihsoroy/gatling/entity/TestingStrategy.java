package io.ukihsoroy.gatling.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 测试case清单
 * @author K.O
 */
@Entity
@Table(name = "t_testing_strategy")
public class TestingStrategy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "strategy_name")
    private String strategyName;

    @Column(name = "strategy_zh_name")
    private String strategyZhName;

    @Column(name = "origin_table_type")
    private String originTableType;

    @Column(name = "target_table_type")
    private String targetTableType;

    @Column(name = "data_sync_type")
    private String dataSyncType;

    @Column(name = "enable")
    private Integer enable;

    @Column(name = "version")
    private Integer version;

    @Column(name = "gmt_created")
    private Date gmtCreated;

    @Column(name = "gmt_updated")
    private Date gmtUpdated;

    public TestingStrategy() {
    }

    public TestingStrategy(Integer id, String strategyName, String strategyZhName, String originTableType, String targetTableType, String dataSyncType, Integer enable, Integer version, Date gmtCreated, Date gmtUpdated) {
        this.id = id;
        this.strategyName = strategyName;
        this.strategyZhName = strategyZhName;
        this.originTableType = originTableType;
        this.targetTableType = targetTableType;
        this.dataSyncType = dataSyncType;
        this.enable = enable;
        this.version = version;
        this.gmtCreated = gmtCreated;
        this.gmtUpdated = gmtUpdated;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStrategyName() {
        return strategyName;
    }

    public void setStrategyName(String strategyName) {
        this.strategyName = strategyName;
    }

    public String getStrategyZhName() {
        return strategyZhName;
    }

    public void setStrategyZhName(String strategyZhName) {
        this.strategyZhName = strategyZhName;
    }

    public String getOriginTableType() {
        return originTableType;
    }

    public void setOriginTableType(String originTableType) {
        this.originTableType = originTableType;
    }

    public String getTargetTableType() {
        return targetTableType;
    }

    public void setTargetTableType(String targetTableType) {
        this.targetTableType = targetTableType;
    }

    public String getDataSyncType() {
        return dataSyncType;
    }

    public void setDataSyncType(String dataSyncType) {
        this.dataSyncType = dataSyncType;
    }

    public Integer getEnable() {
        return enable;
    }

    public void setEnable(Integer enable) {
        this.enable = enable;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Date getGmtCreated() {
        return gmtCreated;
    }

    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    public Date getGmtUpdated() {
        return gmtUpdated;
    }

    public void setGmtUpdated(Date gmtUpdated) {
        this.gmtUpdated = gmtUpdated;
    }
}

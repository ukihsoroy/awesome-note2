package io.ukihsoroy.gatling.view;

/**
 * 条数比较结果视图
 * @author K.O
 */
public class CountCompareView {

    /**
     * 原表条数
     */
    private Long originCount;

    /**
     * 目标表条数
     */
    private Long targetCount;

    /**
     * 比较结果
     */
    private boolean compare;

    public CountCompareView() {
    }

    public CountCompareView(Long originCount, Long targetCount, boolean compare) {
        this.originCount = originCount;
        this.targetCount = targetCount;
        this.compare = compare;
    }

    public Long getOriginCount() {
        return originCount;
    }

    public void setOriginCount(Long originCount) {
        this.originCount = originCount;
    }

    public Long getTargetCount() {
        return targetCount;
    }

    public void setTargetCount(Long targetCount) {
        this.targetCount = targetCount;
    }

    public boolean isCompare() {
        return compare;
    }

    public void setCompare(boolean compare) {
        this.compare = compare;
    }
}

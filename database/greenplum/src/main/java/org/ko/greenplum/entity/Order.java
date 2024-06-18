package org.ko.greenplum.entity;

import java.util.Date;

/**
 * 订单
 */
public class Order {

    public Order(Long id, Long goodsId, String userId, Integer count, Date time) {
        this.id = id;
        this.goodsId = goodsId;
        this.userId = userId;
        this.count = count;
        this.time = time;
    }

    /**
     * 订单编号
     */
    private Long id;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 下单用户编号
     */
    private String userId;

    /**
     * 购买数量
     */
    private Integer count;

    /**
     * 下单时间
     */
    private Date time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", userId='" + userId + '\'' +
                ", time=" + time +
                '}';
    }

}

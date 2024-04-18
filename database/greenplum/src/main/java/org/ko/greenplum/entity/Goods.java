package org.ko.greenplum.entity;

/**
 * 商品
 */
public class Goods {

    public Goods(Long id, String name, String stock) {
        this.id = id;
        this.name = name;
        this.stock = stock;
    }

    /**
     * 商品编号
     */
    private Long id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 库存数量
     */
    private String stock;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }
}

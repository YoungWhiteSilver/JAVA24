package com.kaishengit.entity;

import org.joda.time.DateTime;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author 
 */
public class Product implements Serializable {
    /**
     * 商品ID
     */
    private Integer id;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品副标题
     */
    private String productTitile;

    /**
     * 商品数量
     */
    private Integer productNumber;

    /**
     * 秒杀价格
     */
    private BigDecimal productPrice;

    /**
     * 市场价
     */
    private BigDecimal productMaketPrice;

    /**
     * 开始时间
     */
    private Date startTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 商品图片
     */
    private String productImage;

    /**
     * 商品描述
     */
    private String productDesc;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductTitile() {
        return productTitile;
    }

    public void setProductTitile(String productTitile) {
        this.productTitile = productTitile;
    }

    public Integer getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(Integer productNumber) {
        this.productNumber = productNumber;
    }

    public BigDecimal getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(BigDecimal productPrice) {
        this.productPrice = productPrice;
    }

    public BigDecimal getProductMaketPrice() {
        return productMaketPrice;
    }

    public void setProductMaketPrice(BigDecimal productMaketPrice) {
        this.productMaketPrice = productMaketPrice;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    /**
     * 是否在开始时间之前
     * true ： 在开始之前
     * false：在开始之后
     * @return
     */
    public boolean isStart() {

        DateTime dateTime = new DateTime(getStartTime().getTime());
        return dateTime.isBeforeNow();

    }

    /**
     * 是否在结束时间后
     * true ： 在结束之前
     * false：在结束后
     * @return
     */
    public boolean isEnd() {

        DateTime dateTime = new DateTime(getEndTime().getTime());
        return dateTime.isBeforeNow();

    }

}
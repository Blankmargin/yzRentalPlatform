package com.fhh.pojo;

import java.util.Date;

public class Order {
    private String orderId;

    private Float payment;

    private Integer paymentType;

    private Integer status;

    private Date createtime;

    private Date updatetime;

    private Date paytime;

    private Date consigntime;

    private Date endtime;

    private Date closetime;

    private String tenterLoginName;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId == null ? null : orderId.trim();
    }

    public Float getPayment() {
        return payment;
    }

    public void setPayment(Float payment) {
        this.payment = payment;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Date getPaytime() {
        return paytime;
    }

    public void setPaytime(Date paytime) {
        this.paytime = paytime;
    }

    public Date getConsigntime() {
        return consigntime;
    }

    public void setConsigntime(Date consigntime) {
        this.consigntime = consigntime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public Date getClosetime() {
        return closetime;
    }

    public void setClosetime(Date closetime) {
        this.closetime = closetime;
    }

    public String getTenterLoginName() {
        return tenterLoginName;
    }

    public void setTenterLoginName(String tenterLoginName) {
        this.tenterLoginName = tenterLoginName == null ? null : tenterLoginName.trim();
    }
}
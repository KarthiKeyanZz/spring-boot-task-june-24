package com.stackly.recharge.model;

public class Recharge {

    private Long rechargeId;
    private String mobileNumber;
    private String operator;
    private Integer amount;
    private String planType;
    private RechargeStatus status;

    public Recharge() {
    }

    public Recharge(Long rechargeId, String mobileNumber, String operator,
                    Integer amount, String planType, RechargeStatus status) {
        this.rechargeId = rechargeId;
        this.mobileNumber = mobileNumber;
        this.operator = operator;
        this.amount = amount;
        this.planType = planType;
        this.status = status;
    }

    public Long getRechargeId() {
        return rechargeId;
    }

    public void setRechargeId(Long rechargeId) {
        this.rechargeId = rechargeId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public RechargeStatus getStatus() {
        return status;
    }

    public void setStatus(RechargeStatus status) {
        this.status = status;
    }
}

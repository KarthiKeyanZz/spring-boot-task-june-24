package com.stackly.recharge.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PlanUpdateRequest {

    @NotBlank(message = "Plan type is required")
    private String planType;

    @NotNull(message = "Amount is required")
    @Min(value = 10, message = "Minimum recharge amount is 10")
    private Integer amount;

    public String getPlanType() {
        return planType;
    }

    public void setPlanType(String planType) {
        this.planType = planType;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }
}

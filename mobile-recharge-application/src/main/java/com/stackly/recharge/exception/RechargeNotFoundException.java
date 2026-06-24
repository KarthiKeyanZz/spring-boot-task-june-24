package com.stackly.recharge.exception;

public class RechargeNotFoundException extends RuntimeException {

    public RechargeNotFoundException(Long rechargeId) {
        super("Recharge not found with id: " + rechargeId);
    }
}

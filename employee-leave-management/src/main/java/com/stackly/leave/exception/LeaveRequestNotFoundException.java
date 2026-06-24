package com.stackly.leave.exception;

public class LeaveRequestNotFoundException extends RuntimeException {

    public LeaveRequestNotFoundException(Long employeeId) {
        super("Leave request not found with id: " + employeeId);
    }
}

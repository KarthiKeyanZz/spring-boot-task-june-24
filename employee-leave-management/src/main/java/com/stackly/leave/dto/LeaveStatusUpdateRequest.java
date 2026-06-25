package com.stackly.leave.dto;

import com.stackly.leave.model.LeaveStatus;
import jakarta.validation.constraints.NotNull;

public class LeaveStatusUpdateRequest {

    @NotNull(message = "Status is required (PENDING, APPROVED or REJECTED)")
    private LeaveStatus status;

    public LeaveStatus getStatus() {
        return status;
    }

    public void setStatus(LeaveStatus status) {
        this.status = status;
    }
}

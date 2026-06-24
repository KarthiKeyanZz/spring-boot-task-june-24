package com.stackly.leave.dto;

import com.stackly.leave.model.LeaveStatus;
import jakarta.validation.constraints.NotNull;

/**
 * Partial update payload used by the PATCH endpoint to approve or reject a leave request.
 */
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

package com.stackly.leave.service;

import com.stackly.leave.dto.LeaveApplicationRequest;
import com.stackly.leave.dto.LeaveStatusUpdateRequest;
import com.stackly.leave.exception.FieldValidationException;
import com.stackly.leave.exception.InvalidLeaveStateException;
import com.stackly.leave.exception.LeaveRequestNotFoundException;
import com.stackly.leave.model.LeaveRequest;
import com.stackly.leave.model.LeaveStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class LeaveService {

    private final Map<Long, LeaveRequest> store = new ConcurrentHashMap<>();
    private final AtomicLong idSequence = new AtomicLong(0);

    public LeaveRequest apply(LeaveApplicationRequest request) {
        validateNumberOfDays(request.getNumberOfDays());
        long id = idSequence.incrementAndGet();
        LeaveRequest leave = new LeaveRequest(id, request.getEmployeeName(), request.getLeaveType(),
                request.getNumberOfDays(), request.getReason(), LeaveStatus.PENDING);
        store.put(id, leave);
        return leave;
    }

    public List<LeaveRequest> findAll() {
        return new ArrayList<>(store.values());
    }

    public LeaveRequest findById(Long id) {
        LeaveRequest leave = store.get(id);
        if (leave == null) {
            throw new LeaveRequestNotFoundException(id);
        }
        return leave;
    }

    public LeaveRequest update(Long id, LeaveApplicationRequest request) {
        LeaveRequest leave = findById(id);
        if (leave.getStatus() != LeaveStatus.PENDING) {
            throw new InvalidLeaveStateException(
                    "Only PENDING leave requests can be edited. Current status: " + leave.getStatus());
        }
        validateNumberOfDays(request.getNumberOfDays());
        leave.setEmployeeName(request.getEmployeeName());
        leave.setLeaveType(request.getLeaveType());
        leave.setNumberOfDays(request.getNumberOfDays());
        leave.setReason(request.getReason());
        return leave;
    }

    public LeaveRequest updateStatus(Long id, LeaveStatusUpdateRequest request) {
        LeaveRequest leave = findById(id);
        if (leave.getStatus() != LeaveStatus.PENDING) {
            throw new InvalidLeaveStateException(
                    "Leave request is already " + leave.getStatus() + " and cannot be changed");
        }
        leave.setStatus(request.getStatus());
        return leave;
    }

    public void delete(Long id) {
        if (store.remove(id) == null) {
            throw new LeaveRequestNotFoundException(id);
        }
    }

    private void validateNumberOfDays(Integer numberOfDays) {
        if (numberOfDays != null && numberOfDays > 30) {
            throw new FieldValidationException("Leave cannot exceed 30 days in a single request");
        }
    }
}

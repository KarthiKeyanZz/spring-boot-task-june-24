package com.stackly.leave.controller;

import com.stackly.leave.dto.LeaveApplicationRequest;
import com.stackly.leave.dto.LeaveStatusUpdateRequest;
import com.stackly.leave.model.LeaveRequest;
import com.stackly.leave.service.LeaveService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leaves")
@Tag(name = "Employee Leave Management", description = "Apply for leave and manage approval status")
public class LeaveController {

    private final LeaveService leaveService;

    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    @Operation(summary = "Apply for a new leave request")
    @PostMapping
    public ResponseEntity<LeaveRequest> apply(@Valid @RequestBody LeaveApplicationRequest request) {
        LeaveRequest created = leaveService.apply(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @Operation(summary = "View all leave requests")
    @GetMapping
    public ResponseEntity<List<LeaveRequest>> getAll() {
        return ResponseEntity.ok(leaveService.findAll());
    }

    @Operation(summary = "Find a leave request by id")
    @GetMapping("/{id}")
    public ResponseEntity<LeaveRequest> getById(@PathVariable Long id) {
        return ResponseEntity.ok(leaveService.findById(id));
    }

    @Operation(summary = "Fully update a pending leave request")
    @PutMapping("/{id}")
    public ResponseEntity<LeaveRequest> update(@PathVariable Long id,
                                               @Valid @RequestBody LeaveApplicationRequest request) {
        return ResponseEntity.ok(leaveService.update(id, request));
    }

    @Operation(summary = "Approve or reject a leave request")
    @PatchMapping("/{id}/status")
    public ResponseEntity<LeaveRequest> updateStatus(@PathVariable Long id,
                                                     @Valid @RequestBody LeaveStatusUpdateRequest request) {
        return ResponseEntity.ok(leaveService.updateStatus(id, request));
    }

    @Operation(summary = "Delete a leave request")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        leaveService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

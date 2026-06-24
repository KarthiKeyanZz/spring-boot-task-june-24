package com.stackly.recharge.controller;

import com.stackly.recharge.dto.PlanUpdateRequest;
import com.stackly.recharge.dto.RechargeRequest;
import com.stackly.recharge.model.Recharge;
import com.stackly.recharge.service.RechargeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recharges")
@Tag(name = "Mobile Recharge", description = "Create and manage mobile recharge transactions")
public class RechargeController {

    private final RechargeService rechargeService;

    public RechargeController(RechargeService rechargeService) {
        this.rechargeService = rechargeService;
    }

    @Operation(summary = "Create a new mobile recharge")
    @PostMapping
    public ResponseEntity<Recharge> create(@Valid @RequestBody RechargeRequest request) {
        Recharge created = rechargeService.create(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @Operation(summary = "View all recharges")
    @GetMapping
    public ResponseEntity<List<Recharge>> getAll() {
        return ResponseEntity.ok(rechargeService.findAll());
    }

    @Operation(summary = "Find a recharge by id")
    @GetMapping("/{id}")
    public ResponseEntity<Recharge> getById(@PathVariable Long id) {
        return ResponseEntity.ok(rechargeService.findById(id));
    }

    @Operation(summary = "Fully update a pending recharge")
    @PutMapping("/{id}")
    public ResponseEntity<Recharge> update(@PathVariable Long id, @Valid @RequestBody RechargeRequest request) {
        return ResponseEntity.ok(rechargeService.update(id, request));
    }

    @Operation(summary = "Partially update the plan / amount of a pending recharge")
    @PatchMapping("/{id}/plan")
    public ResponseEntity<Recharge> updatePlan(@PathVariable Long id, @Valid @RequestBody PlanUpdateRequest request) {
        return ResponseEntity.ok(rechargeService.updatePlan(id, request));
    }

    @Operation(summary = "Delete a recharge")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        rechargeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

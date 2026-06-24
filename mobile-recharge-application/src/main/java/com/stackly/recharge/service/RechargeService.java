package com.stackly.recharge.service;

import com.stackly.recharge.dto.PlanUpdateRequest;
import com.stackly.recharge.dto.RechargeRequest;
import com.stackly.recharge.exception.InvalidRechargeStateException;
import com.stackly.recharge.exception.RechargeNotFoundException;
import com.stackly.recharge.model.Recharge;
import com.stackly.recharge.model.RechargeStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class RechargeService {

    private final Map<Long, Recharge> store = new ConcurrentHashMap<>();
    private final AtomicLong idSequence = new AtomicLong(0);

    public Recharge create(RechargeRequest request) {
        long id = idSequence.incrementAndGet();
        Recharge recharge = new Recharge(id, request.getMobileNumber(), request.getOperator(),
                request.getAmount(), request.getPlanType(), RechargeStatus.PENDING);
        store.put(id, recharge);
        return recharge;
    }

    public List<Recharge> findAll() {
        return new ArrayList<>(store.values());
    }

    public Recharge findById(Long id) {
        Recharge recharge = store.get(id);
        if (recharge == null) {
            throw new RechargeNotFoundException(id);
        }
        return recharge;
    }

    public Recharge update(Long id, RechargeRequest request) {
        Recharge recharge = findById(id);
        requirePending(recharge);
        recharge.setMobileNumber(request.getMobileNumber());
        recharge.setOperator(request.getOperator());
        recharge.setAmount(request.getAmount());
        recharge.setPlanType(request.getPlanType());
        return recharge;
    }

    public Recharge updatePlan(Long id, PlanUpdateRequest request) {
        Recharge recharge = findById(id);
        requirePending(recharge);
        recharge.setPlanType(request.getPlanType());
        recharge.setAmount(request.getAmount());
        return recharge;
    }

    public void delete(Long id) {
        if (store.remove(id) == null) {
            throw new RechargeNotFoundException(id);
        }
    }

    private void requirePending(Recharge recharge) {
        if (recharge.getStatus() != RechargeStatus.PENDING) {
            throw new InvalidRechargeStateException(
                    "Recharge is already " + recharge.getStatus() + " and cannot be modified");
        }
    }
}

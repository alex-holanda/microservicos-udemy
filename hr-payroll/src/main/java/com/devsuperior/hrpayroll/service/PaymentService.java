package com.devsuperior.hrpayroll.service;

import com.devsuperior.hrpayroll.feignclients.WorkerFeignClient;
import com.devsuperior.hrpayroll.model.Payment;
import com.devsuperior.hrpayroll.model.Worker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {


    private final WorkerFeignClient workerFeignClient;

    public Payment getPayment(Long workerId, Integer days) {
        Worker worker = workerFeignClient.getWorker(workerId).getBody();

        return Payment.builder()
                .name(worker.getName())
                .dailyIncome(worker.getDailyIncome())
                .days(days)
                .build();
    }

}

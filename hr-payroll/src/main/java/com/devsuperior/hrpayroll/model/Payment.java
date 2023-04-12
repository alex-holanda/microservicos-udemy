package com.devsuperior.hrpayroll.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Builder
public class Payment implements Serializable {

    private String name;
    private Double dailyIncome;
    private Integer days;

    public Double getTotal() {
        return days * dailyIncome;
    }
}

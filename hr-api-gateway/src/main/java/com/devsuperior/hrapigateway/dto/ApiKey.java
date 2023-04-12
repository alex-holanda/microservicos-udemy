package com.devsuperior.hrapigateway.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ApiKey {

    private String key;
    private List<String> services;
}

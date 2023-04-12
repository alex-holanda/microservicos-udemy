package com.devsuperior.hrapigateway;

import com.devsuperior.hrapigateway.dto.ApiKey;
import com.devsuperior.hrapigateway.util.AppConstants;
import io.lettuce.core.api.sync.RedisHashCommands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@EnableEurekaClient
@SpringBootApplication
public class HrApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(HrApiGatewayApplication.class, args);
    }


    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("hr-oauth", r -> r.path("/api/oauth/**")
                        .filters(f -> f.stripPrefix(1))
                        .uri("lb://hr-oauth"))
                .route("hr-user", r -> r.path("/users/**")
                        .uri("lb://hr-user"))
                .route("hr-worker", r -> r.path("/workers/**")
                        .uri("lb://hr-worker"))
                .route("hr-payroll", r -> r.path("/payments/**")
                        .uri("lb://hr-payroll"))
                .build();
    }

    @PostConstruct
    public void initKeysToRedis() {
        List<ApiKey> apiKeys = new ArrayList<>();
        apiKeys.add(new ApiKey("343C-ED0B-4137-B27E", Stream.of(AppConstants.STUDENT_SERVICE_KEY,
                AppConstants.COURSE_SERVICE_KEY).collect(Collectors.toList())));
        apiKeys.add(new ApiKey("FA48-EF0C-427E-8CCF", Stream.of(AppConstants.COURSE_SERVICE_KEY)
                .collect(Collectors.toList())));
//        List<Object> lists = redisHashComponent.hvals(AppConstants.RECORD_KEY);
//        if (lists.isEmpty()) {
//            apiKeys.forEach(k -> redisHashComponent.hset(AppConstants.RECORD_KEY, k.getKey(), k));
//        }
    }
}

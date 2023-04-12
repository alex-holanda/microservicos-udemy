package com.devsuperior.hrapigateway.filter;

import com.devsuperior.hrapigateway.dto.ApiKey;
import com.devsuperior.hrapigateway.util.MapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
public class AuthFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        var apiKeyHeader = exchange.getRequest().getHeaders().get("gatewaykey");
        log.info("Api key {}", apiKeyHeader);

        Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);

        String routeId = Objects.nonNull(route) ? route.getId() : null;

        if (Objects.isNull(routeId) || CollectionUtils.isEmpty(apiKeyHeader) || isAuthorize(routeId, apiKeyHeader.get(0))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Você não tem acesso a este recurso");
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    private boolean isAuthorize(String routeId, String apiKey) {
//        log.info("Route ID: {}", routeId);
//        Map<String, Object> obj = new HashMap<>();
//        obj.put("key", "hr-oauth");
//        obj.put("services", new String[] {"hr-oauth", "/oauth/search-oauth"});
//
//        Object apiKeyObject = obj;
//
//        if (Objects.nonNull(apiKeyObject)) {
//            ApiKey key = MapperUtils.objectMapper(apiKeyObject, ApiKey.class);
//            log.info("Contém: {}", key.getServices().contains(routeId));
//            return !key.getServices().contains(routeId);
//        }

        return false;
    }

}

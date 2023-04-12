package com.devsuperior.hroauth.service;

import com.devsuperior.hroauth.feignclients.UserFeignClient;
import com.devsuperior.hroauth.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserFeignClient userFeignClient;

    public User findByEmail(String email) {
        User user = userFeignClient.search(email).getBody();

        if (Objects.isNull(user)) {
            log.error("Email {} not found", email);
            throw new IllegalArgumentException("Email not found");
        }
        log.info("Email {} found", email);
        return user;
    }
}

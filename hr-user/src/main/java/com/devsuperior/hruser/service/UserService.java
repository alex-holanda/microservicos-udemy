package com.devsuperior.hruser.service;

import com.devsuperior.hruser.model.User;
import com.devsuperior.hruser.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Usuário não encontrado")
        );
    }
}

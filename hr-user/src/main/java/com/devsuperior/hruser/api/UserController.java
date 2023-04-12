package com.devsuperior.hruser.api;

import com.devsuperior.hruser.model.User;
import com.devsuperior.hruser.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<User> search(@RequestParam String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }
}

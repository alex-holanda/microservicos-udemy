package com.devsuperior.hroauth.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class User implements Serializable {

    private Long id;
    private String name;
    private String email;
    private String password;

    private Set<Role> roles = new HashSet<>();
}

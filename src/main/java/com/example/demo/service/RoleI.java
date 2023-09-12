package com.example.demo.service;

import com.example.demo.entity.Role;

public interface RoleI {

    Role findById(Long id);

    Role save(Role role);
}

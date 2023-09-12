package com.example.dg_task.service;

import com.example.dg_task.entity.Role;

public interface RoleI {

    Role findById(Long id);

    Role save(Role role);
}

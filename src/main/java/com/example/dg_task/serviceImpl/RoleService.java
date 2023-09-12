package com.example.dg_task.serviceImpl;


import com.example.dg_task.entity.Role;
import com.example.dg_task.repository.RoleRepo;
import com.example.dg_task.service.RoleI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService implements RoleI {

    private final RoleRepo roleRepo;


    @Override
    public Role findById(Long id){
        return roleRepo.findById(id).orElse(null);
    }



    @Override
    public Role save(Role role) {
        return roleRepo.save(role);
    }


}

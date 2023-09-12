package com.example.demo.serviceImpl;


import com.example.demo.entity.Role;
import com.example.demo.repository.RoleRepo;
import com.example.demo.service.RoleI;
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

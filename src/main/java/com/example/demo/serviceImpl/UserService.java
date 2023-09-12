package com.example.demo.serviceImpl;


import com.example.demo.DTO.RegisterRequestDto;
import com.example.demo.entity.AppUser;
import com.example.demo.entity.Role;
import com.example.demo.exceptions.DuplicateRecordException;
import com.example.demo.mapping.DtoToEntity;
import com.example.demo.repository.RoleRepo;
import com.example.demo.repository.UserRepo;
import com.example.demo.security.AppUserDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    private final PasswordEncoder passwordEncoder;
    private final DtoToEntity dtoToEntity;

    private Optional<AppUser>  findUserByEmail (String email){
        return userRepo.findUserByEmail(email);
    }


    public AppUser registerUser(RegisterRequestDto requestDto){

        Optional<AppUser> user = findUserByEmail(requestDto.getEmail());
        if(user.isPresent()){
            throw new DuplicateRecordException("This Email is already exist");
        }else{
            requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));

            Set<Role> roles = new HashSet<>();

            for (String roleName: requestDto.getRole()){

                Role role = roleRepo.findRoleByName(roleName);
                roles.add(role);

            }
            return userRepo.save(dtoToEntity.getUser(requestDto,roles));
        }
    }




    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<AppUser> appUser =	userRepo.findByUsername(userName);
        if (!appUser.isPresent()) {
            throw new UsernameNotFoundException("This User Not found with selected user name :- " + userName);
        }
        return new AppUserDetail(appUser.get());
    }



}
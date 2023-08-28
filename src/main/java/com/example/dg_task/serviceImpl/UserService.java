package com.example.dg_task.serviceImpl;


import com.example.dg_task.DTO.RegisterRequestDto;
import com.example.dg_task.entity.AppUser;
import com.example.dg_task.exceptions.DuplicateRecordException;
import com.example.dg_task.mapping.DtoToEntity;
import com.example.dg_task.repository.UserRepo;
import com.example.dg_task.security.AppUserDetail;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;


    private Optional<AppUser>  findUserByEmail (String email){
        return userRepo.findUserByEmail(email);
    }


    public AppUser registerUser(RegisterRequestDto requestDto){

        Optional<AppUser> user = findUserByEmail(requestDto.getEmail());
        if(user.isPresent()){
            throw new DuplicateRecordException("This Email is already exist");
        }else{
            requestDto.setPassword(passwordEncoder.encode(requestDto.getPassword()));
            return userRepo.save(DtoToEntity.getUser(requestDto));
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
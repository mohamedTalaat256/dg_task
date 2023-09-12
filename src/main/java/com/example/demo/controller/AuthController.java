package com.example.demo.controller;

import com.example.demo.DTO.AccessTokenDto;
import com.example.demo.DTO.JWTResponseDto;
import com.example.demo.DTO.LoginRequestDto;
import com.example.demo.DTO.RegisterRequestDto;
import com.example.demo.security.AuthService;
import com.example.demo.serviceImpl.UserService;
import com.example.demo.utilis.AppResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserService userService;



    @PostMapping("/login")
    public ResponseEntity<Object> login (@RequestBody LoginRequestDto loginRequest){
        JWTResponseDto jwtResponseDto = authService.login(loginRequest.getUsername(), loginRequest.getPassword());

        return AppResponse.generateResponse("login success", HttpStatus.OK, jwtResponseDto, true);
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register (@RequestBody @Valid RegisterRequestDto registerRequest){

        return AppResponse.generateResponse("register success", HttpStatus.OK, userService.registerUser(registerRequest), true);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AccessTokenDto> refreshAccessToken(@RequestParam String refreshToken) {
        AccessTokenDto dto = authService.refreshAccessToken(refreshToken);
        return ResponseEntity.ok(dto);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestParam String refreshToken) {
        authService.logoutUser(refreshToken);
        return ResponseEntity.ok(null);
    }
}

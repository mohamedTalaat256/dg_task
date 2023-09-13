package com.example.dg_task.security;

import com.example.dg_task.DTO.AccessTokenDto;
import com.example.dg_task.DTO.JWTResponseDto;
import com.example.dg_task.entity.AppUser;
import com.example.dg_task.entity.TokenInfo;
import com.example.dg_task.serviceImpl.TokenInfoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authManager;

    private final HttpServletRequest httpRequest;

    private final TokenInfoService tokenInfoService;

    private final JwtTokenUtils jwtTokenUtils;


    public JWTResponseDto login(String username, String password) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));



        if (authentication.isAuthenticated()){
            log.debug("Valid userDetails credentials.");

            AppUserDetail userDetails = (AppUserDetail) authentication.getPrincipal();

            SecurityContextHolder.getContext().setAuthentication(authentication);
            log.debug("SecurityContextHolder updated. [login={}]", username);


            TokenInfo tokenInfo = createLoginToken(username, userDetails.getId());


            Map<String, String> userData = new HashMap<>();


            userData.put("id", userDetails.getId().toString());
            userData.put("email", userDetails.getEmail());
            userData.put("fullName", userDetails.getFullName());
            userData.put("username", userDetails.getUsername());
            userData.put("roles", userDetails.getAuthorities().toString());



            return JWTResponseDto.builder()
                    .accessToken(tokenInfo.getAccessToken())
                    .refreshToken(tokenInfo.getRefreshToken())
                    .message("login_success")
                    .userData( userData)
                    .build();
        }else {

            return JWTResponseDto.builder()
                    .accessToken("")
                    .refreshToken("")
                    .message("fail to login")
                    .build();
        }



    }


    public TokenInfo createLoginToken(String userName, Long userId) {
        String userAgent = httpRequest.getHeader(HttpHeaders.USER_AGENT);
        InetAddress ip = null;
        try {
            ip = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String accessTokenId = UUID.randomUUID().toString();
        String accessToken = JwtTokenUtils.generateToken(userName, accessTokenId, false);
        log.info("Access token created. [tokenId={}]", accessTokenId);

        String refreshTokenId = UUID.randomUUID().toString();
        String refreshToken = JwtTokenUtils.generateToken(userName, refreshTokenId, true);
        log.info("Refresh token created. [tokenId={}]", accessTokenId);

        TokenInfo tokenInfo = new TokenInfo(accessToken, refreshToken);
        tokenInfo.setUser(new AppUser(userId));
        tokenInfo.setUserAgentText(userAgent);
        tokenInfo.setLocalIpAddress(ip.getHostAddress());
        tokenInfo.setRemoteIpAddress(httpRequest.getRemoteAddr());
        return tokenInfoService.save(tokenInfo);
    }


    public AccessTokenDto refreshAccessToken(String refreshToken) {
        if (jwtTokenUtils.isTokenExpired(refreshToken)) {
            return null;
        }
        String userName = jwtTokenUtils.getUserNameFromToken(refreshToken);
        Optional<TokenInfo> refresh = tokenInfoService.findByRefreshToken(refreshToken);
        if (!refresh.isPresent()) {
            return null;
        }

        return new AccessTokenDto(JwtTokenUtils.generateToken(userName, UUID.randomUUID().toString(), false));

    }

    public void logoutUser(String refreshToken) {

        log.info("begin logoutUser()");

        Optional<TokenInfo> tokenInfo = tokenInfoService.findByRefreshToken(refreshToken);
        if (tokenInfo.isPresent()) {

            log.info("User loged Out");

            tokenInfoService.deleteById(tokenInfo.get().getId());
        }
    }

}
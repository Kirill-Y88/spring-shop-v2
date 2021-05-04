package ru.gb.market.happy.auth.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.gb.market.happy.auth.entities.User;
import ru.gb.market.happy.auth.services.UserService;
import ru.gb.market.happy.core.filters.JWTAuthenticationFilter;
import ru.gb.market.happy.core.interfaces.ITokenService;
import ru.gb.market.happy.core.model.TokenRedis;
import ru.gb.market.happy.core.model.UserInfo;
import ru.gb.market.happy.core.model.dto.AuthRequestDto;
import ru.gb.market.happy.core.model.dto.AuthResponseDto;
import ru.gb.market.happy.core.model.dto.SignUpRequestDto;
import ru.gb.market.happy.core.repository.RedisRepository;

import javax.servlet.http.HttpServletRequest;

@RestController
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private ITokenService tokenService;

    @Autowired
    private RedisRepository redisRepository;


    @PostMapping("/signup")
    public String signUp(@RequestBody SignUpRequestDto signUpRequest) {
        User user = new User();
        user.setPassword(signUpRequest.getPassword());
        user.setLogin(signUpRequest.getLogin());
        userService.saveUser(user);
        return "OK";
    }

    @PostMapping("/login")
    public AuthResponseDto login(@RequestBody AuthRequestDto request) {
        User user = userService.findByLoginAndPassword(request.getLogin(), request.getPassword());
        System.out.println("login " + request.getLogin());
        System.out.println("password " + request.getPassword());
        UserInfo userInfo = UserInfo.builder()
                .userId(user.getId())
                .userEmail(user.getLogin())
                .role(user.getRole().getName())
                .build();
        String token = tokenService.generateToken(userInfo);
        return new AuthResponseDto(token);
    }

    @GetMapping("/log_out") //почему то "logout" не работает, видимо перекрывает метод спринга по умолчанию...
    @PreAuthorize("hasRole('ROLE_USER')")
    public String logout (HttpServletRequest httpServletRequest){
        //Очеь удивлен что можно вставить в атрибуты метода весь запрос и просто продолжить работать с ним..
        String authorizationHeadertoken = httpServletRequest.getHeader("Authorization");
        redisRepository.addToken(new TokenRedis(authorizationHeadertoken, 100L));
       // redisRepository.add(authorizationHeadertoken);
        System.out.println(authorizationHeadertoken);
        return authorizationHeadertoken;
    }

    @GetMapping("/check")
    @PreAuthorize("hasRole('ROLE_USER')")
    public String check() {
        return "OK!";
    }

}
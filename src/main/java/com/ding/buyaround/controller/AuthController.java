package com.ding.buyaround.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.ding.buyaround.Application;
import com.ding.buyaround.model.UserEntity;
import com.ding.buyaround.model.UserID;
import com.ding.buyaround.security.jwt.TokenProvider;
import com.ding.buyaround.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by pc on 2017/6/1.
 */
@RestController
@CrossOrigin
public class AuthController {
    private final UserService userService;

    private final TokenProvider tokenProvider;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthController(PasswordEncoder passwordEncoder, UserService userService,
                          TokenProvider tokenProvider, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/authenticate")
    public void authenticate() {
        // we don't have to do anything here
        // this is just a secure endpoint and the JWTFilter
        // validates the token
        // this service is called at startup of the app to check
        // if the jwt token is still valid
    }

    @PostMapping("/login")
    public UserID authorize(@Valid @RequestBody UserEntity loginUser,
                            HttpServletResponse response) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginUser.getUsername(), loginUser.getPassword());

        try {
            this.authenticationManager.authenticate(authenticationToken);

            UserID userID = new UserID();

            Long userid = this.userService.getUserID(loginUser.getUsername());
            userID.setJwt(this.tokenProvider.createToken(loginUser.getUsername()));
            userID.setUserid(userid);

            return userID;
        }
        catch (AuthenticationException e) {
            Application.logger.info("Security exception {}", e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return null;
        }
    }

    @PostMapping("/signup")
    public UserID signup(@RequestBody UserEntity signupUser) {

        UserID userID = new UserID();

        if (this.userService.usernameExists(signupUser.getUsername())) {
            userID.setJwt("EXISTS");
            return userID;
        }

        signupUser.encodePassword(this.passwordEncoder);
        this.userService.save(signupUser);

        Long userid = this.userService.getUserID(signupUser.getUsername());
        userID.setJwt(this.tokenProvider.createToken(signupUser.getUsername()));
        userID.setUserid(userid);

        return userID;
    }
}

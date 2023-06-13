package org.binar.kamihikoukiairlines.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.binar.kamihikoukiairlines.dto.LoginRequest;
import org.binar.kamihikoukiairlines.dto.SignupRequest;
import org.binar.kamihikoukiairlines.jwt.AuthenticationResponse;
import org.binar.kamihikoukiairlines.jwt.JwtUtils;
import org.binar.kamihikoukiairlines.repository.RoleRepository;
import org.binar.kamihikoukiairlines.repository.UserRepository;
import org.binar.kamihikoukiairlines.response.MessageResponse;
import org.binar.kamihikoukiairlines.response.UserInfoResponse;
import org.binar.kamihikoukiairlines.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth", description = "Login Register Controller | contains : Login & Registrasi")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    private AuthService authService;

    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@Valid @RequestBody LoginRequest loginRequest) {
        AuthenticationResponse authenticationResponse = authService.loginUser(loginRequest);

        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE, authenticationResponse.getJwt())
                .body(new UserInfoResponse("Status 200 OK",
                        "Login Sucsessfull",
                        authenticationResponse.getJwt()));

    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        authService.registerUser(signUpRequest);
        return ResponseEntity.ok(new MessageResponse("User Registered Successful"));
    }
}
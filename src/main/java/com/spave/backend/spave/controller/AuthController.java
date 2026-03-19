package com.spave.backend.spave.controller;

import com.spave.backend.spave.dto.AuthResponse;
import com.spave.backend.spave.dto.LoginRequest;
import com.spave.backend.spave.dto.RegisterRequest;
import com.spave.backend.spave.service.JwtService;
import com.spave.backend.spave.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    private void setAuthCookie(HttpServletResponse response, String token) {
        Cookie cookie = new Cookie("jwt", token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 30); // 30 minutes, matches token expiry
        response.addCookie(cookie);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request, HttpServletResponse response) {
        if (userService.emailExists(request.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(new AuthResponse("Email already in use"));
        }

        userService.registerUser(request.getEmail(), request.getPassword());
        String token = jwtService.generateToken(request.getEmail());
        setAuthCookie(response, token);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new AuthResponse("Account created successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request, HttpServletResponse response) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getEmail(), request.getPassword()));
        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new AuthResponse("Invalid email or password"));
        }

        String token = jwtService.generateToken(request.getEmail());
        setAuthCookie(response, token);
        return ResponseEntity.status(HttpStatus.OK).body(new AuthResponse("Login successful"));
    }

    @PostMapping("/logout")
    public ResponseEntity<AuthResponse> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("jwt", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return ResponseEntity.ok(new AuthResponse("Logged out successfully"));
    }
}

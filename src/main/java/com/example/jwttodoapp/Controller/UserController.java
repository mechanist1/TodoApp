package com.example.jwttodoapp.Controller;

import com.example.jwttodoapp.DTO.LoginUser;
import com.example.jwttodoapp.Module.User;
import com.example.jwttodoapp.Security.JwtService;
import com.example.jwttodoapp.Services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/Users")
@AllArgsConstructor
public class UserController {

    private final JwtService jwtService;
    private final UserService userService;
    private final AuthenticationManager authManager;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        User user = userService.getUser(id);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signInUser(@RequestBody LoginUser loginUser) {
        authManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUser.getName(), loginUser.getPassword())
        );

        String token = jwtService.generateToken(loginUser.getName());
        return ResponseEntity.ok(token);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUpUser(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok("User registered successfully");
    }
}

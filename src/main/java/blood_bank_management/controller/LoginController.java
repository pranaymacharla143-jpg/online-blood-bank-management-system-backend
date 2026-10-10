package blood_bank_management.controller;

import blood_bank_management.dto.LoginRequest;
import blood_bank_management.entity.Admin;
import blood_bank_management.service.LoginService;
import blood_bank_management.util.JwtUtil;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "*")
public class LoginController {

    private final LoginService loginService;
    private final JwtUtil jwtUtil;

    public LoginController(
            LoginService loginService,
            JwtUtil jwtUtil) {

        this.loginService = loginService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity<?> login(
            @RequestBody LoginRequest loginRequest) {

        Optional<Admin> admin = loginService.login(
                loginRequest.getEmail(),
                loginRequest.getPassword());

        if (admin.isPresent()) {

            String token = jwtUtil.generateToken(
                    admin.get().getEmail());

            Map<String, Object> response = new HashMap<>();

            response.put("message", "Login successful");
            response.put("token", token);
            response.put("adminId", admin.get().getId());
            response.put("name", admin.get().getName());
            response.put("email", admin.get().getEmail());

            return ResponseEntity.ok(response);
        }

        return ResponseEntity
                .status(401)
                .body("Invalid email or password");
    }
}
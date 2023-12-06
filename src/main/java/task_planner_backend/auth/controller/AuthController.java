package task_planner_backend.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import task_planner_backend.auth.config.JavaMailSenderImpl;
import task_planner_backend.auth.model.dto.JwtResponse;
import task_planner_backend.auth.model.dto.LoginRequest;
import task_planner_backend.auth.model.dto.PasswordResetRequest;
import task_planner_backend.auth.model.dto.SignupRequest;
import task_planner_backend.tasklogic.service.service.AuthService;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*", maxAge = 3600)
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JavaMailSenderImpl senderService;

    @PostMapping("/login")
    public JwtResponse authUser(@RequestBody LoginRequest loginRequest) {
        return authService.authenticateUser(loginRequest);
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signupRequest) {
        return authService.registerUser(signupRequest);
    }

    @PostMapping("/reset")
    public ResponseEntity<?> resetPassword(@RequestBody PasswordResetRequest passwordResetRequest) {
        ResponseEntity<?> entity = null;
        try {
            senderService.sendEmail("nu38380@gmail.com",
                    "password change",
                    "you requested to reset your passwrod");
            entity = authService.passwordResetEmail(passwordResetRequest);
        } catch(Exception e) {
            System.out.println(e);
        }
        return entity;
    }

}
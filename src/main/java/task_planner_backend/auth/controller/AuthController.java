package task_planner_backend.auth.controller;
import task_planner_backend.auth.model.User;
import task_planner_backend.utils.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        ResponseEntity<?> response = null;
        try {
            // check if email exists
            String token = RandomStringGenerator.generateRandomString(8) ;
            response = authService.passwordResetEmail(passwordResetRequest, token);
            if(response.getStatusCode() == HttpStatus.OK) {
                System.out.println("email exists");
                senderService.sendEmail( passwordResetRequest.getEmail(),
                        "password change",
                        "you requested to reset your passwrod here is a link to verify if you are real http://localhost:8080/api/auth/resetConfirmation?token="
                                + token + "&email=" + passwordResetRequest.getEmail());

            } else {
                System.out.println("email not exists");
            }
        } catch(Exception e) {
            System.out.println();
        }
        return response;
    }

    @GetMapping("/resetConfirmation")
    public ResponseEntity<?> resetConfirm(@RequestParam String email, @RequestParam String token) {
        if(email.isEmpty()) {
            return ResponseEntity.badRequest().body("Bad");
        }
        if(token.isEmpty()) {
            return ResponseEntity.badRequest().body("Badder");
        }
        User changedUser = authService.resetConfirm(email, token);
        if(changedUser != null) {
            senderService.sendEmail(changedUser.getEmail(),
                "Reset Successful!",
                "You requested to reset your passwrod. This is your new password: " + changedUser.getPassword()
            );
            return ResponseEntity.ok("Reset successful!");
        }
        return ResponseEntity.internalServerError().body("Reset failed! Something went wrong when checking the Database.");
    }
}
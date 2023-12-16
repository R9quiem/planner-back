package task_planner_backend.tasklogic.service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import task_planner_backend.auth.config.jwt.JwtUtils;
import task_planner_backend.auth.model.User;
import task_planner_backend.auth.model.dto.*;
import task_planner_backend.auth.repository.UserRepository;
import task_planner_backend.utils.RandomStringGenerator;

import java.security.Principal;
import java.util.List;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtils jwtUtils;

    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail());
    }

    public User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username " + username));
    }

    public ResponseEntity<?> registerUser(SignupRequest signupRequest) {
        if (userRepository.existsByUsername(signupRequest.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username already exists"));
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email already exists"));
        }

        User user = new User(signupRequest.getUsername(), signupRequest.getEmail(), passwordEncoder.encode(signupRequest.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User CREATED"));
    }

    public ResponseEntity<?> passwordResetEmail(PasswordResetRequest passwordResetRequest, String token){
        if (userRepository.existsByEmail(passwordResetRequest.getEmail())) {
            List<User> userList = userRepository.findAll();
            User currentUser = userList.stream().filter(
                    user -> user.getEmail().equals(
                        passwordResetRequest.getEmail()
                    )
            ).findFirst().orElse(null);
            currentUser.setPasswordResetToken(token);
            userRepository.save(currentUser);
            return ResponseEntity.ok(new MessageResponse("E-Mail found, proceed sending password reset email."));
        }
        return ResponseEntity.badRequest().body(new MessageResponse("No user found with the given E-Mail. Cancel password reset."));
    }

    public User resetConfirm(String email, String token){
        if (userRepository.existsByEmail(email)) {
            List<User> userList = userRepository.findAll();
            User currentUser = userList.stream().filter(
                    user -> user.getEmail().equals(
                        email
                    )
            ).findFirst().orElse(null);
            if(currentUser.getPasswordResetToken().equals(token)) {
                // generate and return new random password
                currentUser.setPassword(RandomStringGenerator.generateRandomString(8));
                currentUser.setPasswordResetToken(null);
                userRepository.save(currentUser);
                return currentUser;
            } else {
                return currentUser;
            }
        }
        return null;
    }
}
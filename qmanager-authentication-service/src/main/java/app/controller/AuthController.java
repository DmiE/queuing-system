package app.controller;

import app.entity.User;
import app.exceptions.ResourceAlreadyExistsException;
import app.payload.MyApiResponse;
import app.payload.JWTAuthenticationResponse;
import app.payload.LoginRequest;
import app.payload.SignUpRequest;
import app.security.JWTTokenProvider;
import app.service.UserService;
import app.service.UserServiceMariaImpl;
import app.utils.Mapper;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private UserService userService;
    private JWTTokenProvider tokenProvider;
    private AuthenticationManager authenticationManager;

    @Autowired
    public AuthController(UserServiceMariaImpl userService, JWTTokenProvider tokenProvider, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/signin")
    @ApiResponses({//
            @ApiResponse(code = 200, message = "OK", response = JWTAuthenticationResponse.class)
    })
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        User user = Mapper.mapSignUpRequestToUser(signUpRequest);
        userService.save(user, false);
        return new ResponseEntity<>(new MyApiResponse(true, "User registered successfully"), HttpStatus.OK);
    }
}
package app.controller;

import app.config.ApplicationConfig;
import app.entity.User;
import app.payload.MyApiResponse;
import app.payload.JWTAuthenticationResponse;
import app.payload.LoginRequest;
import app.payload.SignUpRequest;
import app.security.JWTAuthenticationFilter;
import app.security.JWTTokenProvider;
import app.service.MongoDBServices.UserServiceMongoImpl;
import app.service.UserService;
import app.service.MariaDBServices.UserServiceMariaImpl;
import app.utils.ApplicationBackends;
import app.utils.Mapper;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    public AuthController(UserServiceMariaImpl userServiceMaria,
                          JWTTokenProvider tokenProvider,
                          AuthenticationManager authenticationManager,
                          UserServiceMongoImpl userServiceMongo) {

        if (ApplicationConfig.applicationBackend == ApplicationBackends.MariaDB){
            this.userService = userServiceMaria;
        }
        else {
            this.userService = userServiceMongo;
        }
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/signin")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = JWTAuthenticationResponse.class)
    })
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        org.apache.log4j.Logger.getRootLogger().setLevel(org.apache.log4j.Level.DEBUG);
        logger.error("jestem");
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsernameOrEmail(),
                        loginRequest.getPassword()
                )
        );
        org.apache.log4j.Logger.getRootLogger().setLevel(org.apache.log4j.Level.DEBUG);
        logger.error("jestem");
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JWTAuthenticationResponse(jwt));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        User user = Mapper.mapSignUpRequestToUser(signUpRequest);
        userService.save(user, false);
        return new ResponseEntity<>(new MyApiResponse(true, "UserMariaDB registered successfully"), HttpStatus.OK);
    }
}
package app.controller;

import app.entity.User;
import app.payload.ApiResponse;
import app.payload.PostUserRequest;
import app.service.UserService;
import app.service.UserServiceMariaImpl;
import app.utils.Mapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService;

    @Autowired
    public AdminController(UserServiceMariaImpl userService) {this.userService = userService;}

    @PostMapping("/")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> postAdminUser(@Valid @RequestBody PostUserRequest postUserRequest) {
        User user = Mapper.mapPostUserRequestToUser(postUserRequest);
        Boolean result = userService.save(user, true);
        return result ? new ResponseEntity<>(new ApiResponse(true, "OK"), HttpStatus.OK):
                new ResponseEntity<>(new ApiResponse(false, "Cannot inert User"), HttpStatus.BAD_REQUEST);
    }
}

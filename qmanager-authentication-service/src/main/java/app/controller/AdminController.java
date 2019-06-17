package app.controller;

import app.entity.User;
import app.payload.ApiResponse;
import app.payload.PostQueueRequest;
import app.payload.PostUserRequest;
import app.service.QueueService;
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
    private QueueService queueService;

    @Autowired
    public AdminController(UserServiceMariaImpl userService, QueueService queueService) {
        this.userService = userService;
        this.queueService = queueService;
    }

    @PostMapping("/user")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> createAdminUser(@Valid @RequestBody PostUserRequest postUserRequest) {
        User user = Mapper.mapPostUserRequestToUser(postUserRequest);
        userService.save(user, true);
        return new ResponseEntity<>(new ApiResponse(true, "OK"), HttpStatus.OK);
    }

    @PostMapping("/queue")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> createQueue(@Valid @RequestBody PostQueueRequest postqueueRequest) {
        queueService.createQueue(postqueueRequest.getQueueName(), postqueueRequest.getUserId());
        return new ResponseEntity<>(new ApiResponse(true, "OK"), HttpStatus.OK);
    }


}

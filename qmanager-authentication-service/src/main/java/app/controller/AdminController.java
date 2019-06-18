package app.controller;

import app.annotations.CurrentUser;
import app.entity.User;
import app.exceptions.ResourceAlreadyExistsException;
import app.payload.MyApiResponse;
import app.payload.PostQueueRequest;
import app.payload.PostUserRequest;
import app.service.QueueService;
import app.service.UserPrincipal;
import app.service.UserService;
import app.service.UserServiceMariaImpl;
import app.utils.Mapper;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
    @ApiResponses({//
            @ApiResponse(code = 200, message = "OK", response = MyApiResponse.class),
            @ApiResponse(code = 409, message = "User with email %s already exists", response = ResourceAlreadyExistsException.class)//
    })
    public ResponseEntity<?> createAdminUser(@Valid @RequestBody PostUserRequest postUserRequest) {
        User user = Mapper.mapPostUserRequestToUser(postUserRequest);
        userService.save(user, true);
        return new ResponseEntity<>(new MyApiResponse(true, "OK"), HttpStatus.OK);
    }

    @PostMapping("/queue")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiResponses({//
            @ApiResponse(code = 200, message = "OK", response = MyApiResponse.class),
            @ApiResponse(code = 409, message = "Queue with name:  already exists", response = ResourceAlreadyExistsException.class)//
    })
    public ResponseEntity<?> createQueue(@Valid @RequestBody PostQueueRequest postqueueRequest, @CurrentUser UserPrincipal currentUser) {
        queueService.createQueue(postqueueRequest.getQueueName(), currentUser.getId());
        return new ResponseEntity<>(new MyApiResponse(true, "OK"), HttpStatus.OK);
    }


}

package app.controller;

import app.annotations.CurrentUser;
import app.config.ApplicationConfig;
import app.entity.User;
import app.exceptions.AppException;
import app.exceptions.ResourceAlreadyExistsException;
import app.exceptions.ResourceNotFoundException;
import app.payload.DeleteUserRequest;
import app.payload.MyApiResponse;
import app.payload.PostQueueRequest;
import app.payload.PostUserRequest;
import app.service.MariaDBServices.QueueServiceMariaImpl;
import app.service.MongoDBServices.QueueServiceMongoImpl;
import app.service.MongoDBServices.UserServiceMongoImpl;
import app.service.QueueService;
import app.service.UserPrincipal;
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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService;
    private QueueService queueService;

    @Autowired
    public AdminController(UserServiceMariaImpl userServiceMaria,
                           QueueServiceMariaImpl queueServiceMaria,
                           UserServiceMongoImpl userServiceMongo,
                           QueueServiceMongoImpl queueServiceMongo
                           ) {

        if (ApplicationConfig.applicationBackend == ApplicationBackends.MariaDB){
            this.userService = userServiceMaria;
            this.queueService = queueServiceMaria;
        }
        else {
            this.userService = userServiceMongo;
            this.queueService = queueServiceMongo;

        }
    }

    @PostMapping("/user")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiResponses({//
            @ApiResponse(code = 200, message = "OK", response = MyApiResponse.class),
            @ApiResponse(code = 409, message = "User with email %s already exists", response = ResourceAlreadyExistsException.class)
    })
    public ResponseEntity<?> createAdminUser(@Valid @RequestBody PostUserRequest postUserRequest) {
        User user = Mapper.mapPostUserRequestToUser(postUserRequest);
        userService.save(user, true);
        return new ResponseEntity<>(new MyApiResponse(true, "OK"), HttpStatus.OK);
    }

    @PostMapping("/queues")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiResponses({//
            @ApiResponse(code = 200, message = "OK", response = MyApiResponse.class),
            @ApiResponse(code = 409, message = "Queue with name:  already exists", response = ResourceAlreadyExistsException.class)
    })
    public ResponseEntity<?> createQueue(@Valid @RequestBody PostQueueRequest postqueueRequest, @CurrentUser UserPrincipal currentUser) {
        queueService.createQueue(postqueueRequest.getQueueName(), currentUser.getEmail());
        return new ResponseEntity<>(new MyApiResponse(true, "OK"), HttpStatus.OK);
    }

    @DeleteMapping("/queues/user")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = MyApiResponse.class),
            @ApiResponse(code = 404, message = "User  with name:  does not exists", response = ResourceNotFoundException.class),
            @ApiResponse(code = 404, message = "Queue with name:  does not exists", response = ResourceNotFoundException.class),
            @ApiResponse(code = 500, message = "User not deleted", response = ResourceNotFoundException.class)

    })
    public ResponseEntity<?> deleteUserFromQueue(@Valid @RequestBody DeleteUserRequest deleteUserRequest) {
        if (deleteUserRequest.getQueueName().isEmpty()){
            throw new AppException("QueueName is not provided");
        }
        queueService.deleteUserFromQueue(deleteUserRequest.getEmail(), deleteUserRequest.getQueueName());
        return new ResponseEntity<>(new MyApiResponse(true, "OK"), HttpStatus.OK);
    }

}

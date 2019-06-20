package app.controller;

import app.config.ApplicationConfig;
import app.entity.MariaEntities.UserMaria;
import app.entity.User;
import app.exceptions.ResourceAlreadyExistsException;
import app.payload.*;
import app.service.UserService;
import app.service.MariaServices.UserServiceMariaImpl;
import app.utils.ApplicationBackends;
import app.utils.Mapper;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class UserController{

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService;

    @Autowired
    public UserController(UserServiceMariaImpl userServiceMaria) {
        if (ApplicationConfig.applicationBackend == ApplicationBackends.MariaDB){
            this.userService = userServiceMaria;
        }
    }

    @PostMapping("users")
    @ApiResponses({//
            @ApiResponse(code = 200, message = "OK", response = MyApiResponse.class),
            @ApiResponse(code = 409, message = "Queue with name alreadu exists", response = ResourceAlreadyExistsException.class),
    })
    public ResponseEntity<?> postUser(@Valid @RequestBody PostUserRequest postUserRequest) {
        User user = Mapper.mapPostUserRequestToUser(postUserRequest);
        userService.save(user,false);
        return new ResponseEntity<>(new MyApiResponse(true, "OK"), HttpStatus.OK);
    }

    @GetMapping("users/{email}")
    @ApiResponses({//
            @ApiResponse(code = 200, message = "OK", response = GetQueueResponse.class),
            @ApiResponse(code = 400, message = "UserMaria with email does not exists", response = GetUserResponse.class),
    })
    public ResponseEntity<?> getUser(@PathVariable(value = "email") String email) {
        return ResponseEntity.ok(new GetUserResponse(userService.findByEmail(email)));
    }

    @GetMapping("users")
    @ApiResponses({//
            @ApiResponse(code = 200, message = "OK", response = GetAllUsersResponse.class),
    })
    public ResponseEntity<?> getUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(new GetAllUsersResponse(users));
    }
}

package app.controller;

import app.entity.User;
import app.payload.*;
import app.service.UserService;
import app.service.UserServiceMariaImpl;
import app.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController{

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private UserService userService;

    @Autowired
    public UserController(UserServiceMariaImpl userService) {this.userService = userService;}

    @PostMapping("")
    public ResponseEntity<?> postUser(@Valid @RequestBody PostUserRequest postUserRequest) {
        User user = Mapper.mapPostUserRequestToUser(postUserRequest);
        Boolean result = userService.save(user,false);
        return result ? new ResponseEntity<>(new ApiResponse(true, "OK"), HttpStatus.OK):
                new ResponseEntity<>(new ApiResponse(false, "Cannot inert User"), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> getUser(@PathVariable(value = "email") String email) {
        return ResponseEntity.ok(new GetUserResponse(userService.findByEmail(email)));
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> getUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(new GetAllUsersResponse(users));
    }


}

package app.controller;

import app.annotations.CurrentUser;
import app.exceptions.ResourceAlreadyExistsException;
import app.exceptions.ResourceNotFoundException;
import app.payload.AddtoQueueRequest;
import app.payload.GetQueueResponse;
import app.payload.JWTAuthenticationResponse;
import app.payload.MyApiResponse;
import app.service.QueueService;
import app.service.UserPrincipal;
import app.utils.Mapper;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/queues")
public class QueueController {
    private QueueService queueService;

    @Autowired
    public QueueController(QueueService queueService) {
        this.queueService = queueService;
    }

    @PutMapping("/")
    @ApiResponses({//
            @ApiResponse(code = 200, message = "OK", response = MyApiResponse.class),
            @ApiResponse(code = 400, message = "Queue with name does not exists", response = ResourceNotFoundException.class),
            @ApiResponse(code = 409, message = "User with id already exists in queue", response = ResourceAlreadyExistsException.class)
    })
    public ResponseEntity<?> addToQueue(@CurrentUser UserPrincipal currentUser, @Valid @RequestBody AddtoQueueRequest addtoQueue ) {
        queueService.addUserToQueue(addtoQueue.getQueueName(), currentUser.getId());
        return new ResponseEntity<>(new MyApiResponse(true, "OK"), HttpStatus.OK);
    }

    @ApiResponses({//
            @ApiResponse(code = 200, message = "OK", response = GetQueueResponse.class),
            @ApiResponse(code = 400, message = "Queue with name does not exists", response = ResourceNotFoundException.class),
    })
    @GetMapping("/{queueName}")
    public ResponseEntity<?> getQueue(@PathVariable(value = "queueName") String queueName) {
        return ResponseEntity.ok(Mapper.mapQueueRowtoGetQueueResponse(queueService.getQueue(queueName)));
    }

//    @GetMapping("/")
//    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
//    }
}

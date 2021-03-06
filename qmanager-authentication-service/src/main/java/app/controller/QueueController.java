package app.controller;

import app.annotations.CurrentUser;
import app.config.ApplicationConfig;
import app.exceptions.ResourceAlreadyExistsException;
import app.exceptions.ResourceNotFoundException;
import app.payload.*;
import app.service.MongoDBServices.QueueServiceMongoImpl;
import app.service.MongoDBServices.UserServiceMongoImpl;
import app.service.QueueService;
import app.service.MariaDBServices.QueueServiceMariaImpl;
import app.service.UserPrincipal;
import app.utils.ApplicationBackends;
import app.utils.Mapper;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/queues")
public class QueueController {
    private QueueService queueService;

    private static final Logger logger = LoggerFactory.getLogger(QueueController.class);

    @Autowired
    public QueueController(QueueServiceMariaImpl queueServiceMaria, QueueServiceMongoImpl queueServiceMongo) {
        if (ApplicationConfig.applicationBackend == ApplicationBackends.MariaDB) {
            this.queueService = queueServiceMaria;
        }
        else{
            this.queueService = queueServiceMongo;
        }
    }

    @PutMapping("")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = MyApiResponse.class),
            @ApiResponse(code = 400, message = "Queue with name does not exists", response = ResourceNotFoundException.class),
            @ApiResponse(code = 409, message = "Userwith id already exists in queue", response = ResourceAlreadyExistsException.class)
    })
    public ResponseEntity<?> addToQueue(@CurrentUser UserPrincipal currentUser, @Valid @RequestBody AddtoQueueRequest addtoQueue ) {
        org.apache.log4j.Logger.getRootLogger().setLevel(org.apache.log4j.Level.DEBUG);
        queueService.addUserToQueue(addtoQueue.getQueueName(), currentUser.getEmail());
        return new ResponseEntity<>(new MyApiResponse(true, "OK"), HttpStatus.OK);
    }

    @GetMapping("")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = GetAllQueueResponse.class),
    })
    public ResponseEntity<?> getAllQueue() {
        return ResponseEntity.ok(Mapper.mapQueueRowToGetAllQueueResponse(queueService.getAllQueues()));
    }

    @GetMapping("/queueNames")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = GetAllQueueResponse.class),
    })
    public ResponseEntity<?> getQueueNames() {
        return ResponseEntity.ok( new GetQueueNamesResponse(queueService.getQueueNames()));
    }


    @GetMapping("/{queueName}")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = GetQueueResponse.class),
            @ApiResponse(code = 400, message = "Queue with name does not exists", response = ResourceNotFoundException.class),
    })
    public ResponseEntity<?> getQueue(@PathVariable(value = "queueName") String queueName) {
        return ResponseEntity.ok(Mapper.mapQueueRowtoGetQueueResponse(queueService.getQueue(queueName)));
    }

    @DeleteMapping("/user")
    @ApiResponses({
            @ApiResponse(code = 200, message = "OK", response = MyApiResponse.class),
            @ApiResponse(code = 404, message = "User  with name:  does not exists", response = ResourceNotFoundException.class),
            @ApiResponse(code = 500, message = "User not deleted", response = ResourceNotFoundException.class)

    })
    public ResponseEntity<?> deleteUserFromQueue(@CurrentUser UserPrincipal currentUser) {
        queueService.deleteUserFromQueue(currentUser.getEmail());
        return new ResponseEntity<>(new MyApiResponse(true, "OK"), HttpStatus.OK);
    }
}

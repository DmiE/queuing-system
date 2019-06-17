package app.controller;

import app.annotations.CurrentUser;
import app.payload.AddtoQueueRequest;
import app.payload.ApiResponse;
import app.payload.GetQueueResponse;
import app.service.QueueService;
import app.service.UserPrincipal;
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
    public ResponseEntity<?> addToQueue(@CurrentUser UserPrincipal currentUser, @Valid @RequestBody AddtoQueueRequest addtoQueue ) {
        queueService.addUserToQueue(addtoQueue.getQueueName(), currentUser.getId());
        return new ResponseEntity<>(new ApiResponse(true, "OK"), HttpStatus.OK);
    }

    @GetMapping("/{queueName}")
    public ResponseEntity<?> getUser(@PathVariable(value = "queueName") String queueName) {
        return ResponseEntity.ok(new GetQueueResponse(queueService.getQueue(queueName)));
    }

//    @GetMapping("/")
//    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
//    }
}

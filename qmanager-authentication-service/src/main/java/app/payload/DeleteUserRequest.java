package app.payload;

import javax.validation.constraints.NotBlank;

public class DeleteUserRequest {

    @NotBlank
    private String email;
    private String queueName;

    public DeleteUserRequest(){
    }

    public DeleteUserRequest(String email){
        this.email = email;
    }
    public DeleteUserRequest(String email, String queueName){
        this.email = email;
        this.queueName = queueName;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getQueueName() { return queueName; }

    public void setQueueName(String queueName) { this.queueName = queueName; }


}

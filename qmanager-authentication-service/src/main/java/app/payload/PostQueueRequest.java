package app.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class PostQueueRequest {
    @NotNull(message = "Please enter id")
    private Long userId;

    @Size(max = 20, min = 3, message = "{user.name.invalid}")
    @NotEmpty(message = "Please enter name")
    private String queueName;

    public String getQueueName() { return queueName; }

    public void setQueueName(String queueName) { this.queueName = queueName; }

    public Long getUserId() { return userId; }

    public void setUserId(Long userId) { this.userId = userId;}
}

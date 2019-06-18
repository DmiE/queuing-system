package app.payload;

import app.entity.Helper.UserInQueue;
import java.util.ArrayList;
import java.util.List;

public class GetQueueResponse {

    private String queueName;
    private String queueId;
    private List<UserInQueue> userInQueue;

    public GetQueueResponse(){
        userInQueue = new ArrayList<>();
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public String getQueueId() {
        return queueId;
    }

    public void setQueueId(String queueId) {
        this.queueId = queueId;
    }

    public List<UserInQueue> getUserInQueue() {
        return userInQueue;
    }

    public void setUserInQueue(UserInQueue userInQueue) {
        this.userInQueue.add(userInQueue);
    }
}


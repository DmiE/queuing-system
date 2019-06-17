package app.payload;

import app.entity.QueueRow;
import app.entity.User;

import java.util.List;

public class GetQueueResponse {
    private List<QueueRow> queue;

    public GetQueueResponse(List<QueueRow> queue) {
        this.queue = queue;
    }

    public List<QueueRow> getQueue() {
        return this.queue;
    }

    public void setQueue(List<QueueRow> users) {
        this.queue = queue;
    }
}

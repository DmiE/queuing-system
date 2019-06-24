package app.payload;

import java.util.List;

public class GetQueueNamesResponse {

    List<String> queueNames;

    public GetQueueNamesResponse(List<String> queueNames){
        this.queueNames = queueNames;
    }

    public List<String> getQueueNames() {
        return queueNames;
    }

    public void setQueueNames(List<String> queueNames) {
        this.queueNames = queueNames;
    }
}

package app.payload;

import java.util.Set;

public class GetQueueNamesResponse {

    Set <String> queueNames;

    public GetQueueNamesResponse(Set<String> queueNames){ this.queueNames = queueNames;
    }

    public Set<String> getQueueNames() {
        return queueNames;
    }

    public void setQueueNames(Set<String> queueNames) {
        this.queueNames = queueNames;
    }
}

package app.payload;

import java.util.List;

public class GetAllQueueResponse {

    private List<GetQueueResponse> getAllQueueResponse;

    public List<GetQueueResponse> getGetAllQueueResponse() {
        return getAllQueueResponse;
    }

    public void setGetAllQueueResponse(List<GetQueueResponse> getAllQueueResponse) {
        this.getAllQueueResponse = getAllQueueResponse;
    }

    public GetAllQueueResponse(List<GetQueueResponse> getqueueResponse){
        this.getAllQueueResponse = getqueueResponse;
    }

}

package app.utils;

import app.entity.Helper.UserInQueue;
import app.entity.QueueRow;
import app.entity.User;
import app.payload.GetQueueResponse;
import app.payload.PostUserRequest;
import app.payload.SignUpRequest;

import java.util.List;

public class Mapper {

    public static User mapPostUserRequestToUser(PostUserRequest postUserRequest){
        return new User(postUserRequest.getFirstName(), postUserRequest.getLastName(),
                postUserRequest.getEmail(), postUserRequest.getPassword());
    }

    public static User mapSignUpRequestToUser(SignUpRequest signUpRequest){
        return new User(signUpRequest.getFirstName(), signUpRequest.getLastName(),
                signUpRequest.getEmail(), signUpRequest.getPassword());
    }
    public static GetQueueResponse mapQueueRowtoGetQueueResponse(List<QueueRow> rows){
        GetQueueResponse response = new GetQueueResponse();
        response.setQueueId(rows.get(0).getId().toString());
        response.setQueueName(rows.get(0).getQueueName());
        rows.forEach(queueRow -> response.setUserInQueue(new UserInQueue(queueRow)));
        return response;
    }
}

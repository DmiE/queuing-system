package app.utils;

import app.entity.Helper.UserInQueue;
import app.entity.QueueRow;
import app.entity.MariaEntities.UserMaria;
import app.entity.User;
import app.payload.GetAllQueueResponse;
import app.payload.GetQueueResponse;
import app.payload.PostUserRequest;
import app.payload.SignUpRequest;

import java.util.ArrayList;
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

    public static GetAllQueueResponse mapQueueRowToGetAllQueueResponse(List<QueueRow> rows) {
        List<GetQueueResponse> getQueueResponses = new ArrayList<>();

        System.out.println(rows.toString());
        for (QueueRow row : rows) {
            if (getQueueResponses.stream().noneMatch(response -> response.getQueueName().equals(row.getQueueName()))) {
                getQueueResponses.add(new GetQueueResponse(row.getQueueName(), row.getId().toString(), new UserInQueue(row)));
            }
            else {
                getQueueResponses.stream().
                        filter(response -> response.getQueueName().equals(row.getQueueName())).
                        forEach(response -> response.setUserInQueue(new UserInQueue(row)));
            }
            System.out.println(row.toString());
        }
        return new GetAllQueueResponse(getQueueResponses);
    }


}

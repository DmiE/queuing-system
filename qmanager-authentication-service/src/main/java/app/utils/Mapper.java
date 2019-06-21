package app.utils;

import app.entity.Helper.UserInQueue;
import app.entity.MariaEntities.RoleMariaDB;
import app.entity.QueueRow;
import app.entity.RoleName;
import app.entity.User;
import app.payload.GetAllQueueResponse;
import app.payload.GetQueueResponse;
import app.payload.PostUserRequest;
import app.payload.SignUpRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        response.setQueueId(rows.get(0).getId());
        response.setQueueName(rows.get(0).getQueueName());
        rows.forEach(queueRow -> response.setUserInQueue(new UserInQueue(queueRow)));
        return response;
    }

    public static GetAllQueueResponse mapQueueRowToGetAllQueueResponse(List<QueueRow> rows) {
        List<GetQueueResponse> getQueueResponses = new ArrayList<>();

        System.out.println(rows.toString());
        for (QueueRow row : rows) {
            if (getQueueResponses.stream().noneMatch(response -> response.getQueueName().equals(row.getQueueName()))) {
                getQueueResponses.add(new GetQueueResponse(row.getQueueName(), row.getId(), new UserInQueue(row)));
            }
            else {
                getQueueResponses.stream().
                        filter(response -> response.getQueueName().equals(row.getQueueName())).
                        forEach(response -> response.setUserInQueue(new UserInQueue(row)));
            }
        }
        return new GetAllQueueResponse(getQueueResponses);
    }

    public static Set<RoleName> mapSetRoleMariaDBtoSetRoleName(Set<RoleMariaDB> roleNames){
        Set<RoleName> roles = new HashSet<>();
        for (RoleName role : RoleName.values()){
            for (RoleMariaDB roleMaria : roleNames){
                if (role == (roleMaria.getName())){
                    roles.add(role);
                }
            }
        }
        return roles;
    }

}

package app.payload;

import app.entity.User;

import java.util.List;

public class GetAllUsersResponse {
    private List<User> users;

    public GetAllUsersResponse(List<User> users) {
        this.users = users;
    }

    public List<User> getUserMarias() {
        return this.users;
    }

    public void setUserMarias(List<User> userMarias) {
        this.users = userMarias;
    }

}

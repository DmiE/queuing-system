package app.utils;

import app.entity.User;
import app.payload.PostUserRequest;
import app.payload.SignUpRequest;

public class Mapper {

    public static User mapPostUserRequestToUser(PostUserRequest postUserRequest){
        return new User(postUserRequest.getFirstName(), postUserRequest.getLastName(),
                postUserRequest.getEmail(), postUserRequest.getPassword());
    }

    public static User mapSignUpRequestToUser(SignUpRequest signUpRequest){
        return new User(signUpRequest.getFirstName(), signUpRequest.getLastName(),
                signUpRequest.getEmail(), signUpRequest.getPassword());
    }
}

package com.example.root.deatit13;

import com.example.root.deatit13.User;

/**
 * Created by root on 05.11.16.
 */


public class ServerResponse {

    private String result;
    private String message;
    private User user;

    public String getResult() {

        return result;
    }

    public String getMessage() {

        return message;    }



    public User getUser() {

        return user;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
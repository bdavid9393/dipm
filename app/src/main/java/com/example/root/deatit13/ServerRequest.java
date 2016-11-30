package com.example.root.deatit13;

import com.example.root.deatit13.User;

/**
 * Created by root on 05.11.16.
 */

public class ServerRequest {

    private String operation;
    private User user;

    public void setOperation(String operation) {

        this.operation = operation;
    }

    public void setUser(User user) {

        this.user = user;
    }
}
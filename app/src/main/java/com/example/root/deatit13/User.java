package com.example.root.deatit13;

import android.location.Location;

import java.io.Serializable;

/**
 * Created by root on 05.11.16.
 */

public class User implements Serializable{
    private String name;
    private String email;
    private String unique_id;
    private String password;
    private String old_password;
    private String new_password;
    private Location LastKnownPos;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUnique_id() {
        return unique_id;
    }

    public void setUnique_id(String unique_id) {
        this.unique_id = unique_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getOld_password() {
        return old_password;
    }

    public void setOld_password(String old_password) {
        this.old_password = old_password;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }

    public Location getLastKnownPos() {
        return LastKnownPos;
    }

    public void setLastKnownPos(Location lastKnownPos) {
        LastKnownPos = lastKnownPos;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author sonnt
 */
public class MessageObject implements Serializable {
    private int commandType; //1 - Init Param
                             //2 - Send Message
                             //3 - get advertise list
    private String message;
    private String from;
    private String to;
    private User user;
    private ArrayList<String> advertiseList = new ArrayList<>();
    private ArrayList<User> userList = new ArrayList<>();

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void setUserList(ArrayList<User> userList) {
        this.userList = userList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public int getCommandType() {
        return commandType;
    }

    public void setCommandType(int commandType) {
        this.commandType = commandType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public ArrayList<String> getAdvertiseList() {
        return advertiseList;
    }

    public void setAdvertiseList(ArrayList<String> advertiseList) {
        this.advertiseList = advertiseList;
    }
    
}

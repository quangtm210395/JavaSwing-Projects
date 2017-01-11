/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.entity;

import java.net.Socket;

/**
 *
 * @author sonnt
 */
public class ClientObject {
    private Socket socket;
    private User user;

    public ClientObject(Socket socket, User user) {
        this.socket = socket;
        this.user = user;
    }

    public void setUsername(String username) {
        this.user.setName(username);
    }
    
    public String getUsername() {
        return this.user.getName();
    }
    
    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}

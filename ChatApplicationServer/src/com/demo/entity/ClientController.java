/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.entity;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author sonnt
 */
public class ClientController {
    private HashMap<String,Socket> clients = new HashMap<>();
    private ArrayList<User> userList = new ArrayList<>();
    public void regisClient(ClientObject client) {
        if(!clients.containsKey(client.getUsername()))
        {
            clients.put(client.getUsername(), 
                    client.getSocket());
            userList.add(client.getUser());
        }
    }
    
    public ArrayList<String> getOnlineUsers() {
        ArrayList<String> onlineUsers = new ArrayList<>();
        for(String user : clients.keySet())
        {
            onlineUsers.add(user);
        }
        return onlineUsers;
    }
    
    public ArrayList<User> getUserList() {
        return this.userList;
    }
    
    public Socket getSocketByUsername(String username) {
        Socket socket = clients.get(username);
        return socket;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banana.server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author sonnt
 */
public class ClientController {
    private HashMap<String,Socket> clients = new HashMap<>();
    public void regisClient(ClientObject client)
    {
        if(!clients.containsKey(client.getUsername()))
        {
            clients.put(client.getUsername(), 
                    client.getSocket());
        }
    }
    
    public ArrayList<String> getOnlineUsers()
    {
        ArrayList<String> onlineUsers = new ArrayList<>();
        for(String user : clients.keySet())
        {
            onlineUsers.add(user);
        }
        return onlineUsers;
    }
    
    public Socket getSocketByUsername(String username)
    {
        Socket socket = clients.get(username);
        return socket;
    }
}

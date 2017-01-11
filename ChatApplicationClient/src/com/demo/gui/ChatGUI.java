/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.gui;

import com.demo.entity.MessageObject;
import com.demo.entity.User;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JPanel;

/**
 *
 * @author Tran Minh Quang
 */
public class ChatGUI extends javax.swing.JFrame {

    /*
     * Creates new form ChatGUI
     */
    HashMap<String, ChatDialog> dialogs = new HashMap<>();
    ObjectOutputStream oos;
    
    private final User currentUser;
    Socket socket;
    private final ChatGUI currentInstance;
    private boolean isRunning = true;
    
    public ChatGUI(User user) {
        this.currentUser = user;
        currentInstance = this;
        
        initComponents();
        addListener();
        init();
    }
    
    private void init() {
        try {
            socket = new Socket("127.0.0.1", 1234);
        } catch (IOException ex) {
            Logger.getLogger(ChatGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        ClientListener cl = new ClientListener();
        cl.start();
    }
    
    public JPanel getMainPanel() {
        return mainPanel;
    }
    
    private void addListener() {        
        lstOnlineUser.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    if(lstOnlineUser.getSelectedIndex() != -1) {
                        String user = lstOnlineUser.getSelectedValue();
                        dialogs.get(user).setVisible(true);
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                
            }
        });
    }
    
    public void sendMessage(String message, String to,int commanType) {
        OutputStream os = null;
        try {
            MessageObject mo = new MessageObject();
            mo.setCommandType(commanType);
            mo.setMessage(message);
            mo.setUser(currentUser);
            mo.setFrom(currentUser.getUsername());
            mo.setTo(to);
            os = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(mo);
        } catch (IOException ex) {
            Logger.getLogger(ChatGUI.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }
    
    public void register(User user, int commandType){
        OutputStream os = null;
        try {
            MessageObject mo = new MessageObject();
            mo.setCommandType(commandType);
            mo.setUser(user);
            mo.setFrom(user.getUsername());
            os = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(mo);
        } catch (IOException ex) {
            Logger.getLogger(ChatGUI.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    class ClientListener extends Thread {

        public ClientListener() {
        }

        @Override
        public void run() {
            register(currentUser, 1);
            System.out.println(currentUser.getName());
            while(isRunning) {
                try {
                    ObjectInputStream oos = new ObjectInputStream(
                            socket.getInputStream());
                    MessageObject mo = (MessageObject) oos.readObject();
                    switch(mo.getCommandType()) {
                        case 3:
                            DefaultListModel<String> model = new DefaultListModel<>();
                            int index = 0;
                            for (User user : mo.getUserList()) {
                                if (!user.getUsername().equals(currentUser.getUsername())) {
                                    model.add(index, user.getUsername());
                                    index++;
                                    if (!dialogs.containsKey(user.getUsername())) {
                                        ChatDialog cd = new ChatDialog(
                                                currentInstance, user, currentUser);
                                        cd.setVisible(false);
                                        dialogs.put(user.getUsername(), cd);
                                    }
                                }
                            }
                            lstOnlineUser.setModel(model);
                            break;
                        case 2:
                            String from = mo.getFrom();
                            ChatDialog cd = dialogs.get(from);
                            cd.setVisible(true);
                            cd.loadMessage(mo.getMessage(), mo.getUser().getName());
                            break;
                    }
                } catch (IOException | ClassNotFoundException e) {
                    System.out.println(e);
                }
            }
        }
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        panelTop = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        lstOnlineUser = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setLayout(new java.awt.GridLayout(1, 0, 5, 0));

        panelTop.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Online User", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Dialog", 0, 14))); // NOI18N

        lstOnlineUser.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jScrollPane1.setViewportView(lstOnlineUser);

        javax.swing.GroupLayout panelTopLayout = new javax.swing.GroupLayout(panelTop);
        panelTop.setLayout(panelTopLayout);
        panelTopLayout.setHorizontalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelTopLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );
        panelTopLayout.setVerticalGroup(
            panelTopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTopLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 361, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        mainPanel.add(panelTop);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList<String> lstOnlineUser;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JPanel panelTop;
    // End of variables declaration//GEN-END:variables
}

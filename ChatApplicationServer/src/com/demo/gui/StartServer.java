/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.gui;

import com.demo.entity.ClientController;
import com.demo.entity.ClientObject;
import com.demo.entity.MessageObject;
import com.demo.entity.User;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;

/**
 *
 * @author Tran Minh Quang
 */
public class StartServer extends javax.swing.JFrame implements Runnable{

    /**
     * Creates new form Server
     */
    private final Set<String> setUser;
    private DefaultListModel<String> model = new DefaultListModel<String>();
    private ServerSocket server;
    private ClientController controller = new ClientController();
    private boolean isRunning = true;
    
    public StartServer() {
        initComponents();
        setTitle("Chat application");
        setLocationRelativeTo(null);
        lstOnline.setModel(model);
        setUser = new HashSet<>();
        
        init();
    }
    
    private void init() {
        try {
            server = new ServerSocket(1234);
        } catch (IOException ex) {
            Logger.getLogger(StartServer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Thread t = new Thread(this);
        t.start();
        System.out.println("Server started at port 1234");
    }

    @Override
    public void run() {
        while(true) {
            try {
                Socket socket = server.accept();
                ClientHandler clientHandler = new ClientHandler(socket);
                clientHandler.start();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
    
    class ClientHandler extends Thread{

        private Socket client;

        public ClientHandler(Socket socket) {
            this.client = socket;
        }

        @Override
        public void run() {
            while(isRunning) {
                InputStream is;
                ObjectInputStream ois;
                ObjectOutputStream oos;
                String username = "";
                try {
                    if (client != null) {
                        is = client.getInputStream();
                        ois = new ObjectInputStream(is);
                        MessageObject messageObject = (MessageObject)ois.readObject();
                        switch(messageObject.getCommandType()) {
                            case 1:
                                User regisUser = messageObject.getUser();
                                username = regisUser.getName();
                                ClientObject clientObject = new ClientObject(
                                        client, regisUser);
                                controller.regisClient(clientObject);
                                if (!setUser.contains(username)) {
                                    model.addElement(username);
                                    setUser.add(username);
                                }
                                log(regisUser.getName() + " has just connected");
                                MessageObject mo_notify = new MessageObject();
                                mo_notify.setCommandType(3);
                                ArrayList<String> listOnline = controller.getOnlineUsers();
                                mo_notify.setAdvertiseList(listOnline);
                                mo_notify.setUserList(controller.getUserList());
                                for (String user : listOnline) {
                                    Socket socket = controller.getSocketByUsername(user);
                                    oos = new ObjectOutputStream(socket.getOutputStream());
                                    oos.writeObject(mo_notify);
                                }
                                break;
                            case 2:
                                Socket sendSocket = controller.getSocketByUsername(messageObject.getTo());
                                oos = new ObjectOutputStream(sendSocket.getOutputStream());
                                oos.writeObject(messageObject);
                                break;
                            case 3:
                                break;
                        }
                    }
                }catch(IOException e) {
                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, e);
                    if (model.contains(username)) {
                        model.removeElement(username);
                        setUser.remove(username);
                    }
                    client = null;
                    interrupt();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ClientHandler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
    private void log(String mess) {
        System.out.println(mess);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlUsers = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lstRegister = new javax.swing.JList<>();
        pnlOnlineUsers = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lstOnline = new javax.swing.JList<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlUsers.setBorder(javax.swing.BorderFactory.createTitledBorder("Registered Users"));

        lstRegister.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(lstRegister);

        javax.swing.GroupLayout pnlUsersLayout = new javax.swing.GroupLayout(pnlUsers);
        pnlUsers.setLayout(pnlUsersLayout);
        pnlUsersLayout.setHorizontalGroup(
            pnlUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlUsersLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlUsersLayout.setVerticalGroup(
            pnlUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlUsersLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );

        pnlOnlineUsers.setBorder(javax.swing.BorderFactory.createTitledBorder("Online Users"));

        lstOnline.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(lstOnline);

        javax.swing.GroupLayout pnlOnlineUsersLayout = new javax.swing.GroupLayout(pnlOnlineUsers);
        pnlOnlineUsers.setLayout(pnlOnlineUsersLayout);
        pnlOnlineUsersLayout.setHorizontalGroup(
            pnlOnlineUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOnlineUsersLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlOnlineUsersLayout.setVerticalGroup(
            pnlOnlineUsersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlOnlineUsersLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 253, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlUsers, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(pnlOnlineUsers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlOnlineUsers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlUsers, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StartServer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new StartServer().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JList<String> lstOnline;
    private javax.swing.JList<String> lstRegister;
    private javax.swing.JPanel pnlOnlineUsers;
    private javax.swing.JPanel pnlUsers;
    // End of variables declaration//GEN-END:variables
}

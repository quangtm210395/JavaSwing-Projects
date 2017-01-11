/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package banana.client.gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.FileChooser;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;

/**
 *
 * @author ADMIN
 */
public class ChatDialog extends javax.swing.JDialog {

    /**
     * Creates new form ChatDialog
     */
    MainScreen parent;
    String toUser;
    String current;
    Object object;
    public ChatDialog(MainScreen parent,String toUser,String current) {
        super(parent, false);
        this.parent = parent;
        this.toUser = toUser;
        this.current = current;
        initComponents();
        this.setTitle(toUser);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lstChat = new javax.swing.JList<>();
        txtChat = new javax.swing.JTextField();
        btnSend = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        lbl = new javax.swing.JLabel();

        jScrollPane1.setViewportView(lstChat);

        btnSend.setText("Send");
        btnSend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSendActionPerformed(evt);
            }
        });

        btnAdd.setText("Add file");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtChat, javax.swing.GroupLayout.DEFAULT_SIZE, 306, Short.MAX_VALUE)
                            .addComponent(lbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSend, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtChat)
                    .addComponent(btnSend, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(lbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    ArrayList<String> messages = new ArrayList<>();
    public void loadMessage(String message,String sender)
    {
        messages.add(sender+" : "+message);
        DefaultListModel<String> model = new DefaultListModel<>();
        int index =0;
        for (String item : messages) {
            model.add(index, item);
            index++;
        }
        lstChat.setModel(model);
        lstChat.setSelectedIndex(index-1);
        lstChat.repaint();
        
    }
    
    private void btnSendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSendActionPerformed
        parent.sendMessage(txtChat.getText(),toUser ,2);
        loadMessage(txtChat.getText(),current);
        lbl.setText("");
        txtChat.setText("");
    }//GEN-LAST:event_btnSendActionPerformed

    public void sendFile(String path)
    {
        try {
            FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
            oos.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ChatDialog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ChatDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        FileInputStream fis = null;
        try {
            // TODO add your handling code here:
            JFileChooser fs = new JFileChooser();
            File file = null;
            int val = fs.showOpenDialog(parent);
            if(val == JFileChooser.APPROVE_OPTION)
            {
                String fileName = fs.getSelectedFile().getName();
                String path = fs.getSelectedFile().getPath();
                lbl.setText(fileName);
                file = fs.getSelectedFile();
            }   
            fis = new FileInputStream(fs.getSelectedFile().getPath());
            OutputStream os = parent.socket.getOutputStream();
            int packetSize = 65536;
            byte[] buffer = new byte[packetSize];
            parent.oos.write(buffer);
            int read = 0;
            do {                
                os.write(buffer,0,read);
                System.out.println(read);
                
            } while ((read = fis.read(buffer)) != -1);
            os.flush();
            System.out.println("sent");
            fis.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ChatDialog.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ChatDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_btnAddActionPerformed

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnSend;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl;
    private javax.swing.JList<String> lstChat;
    private javax.swing.JTextField txtChat;
    // End of variables declaration//GEN-END:variables
}
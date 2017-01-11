/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.demo.gui;

import com.demo.entity.Properties;
import com.demo.entity.User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.Stack;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author Tran Minh Quang
 */
public class ClientGUI extends JFrame{
    private static ClientGUI ourInstance = new ClientGUI();
    private final String title = "Chat Client";
    private final Stack<JPanel> stackPanel = new Stack<>();
    public ArrayList<User> userList = new ArrayList<>();
    public ChatGUI chatGUI;
    
    private JPanel mainPanel;
    private JButton buttonBack;
    private JButton buttonExit;
    
    /**
     * Creates new Form ClientGUI
     */
    public ClientGUI() {
        
        settingWindow();
        initComponent();
        addListeners();
        
        userList.add(new User("quangtm", "minhquang@95", "Tran Minh Quang"));
        userList.add(new User("quang123", "minhquang@95", "Tran Viet Dung"));
        userList.add(new User("quang12", "minhquang@95", "Rin Tran"));
    }
    
    public static ClientGUI getInstance() {
        if (ourInstance == null) {
            ourInstance = new ClientGUI();
        }
        return ourInstance;
    }
    
    
    private void settingWindow() {
        
        setTitle(title);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        refreshScreen();
        setSize(Properties.getScreenWidth(), Properties.getScreenHeight());

        setLocationRelativeTo(null);
        setResizable(false);
        
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ClientGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    private void initComponent() {
        
        JPanel allPanel = new JPanel(new BorderLayout());
        getContentPane().add(allPanel, BorderLayout.CENTER);
        
        JPanel topPanel = new JPanel(new BorderLayout());
        allPanel.add(topPanel, BorderLayout.NORTH);
        
        buttonBack = new JButton("Back");
        buttonBack.setFont(new java.awt.Font("Calibri", 0, 17));
        topPanel.add(buttonBack, BorderLayout.WEST);

        JPanel topPanelCenter = new JPanel(new FlowLayout(FlowLayout.CENTER));
        topPanelCenter.setBorder(new LineBorder(Color.BLACK, 2));
        topPanel.add(topPanelCenter, BorderLayout.CENTER);

        JLabel lblName = new JLabel(title);
        lblName.setFont(new java.awt.Font("Calibri", 0, 17));
        topPanelCenter.add(lblName);

        buttonExit = new JButton("Exit");
        buttonExit.setFont(new java.awt.Font("Calibri", 0, 17));
        topPanel.add(buttonExit, BorderLayout.EAST);
        
        mainPanel = new JPanel(new GridLayout(0,1));
        mainPanel.setBorder(new EmptyBorder(Properties.screenBorder));
        allPanel.add(mainPanel, BorderLayout.CENTER);
        
        nextStackPanel(new LoginPanel(userList).getMainPanel());
        
    }
    
    private void addListeners() {
        buttonBack.addActionListener((ActionEvent e) -> {
            
            backStackPanel();
        });
        
        buttonExit.addActionListener((ActionEvent e) -> {
            int i = JOptionPane.showConfirmDialog(ClientGUI.this, "Are you sure you want to exit program?", "Exit confirm", 0);
            if (i == 0) {
                if (chatGUI != null) {
                    chatGUI = null;
                }
                System.exit(0);
            }
        });
        
        this.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {}

            @Override
            public void windowClosing(WindowEvent e) {
                int i = JOptionPane.showConfirmDialog(ClientGUI.this, "Are you sure you want to exit program?", "Exit confirm", 0);
                if (i == 0) {
                    if (chatGUI != null) {
                        chatGUI = null;
                    }
                    System.exit(0);
                }
            }

            @Override
            public void windowClosed(WindowEvent e) {}

            @Override
            public void windowIconified(WindowEvent e) {}

            @Override
            public void windowDeiconified(WindowEvent e) {}

            @Override
            public void windowActivated(WindowEvent e) {}

            @Override
            public void windowDeactivated(WindowEvent e) {}
        });
    }
    
    public void nextStackPanel(JPanel nextPanel) {
        if (!stackPanel.isEmpty()) {
            mainPanel.remove(stackPanel.peek());
        }
        mainPanel.add(nextPanel);
        stackPanel.push(nextPanel);
        refreshScreen();
    }
    
    public void backStackPanel() {
        if (stackPanel.size() <= 1) {
            return;
        }
        mainPanel.remove(stackPanel.pop());
        mainPanel.add(stackPanel.peek());
        pack();
        repaint();
    }
     
    private void refreshScreen() {
        pack();
    }
    
}

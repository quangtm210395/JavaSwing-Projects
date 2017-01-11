package NotePad;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.SwingUtilities;
import javax.swing.undo.UndoManager;
/**
 *
 * @author Rin's
 */
public class Main extends javax.swing.JFrame {

    private final JPopupMenu popup;
    private File file = null;
    private Font font = null;
    boolean checker = false;
    FindDialog fd = null;
    ReplaceDialog rd = null;
    UndoManager ud = new UndoManager();
    FontChooseDialog fcd;
    JMenuItem undoMenuItem;
    JMenuItem cutMenuItem;
    JMenuItem copyMenuItem;
    JMenuItem pasteMenuItem;
    JMenuItem deleteMenuItem;
    JMenuItem selectAllMenuItem;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Font getF() {
        return font;
    }
    
    public void setF(Font f) {
        this.font = f;
    }
    
    public String getFileContent() {
        BufferedReader br;
        String content = "";
        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            while((line = br.readLine()) != null) {
                content += line + "\n";
            }
        } catch (Exception e) {
        }
        return content;
    }
    
    class MyThread extends Thread {

        @Override
        public void run() {
            try {
                while(true) {
                    lblStatus.setText("Ln " + getLine() + ", Col " + getCol());
                }
            } catch (Exception e) {
            }
        }
    }

    class MyAction implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Undo")) {
                try {
                    ud.undo();
                } catch (Exception a) {
                }
            }
            if (e.getActionCommand().equals("Copy")) {
                try {
                    txtArea.copy();
                } catch (Exception a) {
                }
            }
            if (e.getActionCommand().equals("Cut")) {
                try {
                    txtArea.cut();
                } catch (Exception a) {
                }
            }
            if (e.getActionCommand().equals("Paste")) {
                try {
                    txtArea.paste();
                } catch (Exception a) {
                }
            }
            if (e.getActionCommand().equals("Delete")) {
                try {
                    delete();
                } catch (Exception a) {
                }
            }
            if (e.getActionCommand().equals("Select all")) {
                try {
                    txtArea.selectAll();
                } catch (Exception a) {
                }
            }
        }
    }
    
    public Main() {
        initComponents();

        font = new Font("Consolas", 0, 16);
        txtArea.setFont(font);
        ImageIcon icon = new ImageIcon("notepad.png");
        setIconImage(icon.getImage());
        setTitle("Untitled - Notepad");
        setSize(700, 500);
        setLocation(350, 120);
        
        txtArea.getDocument().addUndoableEditListener(ud);
        
        mniWordWrap.setSelected(false);
        mniStatus.setSelected(true);
        
        lblStatus.setText("Ln " + 1 + ", Col " + 1);
        
        fcd = new FontChooseDialog(this, true);
        rd = new ReplaceDialog(this, false);
        MyThread mt = new MyThread();
        mt.start();
        MyAction ma = new MyAction();
        //create popup menu
        popup = new JPopupMenu();
        undoMenuItem = new JMenuItem("Undo");
        undoMenuItem.setActionCommand("Undo");
        cutMenuItem = new JMenuItem("Cut");
        cutMenuItem.setActionCommand("Cut");
        copyMenuItem = new JMenuItem("Copy");
        copyMenuItem.setActionCommand("Copy");
        pasteMenuItem = new JMenuItem("Paste");
        pasteMenuItem.setActionCommand("Paste");
        deleteMenuItem = new JMenuItem("Delete                            ");
        deleteMenuItem.setActionCommand("Delete");
        selectAllMenuItem = new JMenuItem("Select all");
        selectAllMenuItem.setActionCommand("Select all");
        
        undoMenuItem.addActionListener(ma);
        cutMenuItem.addActionListener(ma);
        copyMenuItem.addActionListener(ma);
        pasteMenuItem.addActionListener(ma);
        deleteMenuItem.addActionListener(ma);
        selectAllMenuItem.addActionListener(ma);
        
        popup.add(undoMenuItem);
        popup.add(new JPopupMenu.Separator());
        popup.add(copyMenuItem);
        popup.add(cutMenuItem);
        popup.add(pasteMenuItem);
        popup.add(deleteMenuItem);
        popup.add(new JPopupMenu.Separator());
        popup.add(selectAllMenuItem);
        
        mniUndo.setEnabled(false);
        mniRedo.setEnabled(false);
        mniCopy.setEnabled(false);
        mniCut.setEnabled(false);
        mniDelete.setEnabled(false);
        mniFind.setEnabled(false);
        mniFindNext.setEnabled(false);

        undoMenuItem.setEnabled(false);
        cutMenuItem.setEnabled(false);
        copyMenuItem.setEnabled(false);
        deleteMenuItem.setEnabled(false);
        selectAllMenuItem.setEnabled(false);
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
        txtArea = new javax.swing.JTextArea();
        pnlStatus = new javax.swing.JPanel();
        lblStatus = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnuFile = new javax.swing.JMenu();
        mniNew = new javax.swing.JMenuItem();
        mniOpen = new javax.swing.JMenuItem();
        mniSave = new javax.swing.JMenuItem();
        mniSaveAs = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        mniExit = new javax.swing.JMenuItem();
        mnuEdit = new javax.swing.JMenu();
        mniUndo = new javax.swing.JMenuItem();
        mniRedo = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        mniCut = new javax.swing.JMenuItem();
        mniCopy = new javax.swing.JMenuItem();
        mniPaste = new javax.swing.JMenuItem();
        mniDelete = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        mniFind = new javax.swing.JMenuItem();
        mniFindNext = new javax.swing.JMenuItem();
        mniReplace = new javax.swing.JMenuItem();
        mniGoto = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        mniSelectAll = new javax.swing.JMenuItem();
        mniTimeDate = new javax.swing.JMenuItem();
        mnuFormat = new javax.swing.JMenu();
        mniWordWrap = new javax.swing.JCheckBoxMenuItem();
        mniFont = new javax.swing.JMenuItem();
        mnuView = new javax.swing.JMenu();
        mniStatus = new javax.swing.JCheckBoxMenuItem();
        mnuHelp = new javax.swing.JMenu();
        mniAbout = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jScrollPane1.setAutoscrolls(true);

        txtArea.setColumns(20);
        txtArea.setFont(new java.awt.Font("Consolas", 0, 16)); // NOI18N
        txtArea.setRows(5);
        txtArea.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 120, 215)));
        txtArea.setPreferredSize(new java.awt.Dimension(1400, 900));
        txtArea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtAreaMouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                txtAreaMouseReleased(evt);
            }
        });
        txtArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtAreaKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(txtArea);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        pnlStatus.setEnabled(false);

        lblStatus.setFont(new java.awt.Font("Arial", 2, 12)); // NOI18N
        lblStatus.setText("jLabel1");

        javax.swing.GroupLayout pnlStatusLayout = new javax.swing.GroupLayout(pnlStatus);
        pnlStatus.setLayout(pnlStatusLayout);
        pnlStatusLayout.setHorizontalGroup(
            pnlStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlStatusLayout.createSequentialGroup()
                .addContainerGap(234, Short.MAX_VALUE)
                .addComponent(lblStatus)
                .addGap(124, 124, 124))
        );
        pnlStatusLayout.setVerticalGroup(
            pnlStatusLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lblStatus, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
        );

        getContentPane().add(pnlStatus, java.awt.BorderLayout.PAGE_END);

        mnuFile.setText("File");

        mniNew.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        mniNew.setText("New");
        mniNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniNewActionPerformed(evt);
            }
        });
        mnuFile.add(mniNew);

        mniOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        mniOpen.setText("Open");
        mniOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniOpenActionPerformed(evt);
            }
        });
        mnuFile.add(mniOpen);

        mniSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        mniSave.setText("Save");
        mniSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniSaveActionPerformed(evt);
            }
        });
        mnuFile.add(mniSave);

        mniSaveAs.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.SHIFT_MASK | java.awt.event.InputEvent.CTRL_MASK));
        mniSaveAs.setText("Save As...");
        mniSaveAs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniSaveAsActionPerformed(evt);
            }
        });
        mnuFile.add(mniSaveAs);
        mnuFile.add(jSeparator1);

        mniExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        mniExit.setText("Exit");
        mniExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniExitActionPerformed(evt);
            }
        });
        mnuFile.add(mniExit);

        jMenuBar1.add(mnuFile);

        mnuEdit.setText("Edit");

        mniUndo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        mniUndo.setText("Undo");
        mniUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniUndoActionPerformed(evt);
            }
        });
        mnuEdit.add(mniUndo);

        mniRedo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        mniRedo.setText("Redo");
        mniRedo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniRedoActionPerformed(evt);
            }
        });
        mnuEdit.add(mniRedo);
        mnuEdit.add(jSeparator2);

        mniCut.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        mniCut.setText("Cut");
        mniCut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniCutActionPerformed(evt);
            }
        });
        mnuEdit.add(mniCut);

        mniCopy.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        mniCopy.setText("Copy");
        mniCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniCopyActionPerformed(evt);
            }
        });
        mnuEdit.add(mniCopy);

        mniPaste.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        mniPaste.setText("Paste");
        mniPaste.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniPasteActionPerformed(evt);
            }
        });
        mnuEdit.add(mniPaste);

        mniDelete.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
        mniDelete.setText("Delete");
        mniDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDeleteActionPerformed(evt);
            }
        });
        mnuEdit.add(mniDelete);
        mnuEdit.add(jSeparator3);

        mniFind.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F, java.awt.event.InputEvent.CTRL_MASK));
        mniFind.setText("Find");
        mniFind.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniFindActionPerformed(evt);
            }
        });
        mnuEdit.add(mniFind);

        mniFindNext.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        mniFindNext.setText("Find Next");
        mniFindNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniFindNextActionPerformed(evt);
            }
        });
        mnuEdit.add(mniFindNext);

        mniReplace.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        mniReplace.setText("Replace");
        mniReplace.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniReplaceActionPerformed(evt);
            }
        });
        mnuEdit.add(mniReplace);

        mniGoto.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        mniGoto.setText("Go to");
        mniGoto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniGotoActionPerformed(evt);
            }
        });
        mnuEdit.add(mniGoto);
        mnuEdit.add(jSeparator4);

        mniSelectAll.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        mniSelectAll.setText("Select all");
        mniSelectAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniSelectAllActionPerformed(evt);
            }
        });
        mnuEdit.add(mniSelectAll);

        mniTimeDate.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        mniTimeDate.setText("Time/Date");
        mniTimeDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniTimeDateActionPerformed(evt);
            }
        });
        mnuEdit.add(mniTimeDate);

        jMenuBar1.add(mnuEdit);

        mnuFormat.setText("Format");

        mniWordWrap.setSelected(true);
        mniWordWrap.setText("Word wrap");
        mniWordWrap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniWordWrapActionPerformed(evt);
            }
        });
        mnuFormat.add(mniWordWrap);

        mniFont.setIcon(new javax.swing.ImageIcon("E:\\Programing\\Java Swing\\Project\\SwingBasic\\icons\\font.png")); // NOI18N
        mniFont.setText("Font");
        mniFont.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniFontActionPerformed(evt);
            }
        });
        mnuFormat.add(mniFont);

        jMenuBar1.add(mnuFormat);

        mnuView.setText("View");

        mniStatus.setSelected(true);
        mniStatus.setText("View Status bar");
        mniStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniStatusActionPerformed(evt);
            }
        });
        mnuView.add(mniStatus);

        jMenuBar1.add(mnuView);

        mnuHelp.setText("Help");

        mniAbout.setText("About Notepad");
        mniAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniAboutActionPerformed(evt);
            }
        });
        mnuHelp.add(mniAbout);

        jMenuBar1.add(mnuHelp);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void open() {
        JFileChooser fc = new JFileChooser();
            fc.setMultiSelectionEnabled(false);
            if (fc.showOpenDialog(Main.this) == JFileChooser.APPROVE_OPTION) {
                txtArea.setText("");
                //get selected file
                file = fc.getSelectedFile();
                if (file != null) {
                    try {
                        LineNumberReader lnr = new LineNumberReader(new FileReader(file));
                        String line;
                        while ((line = lnr.readLine()) != null) {
                            txtArea.append(line + "\n");
                        }
                        lnr.close();
                    } catch (Exception e) {
                        txtArea.setText(e + "");
                    }
                    setTitle(file.getName() + " - NotePad");
                }
            }
    }

    private void mniOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniOpenActionPerformed
        // TODO add your handling code here:
        //open file dialog
        if ((txtArea.getText().equals("") && file == null) || (file != null && getFileContent().equals(txtArea.getText() + "\n"))) {
            open();
            mniFind.setEnabled(true);
        } else {
            SaveConfirm sf = new SaveConfirm(Main.this, true);
            sf.setVisible(true);
        }
    }//GEN-LAST:event_mniOpenActionPerformed

    private void mniExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniExitActionPerformed
        // TODO add your handling code here:
        if ((file != null && !getFileContent().equals(txtArea.getText() + "\n")) || (!txtArea.getText().equals("") && file == null)) {
            SaveConfirm sf = new SaveConfirm(Main.this, true);
            sf.setVisible(true);
        } else
        System.exit(0);
    }//GEN-LAST:event_mniExitActionPerformed

    private void mniFontActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniFontActionPerformed
        fcd.setVisible(true);
        txtArea.setFont(font);
    }//GEN-LAST:event_mniFontActionPerformed

    private void mniSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniSaveActionPerformed
        if (file != null) {
            save();
        } else {
            saveDialog();
        }
    }//GEN-LAST:event_mniSaveActionPerformed

    void save() {
        BufferedWriter bw;
        if (file != null) {
            try {
                bw = new BufferedWriter(new FileWriter(file));
                String s[] = txtArea.getText().split("\\n");
                for (String item : s) {
                    bw.write(item + "\r\n");
                }
                bw.close();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            setTitle(file.getName() + " - Notepad");
        }
    }

    void saveDialog() {
        JFileChooser fc = new JFileChooser();
        BufferedWriter bw;
        File f;
        if (fc.showSaveDialog(Main.this) == JFileChooser.APPROVE_OPTION) {
            try {
                f = fc.getSelectedFile();
                if (!f.exists()) {
                    f.createNewFile();
                    bw = new BufferedWriter(new FileWriter(f));
                    String s[] = txtArea.getText().split("\\n");
                    for (String item : s) {
                        bw.write(item + "\r\n");
                    }
                    bw.close();
                    setTitle(f.getName() + " - Notepad");
                    file = fc.getSelectedFile();
                } else {
                    ImageIcon icon = new ImageIcon("warning.png");
                    int reply = JOptionPane.showConfirmDialog(fc, f.getName() + " already exists.\nDo you want to replace it?", "Confirm Save As", JOptionPane.YES_NO_OPTION, 0, icon);
                    if (reply == JOptionPane.YES_OPTION) {
                        bw = new BufferedWriter(new FileWriter(f));
                        String s[] = txtArea.getText().split("\\n");
                        for (String item : s) {
                            bw.write(item + "\r\n");
                        }
                        bw.close();
                        setTitle(f.getName() + " - Notepad");
                        file = fc.getSelectedFile();
                    } else {
                    }
                }
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }

    private void mniSaveAsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniSaveAsActionPerformed
        // TODO add your handling code here:
        saveDialog();
    }//GEN-LAST:event_mniSaveAsActionPerformed

    private void mniNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniNewActionPerformed
        // TODO add your handling code here:
        if (txtArea.getText().equals("") && file == null) {
            return;
        } else if (file != null && getFileContent().equals(txtArea.getText())){
            txtArea.setText("");
            file = null;
            setTitle("Untitled - Notepad");
        } else {
            SaveConfirm sf = new SaveConfirm(Main.this, true);
            sf.setVisible(true);
        }
    }//GEN-LAST:event_mniNewActionPerformed

    private void mniCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniCopyActionPerformed
        // TODO add your handling code here:
        txtArea.copy();
    }//GEN-LAST:event_mniCopyActionPerformed

    private void mniPasteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniPasteActionPerformed
        // TODO add your handling code here:
        txtArea.paste();
    }//GEN-LAST:event_mniPasteActionPerformed

    private void mniCutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniCutActionPerformed
        // TODO add your handling code here:
        txtArea.cut();
    }//GEN-LAST:event_mniCutActionPerformed

    private void mniSelectAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniSelectAllActionPerformed
        // TODO add your handling code here:
        txtArea.selectAll();
    }//GEN-LAST:event_mniSelectAllActionPerformed

    private void mniDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDeleteActionPerformed
        // TODO add your handling code here:
        delete();
    }//GEN-LAST:event_mniDeleteActionPerformed

    void delete() {
        if (!"".equals(txtArea.getSelectedText())) {
            int start = txtArea.getSelectionStart();
            String s = txtArea.getText();
            String s1 = txtArea.getSelectedText();
            System.out.println(s);
            txtArea.setText(s.substring(0, start) + s.substring(start, s.length()).replace(s1, "") );
        }
    }
    
    private void mniTimeDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniTimeDateActionPerformed
        // TODO add your handling code here:
        Date date = new Date();
        SimpleDateFormat sd = new SimpleDateFormat("mm:ss a MM/dd/yyyy");
        txtArea.append(sd.format(date));
    }//GEN-LAST:event_mniTimeDateActionPerformed

    private void mniReplaceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniReplaceActionPerformed
        // TODO add your handling code here:
        if (!txtArea.getText().equals("")) {
            rd.setVisible(true);
        }
    }//GEN-LAST:event_mniReplaceActionPerformed

    private void mniFindActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniFindActionPerformed
        // TODO add your handling code here:
        if (!txtArea.getText().equals("")) {
            fd = new FindDialog(this, false);
            fd.setVisible(true);
            mniFindNext.setEnabled(true);
        }
    }//GEN-LAST:event_mniFindActionPerformed

    private void mniFindNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniFindNextActionPerformed
        // TODO add your handling code here:
        if (fd != null) {
            fd.find();
        }
    }//GEN-LAST:event_mniFindNextActionPerformed

    private void mniUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniUndoActionPerformed
        // TODO add your handling code here:
        try {
            ud.undo();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_mniUndoActionPerformed

    private void mniRedoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniRedoActionPerformed
        // TODO add your handling code here:
        try {
            ud.redo();
        } catch (Exception e) {
        }
    }//GEN-LAST:event_mniRedoActionPerformed

    private void mniAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniAboutActionPerformed
        // TODO add your handling code here:
        JOptionPane.showMessageDialog(this, "Notepad - Power by Tran Minh Quang ^_^", "About Notepad", 1);
    }//GEN-LAST:event_mniAboutActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        if ((file != null && !getFileContent().equals(txtArea.getText() + "\n")) || (!txtArea.getText().equals("") && file == null)) {
            SaveConfirm sf = new SaveConfirm(Main.this, true);
            sf.setVisible(true);
        } else
        System.exit(0);
    }//GEN-LAST:event_formWindowClosing

    boolean checkDigit(String s) {
        if (s.length() == 0) {
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    
    private void mniGotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniGotoActionPerformed
        // TODO add your handling code here:
        int line = txtArea.getLineCount();
        String input = JOptionPane.showInputDialog(this, "Enter line: ", "Go To", -1).trim();
        if (checkDigit(input)) {
            int lineInp = Integer.parseInt(input);
            int c = 1;
            int index = 0;
            String s = txtArea.getText();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '\n') {
                    c++;
                }
                if (c == lineInp) {
                    index = i+1;
                    break;
                } 
            }
            
            if ( lineInp <= line) {
                if (index == 1) {
                    txtArea.setCaretPosition(index-1);
                } else
                txtArea.setCaretPosition(index);
            } else {
                JOptionPane.showMessageDialog(this, "The line number beyond the total number of lines");
            }
        } else JOptionPane.showMessageDialog(this, "Invalid line!");
    }//GEN-LAST:event_mniGotoActionPerformed

    private void mniWordWrapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniWordWrapActionPerformed
        // TODO add your handling code here:
        if (mniWordWrap.isSelected()) {
            txtArea.setLineWrap(true);
            txtArea.setWrapStyleWord(false);
            
            mniStatus.setEnabled(false);
            mniStatus.setSelected(false);
            pnlStatus.setVisible(false);
            mniGoto.setEnabled(false);
        } else {
            txtArea.setLineWrap(false);
            txtArea.setWrapStyleWord(true);
            
            mniStatus.setEnabled(true);
            mniStatus.setSelected(true);
            pnlStatus.setVisible(true);
            mniGoto.setEnabled(true);
        }
    }//GEN-LAST:event_mniWordWrapActionPerformed

    private void mniStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniStatusActionPerformed
        // TODO add your handling code here:
        if (mniStatus.isSelected()) {
            pnlStatus.setVisible(true);
        } else pnlStatus.setVisible(false);
    }//GEN-LAST:event_mniStatusActionPerformed

    boolean checkKeyCode(int code) {
        int ar[] = {44, 45, 46, 47, 32, 61, 91,92,93,109,110,111,127,192};
        int arrCode[] = new int[27];
        int j = 0;
        for (int i = 96; i < 108; i++) {
            arrCode[j++] = i;
        }
        for (int i = 0; i < ar.length; i++) {
            if (code == ar[i]) {
                return true;
            }
        }
        for (int i = 0; i < arrCode.length; i++) {
            if (code == arrCode[i]) {
                return true;
            }
        }
        return false;
    }
        
    private void txtAreaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtAreaKeyReleased
        // TODO add your handling code here:
        lblStatus.setText("Ln " + getLine() + ", Col " + getCol());
        int code = evt.getKeyCode();
        if (((code >= 48 && code <= 57) || (code >= 65 && code <= 90)) || checkKeyCode(evt.getKeyCode())) {
            mniUndo.setEnabled(true);
            mniRedo.setEnabled(true);
            
            mniFind.setEnabled(true);
            mniFindNext.setEnabled(true);

            undoMenuItem.setEnabled(true);
            
            selectAllMenuItem.setEnabled(true);
        }
        if (code == KeyEvent.VK_SHIFT) {
            if (txtArea.getSelectedText() != null) {
                mniCopy.setEnabled(true);
                mniCut.setEnabled(true);
                mniDelete.setEnabled(true);

                cutMenuItem.setEnabled(true);
                copyMenuItem.setEnabled(true);
                deleteMenuItem.setEnabled(true);
            } else {
                mniCopy.setEnabled(false);
                mniCut.setEnabled(false);
                mniDelete.setEnabled(false);

                cutMenuItem.setEnabled(false);
                copyMenuItem.setEnabled(false);
                deleteMenuItem.setEnabled(false);
            }
        }
    }//GEN-LAST:event_txtAreaKeyReleased

    private void txtAreaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAreaMouseClicked
        // TODO add your handling code here:
        if (SwingUtilities.isRightMouseButton(evt)) {
            popup.show(this, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_txtAreaMouseClicked

    private void txtAreaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtAreaMouseReleased
        // TODO add your handling code here:
        rd.idx = txtArea.getCaretPosition();
        if (txtArea.getSelectedText() != null) {
            mniCopy.setEnabled(true);
            mniCut.setEnabled(true);
            mniDelete.setEnabled(true);

            cutMenuItem.setEnabled(true);
            copyMenuItem.setEnabled(true);
            deleteMenuItem.setEnabled(true);
        } else {
            mniCopy.setEnabled(false);
            mniCut.setEnabled(false);
            mniDelete.setEnabled(false);

            cutMenuItem.setEnabled(false);
            copyMenuItem.setEnabled(false);
            deleteMenuItem.setEnabled(false);
        }
    }//GEN-LAST:event_txtAreaMouseReleased

    int getLine() {
        int line = 1;
        String s = txtArea.getText();
        for (int i = 1; i <= s.length(); i++) {
            if (s.charAt(i - 1) == '\n') {
                line++;
            }
            if (i == txtArea.getCaretPosition()) {
                return line;
            }
        }
        return 1;
    }
    
    int getCol() {
        int indLine = 0;
        String s = txtArea.getText();
        for (int i = 1; i <= s.length(); i++) {
            if (s.charAt(i - 1) == '\n') {
                indLine = i;
            }
            if (i == txtArea.getCaretPosition()) {
                return txtArea.getCaretPosition() - indLine + 1;
            }
        }
        return 1;
    }
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JMenuItem mniAbout;
    private javax.swing.JMenuItem mniCopy;
    private javax.swing.JMenuItem mniCut;
    private javax.swing.JMenuItem mniDelete;
    private javax.swing.JMenuItem mniExit;
    private javax.swing.JMenuItem mniFind;
    private javax.swing.JMenuItem mniFindNext;
    private javax.swing.JMenuItem mniFont;
    private javax.swing.JMenuItem mniGoto;
    private javax.swing.JMenuItem mniNew;
    private javax.swing.JMenuItem mniOpen;
    private javax.swing.JMenuItem mniPaste;
    private javax.swing.JMenuItem mniRedo;
    private javax.swing.JMenuItem mniReplace;
    private javax.swing.JMenuItem mniSave;
    private javax.swing.JMenuItem mniSaveAs;
    private javax.swing.JMenuItem mniSelectAll;
    private javax.swing.JCheckBoxMenuItem mniStatus;
    private javax.swing.JMenuItem mniTimeDate;
    private javax.swing.JMenuItem mniUndo;
    private javax.swing.JCheckBoxMenuItem mniWordWrap;
    private javax.swing.JMenu mnuEdit;
    private javax.swing.JMenu mnuFile;
    private javax.swing.JMenu mnuFormat;
    private javax.swing.JMenu mnuHelp;
    private javax.swing.JMenu mnuView;
    private javax.swing.JPanel pnlStatus;
    javax.swing.JTextArea txtArea;
    // End of variables declaration//GEN-END:variables
}

package com.instantdelay.mpie;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * @author Spencer Van Hoose
 */
public class SelectWorldDialog implements ActionListener, ListSelectionListener, ChangeListener {
   
   private JFrame owner;
   private MinecraftData minecraftData;
   private File selectedDirectory;
   private MinecraftSave selectedSave = null;
   
   // GUI Components
   private javax.swing.JDialog frmWindow;
   private javax.swing.JButton btnBrowse;
   private javax.swing.JButton btnCancel;
   private javax.swing.JButton btnOpenWorld;
   private javax.swing.JScrollPane listScrollPane;
   private javax.swing.JList lstWorlds;
   private javax.swing.JRadioButton optCustomDataLocation;
   private javax.swing.JRadioButton optDefaultDataLocation;
   private javax.swing.ButtonGroup saveLocButtonGroup;
   private javax.swing.JTextField txtCustomDataLocation;
   
   /**
    * Creates a new SelectWorldDialog.
    * 
    * @param owner The owner for the modal dialog.
    * @param data 
    */
   public SelectWorldDialog(JFrame owner, MinecraftData data) {
      this.owner = owner;
      this.minecraftData = data;
      
      setDirectoryToDefault();
      initComponents();
      refreshWorlds();
   }
   
   /**
    * Displays the world select dialog.
    * 
    * @return The world that the user selected or null
    * if the dialog was canceled.
    */
   public MinecraftSave selectWorldLocation() {
      frmWindow.setVisible(true);
      return selectedSave;
   }
   
   private void setDirectoryToDefault() {
      this.selectedDirectory = new File(minecraftData.userDataDirectory, "saves");
   }
   
   private void refreshWorlds() {
      DefaultListModel model = (DefaultListModel)lstWorlds.getModel();
      model.clear();
      
      if (!getSelectedDirectory().exists())
         return;
      
      for (File file : getSelectedDirectory().listFiles()) {
         if (file.isDirectory()) {
            MinecraftSave save = MinecraftSave.preload(file);
            if (save != null) {
               model.addElement(save);
            }
         }
      }
   }

   public void setSelectedDirectory(File value) {
      selectedDirectory = value;
      txtCustomDataLocation.setText(value.getPath());
   }

   public File getSelectedDirectory() {
      return selectedDirectory;
   }
   
   @Override
   public void actionPerformed(ActionEvent e) {
      if (e.getActionCommand().equals("Open")) {
         selectedSave = (MinecraftSave)lstWorlds.getSelectedValue();
         frmWindow.setVisible(false);
      }
      else if (e.getActionCommand().equals("Cancel")) {
         frmWindow.setVisible(false);
      }
      else if (e.getActionCommand().equals("Browse...")) {
         JFileChooser fileDialog = new JFileChooser(getSelectedDirectory());
         fileDialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
         if (fileDialog.showOpenDialog(frmWindow) == JFileChooser.APPROVE_OPTION) {
            setSelectedDirectory(fileDialog.getSelectedFile());
            saveLocButtonGroup.setSelected(optCustomDataLocation.getModel(), true);
         }
      }
   }

   @Override
   public void valueChanged(ListSelectionEvent e) {
      btnOpenWorld.setEnabled(true);
   }
   
   @Override
   public void stateChanged(ChangeEvent e) {
      if (saveLocButtonGroup.getSelection() == optDefaultDataLocation.getModel()) {
         setDirectoryToDefault();
      }
      else {
         setSelectedDirectory(new File(txtCustomDataLocation.getText()));
      }
      refreshWorlds();
   }
   
   private void initComponents() {
      frmWindow = new JDialog(owner, "Load World", true);
      frmWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frmWindow.setMinimumSize(new java.awt.Dimension(363, 281));
      Container content = frmWindow.getContentPane();
      
      saveLocButtonGroup = new javax.swing.ButtonGroup();
      javax.swing.JLabel lblSavedGameLocation = new javax.swing.JLabel();
      javax.swing.JLabel lblWorlds = new javax.swing.JLabel();
      listScrollPane = new javax.swing.JScrollPane();
      lstWorlds = new javax.swing.JList();

      lblSavedGameLocation.setText("Saved game location:");

      optDefaultDataLocation = new javax.swing.JRadioButton("Minecraft user data");
      optDefaultDataLocation.setSelected(true);
      optDefaultDataLocation.addChangeListener(this);
      saveLocButtonGroup.add(optDefaultDataLocation);

      optCustomDataLocation = new javax.swing.JRadioButton("Custom:");
      optCustomDataLocation.addChangeListener(this);
      saveLocButtonGroup.add(optCustomDataLocation);

      btnBrowse = new javax.swing.JButton("Browse...");
      btnBrowse.addActionListener(this);

      txtCustomDataLocation = new javax.swing.JTextField(selectedDirectory.getPath());
      txtCustomDataLocation.setEditable(false);

      lblWorlds.setText("Worlds:");

      lstWorlds.setModel(new DefaultListModel());
      lstWorlds.addListSelectionListener(this);
      listScrollPane.setViewportView(lstWorlds);

      btnCancel = new javax.swing.JButton("Cancel");
      btnCancel.addActionListener(this);

      btnOpenWorld = new javax.swing.JButton("Open");
      btnOpenWorld.addActionListener(this);
      btnOpenWorld.setEnabled(false);

      javax.swing.GroupLayout layout = new javax.swing.GroupLayout(content);
      content.setLayout(layout);
      layout.setHorizontalGroup(
          layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
              .addContainerGap()
              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                  .addComponent(listScrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                  .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                      .addGap(10, 10, 10)
                      .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                          .addGroup(layout.createSequentialGroup()
                              .addComponent(optCustomDataLocation)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                              .addComponent(txtCustomDataLocation, javax.swing.GroupLayout.DEFAULT_SIZE, 187, Short.MAX_VALUE))
                          .addGroup(layout.createSequentialGroup()
                              .addComponent(optDefaultDataLocation)
                              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                      .addComponent(btnBrowse))
                  .addComponent(lblSavedGameLocation, javax.swing.GroupLayout.Alignment.LEADING)
                  .addComponent(lblWorlds, javax.swing.GroupLayout.Alignment.LEADING)
                  .addGroup(layout.createSequentialGroup()
                      .addComponent(btnOpenWorld, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                      .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                      .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
              .addContainerGap())
      );
      
      layout.setVerticalGroup(
          layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(layout.createSequentialGroup()
              .addContainerGap()
              .addComponent(lblSavedGameLocation)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
              .addComponent(optDefaultDataLocation)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                  .addComponent(optCustomDataLocation)
                  .addComponent(txtCustomDataLocation, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(btnBrowse))
              .addGap(18, 18, 18)
              .addComponent(lblWorlds)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
              .addComponent(listScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
              .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
              .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                  .addComponent(btnCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                  .addComponent(btnOpenWorld, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
              .addContainerGap())
      );

      frmWindow.pack();
      frmWindow.setLocationRelativeTo(null);
   }

}

package com.instantdelay.mpie;

import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * The main program window.
 * 
 * @author Spencer Van Hoose
 *
 */
public class MainWindow implements ActionListener {
   
   private JFrame frmWindow;
   private javax.swing.ButtonGroup btgSizeGroup;
   private javax.swing.JButton btnBrowse;
   private javax.swing.JMenu mnuFile;
   private javax.swing.JMenu mnuView;
   private javax.swing.JMenuBar mubMenuBar;
   private javax.swing.JMenuItem muiLoadGame;
   private javax.swing.JMenuItem muiSave;
   private javax.swing.JRadioButtonMenuItem muiSizeLarge;
   private javax.swing.JRadioButtonMenuItem muiSizeMedium;
   private javax.swing.JRadioButtonMenuItem muiSizeSmall;
   private InventoryPanel pnlInventory;
   private javax.swing.JPanel pnlMain;
   private javax.swing.JPanel pnlMissingDataDirectory;
   private javax.swing.JPanel pnlBlank;
   
   private MinecraftSave curWorld = null;
   
   public MinecraftData minecraftData = new MinecraftData();
   
   public MainWindow() {
      initComponents();
      
      File appData = new File(System.getenv("APPDATA"));
      File minecraftDataFolder = new File(appData, ".minecraft");
      loadMinecraftData(minecraftDataFolder);
   }
   
   private void loadMinecraftData(File location) {      
      try {
         minecraftData.load(location);
         
         switchView("blankCard");
         muiLoadGame.setEnabled(true);
         pnlInventory.setMinecraftData(minecraftData);
      }
      catch (MinecraftDataException e) {
         switchView("missingDataCard");
         
         if (pnlInventory.getPreferredSize() != InventoryPanel.MEDIUM_SIZE) {
            pnlInventory.setPreferredSize(InventoryPanel.MEDIUM_SIZE);
            frmWindow.pack();
         }
      }
   }
   
   private void switchView(String card) {
      CardLayout cl = (CardLayout)pnlMain.getLayout();
      cl.show(pnlMain, card);
   }
   
   public void show() {
      frmWindow.setVisible(true);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if (e.getActionCommand().equals("Load game...")) {
         SelectWorldDialog dlg = new SelectWorldDialog(frmWindow, minecraftData);
         curWorld = dlg.selectWorldLocation();
         if (curWorld == null)
            return;
         
         try {
            curWorld.load();
            pnlInventory.setInventory(curWorld.getInventory());
            muiSave.setEnabled(true);
            switchView("inventoryCard");
         }
         catch (MinecraftDataException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(frmWindow,
                  "There was an error loading the selected world:\n\n" + ex.getMessage(),
                  "Load Error", JOptionPane.ERROR_MESSAGE);
         }
      }
      else if (e.getActionCommand().equals("Save") && curWorld != null) {
         curWorld.save();
      }
   }
   
   private void initComponents() {
      frmWindow = new JFrame("Minecraft Inventory Editor");
      frmWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Container content = frmWindow.getContentPane();
      
      pnlMain = new javax.swing.JPanel();
      pnlBlank = new javax.swing.JPanel();
      pnlMissingDataDirectory = new javax.swing.JPanel();
      javax.swing.JPanel pnlCenterMessage = new javax.swing.JPanel();
      javax.swing.JLabel lblDataNotFound = new javax.swing.JLabel();
      btnBrowse = new javax.swing.JButton();
      pnlInventory = new InventoryPanel();

      pnlMain.setLayout(new java.awt.CardLayout());

      // Blank card
      pnlMain.add(pnlBlank, "blankCard");
      
      // Missing data directory card
      pnlMissingDataDirectory.setLayout(new java.awt.GridBagLayout());

      pnlCenterMessage.setLayout(new javax.swing.BoxLayout(pnlCenterMessage, javax.swing.BoxLayout.Y_AXIS));

      lblDataNotFound.setText("The minecraft data directory could not be located.");
      lblDataNotFound.setAlignmentX(Component.CENTER_ALIGNMENT);
      pnlCenterMessage.add(lblDataNotFound);
      pnlCenterMessage.add(Box.createVerticalStrut(5));

      btnBrowse.setText("Browse...");
      btnBrowse.setMaximumSize(new Dimension(100, 30));
      btnBrowse.setPreferredSize(new Dimension(100, 30));
      btnBrowse.setAlignmentX(Component.CENTER_ALIGNMENT);
      pnlCenterMessage.add(btnBrowse);
      pnlCenterMessage.add(Box.createVerticalStrut(10));

      pnlMissingDataDirectory.add(pnlCenterMessage, new java.awt.GridBagConstraints());

      pnlMain.add(pnlMissingDataDirectory, "missingDataCard");
      
      // Inventory view card
      pnlMain.add(pnlInventory, "inventoryCard");

      // Add main content
      content.add(pnlMain, java.awt.BorderLayout.CENTER);
      initMenus();
      
      frmWindow.pack();
      frmWindow.setLocationRelativeTo(null);
   }
   
   private void initMenus() {
      btgSizeGroup = new javax.swing.ButtonGroup();
      mubMenuBar = new javax.swing.JMenuBar();
      mnuFile = new javax.swing.JMenu();
      muiLoadGame = new javax.swing.JMenuItem();
      
      mnuFile.setText("File");

      muiLoadGame.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
      muiLoadGame.setText("Load game...");
      muiLoadGame.addActionListener(this);
      muiLoadGame.setEnabled(false);
      mnuFile.add(muiLoadGame);
      
      muiSave = new javax.swing.JMenuItem("Save");
      muiSave.setEnabled(false);
      muiSave.addActionListener(this);
      mnuFile.add(muiSave);

      mubMenuBar.add(mnuFile);
      
      mnuView = new javax.swing.JMenu("View");

      ActionListener sizeListener = new ActionListener() {
         
         @Override
         public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Small")) {
               pnlInventory.setPreferredSize(InventoryPanel.SMALL_SIZE);
               
               pnlMissingDataDirectory.setPreferredSize(new Dimension(50, 50));
            }
            else if (e.getActionCommand().equals("Large")) {
               pnlInventory.setPreferredSize(InventoryPanel.LARGE_SIZE);
            }
            else {
               pnlInventory.setPreferredSize(InventoryPanel.MEDIUM_SIZE);
            }
            
            frmWindow.pack();
         }
      };
      
      muiSizeSmall = new javax.swing.JRadioButtonMenuItem("Small");
      muiSizeSmall.addActionListener(sizeListener);
      btgSizeGroup.add(muiSizeSmall);
      mnuView.add(muiSizeSmall);

      muiSizeMedium = new javax.swing.JRadioButtonMenuItem("Medium");
      muiSizeMedium.addActionListener(sizeListener);
      btgSizeGroup.add(muiSizeMedium);
      muiSizeMedium.setSelected(true);
      mnuView.add(muiSizeMedium);

      muiSizeLarge = new javax.swing.JRadioButtonMenuItem("Large");
      muiSizeLarge.addActionListener(sizeListener);
      btgSizeGroup.add(muiSizeLarge);
      mnuView.add(muiSizeLarge);
      
      mubMenuBar.add(mnuView);

      frmWindow.setJMenuBar(mubMenuBar);
   }
}

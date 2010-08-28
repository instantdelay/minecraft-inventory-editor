package com.instantdelay.mpie;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JSplitPane;

/**
 * The main program window.
 * 
 * @author Spencer Van Hoose
 *
 */
public class MainWindow {
  
   private JFrame uxWindow;
   private JSplitPane uxSplitPane;
   
   private JList uxSaveList;
   private InventoryPanel uxInventory;
   
   public MinecraftData minecraftData = new MinecraftData();
   
   public MainWindow() {
      initComponents();
   }
   
   private void initComponents() {
      uxWindow = new JFrame("Minecraft Inventory Editor");
      uxWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      uxWindow.setLayout(new BorderLayout());

      DefaultListModel saveListModel = new DefaultListModel();
      for (String save : minecraftData.savedGames) {
         saveListModel.addElement(save);
      }
      uxSaveList = new JList(saveListModel);
      uxInventory = new InventoryPanel(minecraftData);
      
      uxSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true);
      uxSplitPane.setLeftComponent(uxSaveList);
      uxSplitPane.setRightComponent(uxInventory);
      
      uxWindow.add(uxSplitPane, BorderLayout.CENTER);
      
      uxWindow.setPreferredSize(new Dimension(500, 350));
      uxWindow.pack();
      uxWindow.setLocationRelativeTo(null);
   }
   
   public void show() {
      uxWindow.setVisible(true);
   }
}

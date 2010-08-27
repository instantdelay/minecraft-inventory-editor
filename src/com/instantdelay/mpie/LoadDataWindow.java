package com.instantdelay.mpie;

import java.awt.Container;
import java.awt.Font;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SpringLayout;

public class LoadDataWindow {

   private MainWindow mainWindow = new MainWindow();
   
   private JFrame uxWindow;
   private JLabel uxMessage = new JLabel();
   
   public LoadDataWindow() {
      File appData = new File(System.getenv("APPDATA"));
      File minecraftDataFolder = new File(appData, ".minecraft");
      
      if (attemptDataLoad(minecraftDataFolder)) {
         mainWindow.initComponents();
         mainWindow.show();
      }
      else {
         // Ask the user for the .minecraft folder location
         initComponents();
         uxWindow.setVisible(true);
      }
   }
   
   private void initComponents() {
      uxWindow = new JFrame("Locate Minecraft Data");
      uxWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      Container content = uxWindow.getContentPane();
      
      SpringLayout layout = new SpringLayout();
      uxWindow.setLayout(layout);
      
      
      uxWindow.getContentPane().add(uxMessage);
      uxMessage.setFont(new Font("Dialog", Font.PLAIN, 14));
      layout.putConstraint(SpringLayout.NORTH, uxMessage, 8, SpringLayout.NORTH, content);
      layout.putConstraint(SpringLayout.WEST, uxMessage, 8, SpringLayout.WEST, content);
      layout.putConstraint(SpringLayout.EAST, uxMessage, -8, SpringLayout.EAST, content);
      
      
      uxWindow.setSize(450, 300);
      uxWindow.setLocationRelativeTo(null);
   }
   
   private Boolean attemptDataLoad(File location) {
      try {
         mainWindow.minecraftData.load(location);
      }
      catch (MinecraftDataException ex) {
         uxMessage.setText("<html>" + ex.getMessage() +
               " <br><br>If you know the correct location of the data folder," +
               " you can select it yourself:</html>");
         return false;
      }
      return true;
   }
   
}

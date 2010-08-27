package com.instantdelay.mpie;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class AppDriver implements Runnable {

   public static void main(String[] args) {
      SwingUtilities.invokeLater(new AppDriver());
   }

   @Override
   public void run() {
      JFrame mainWindow = new JFrame("Minecraft Inventory Editor");
      mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      
      mainWindow.setSize(500, 350);
      mainWindow.setLocationRelativeTo(null);
      mainWindow.setVisible(true);
   }

}

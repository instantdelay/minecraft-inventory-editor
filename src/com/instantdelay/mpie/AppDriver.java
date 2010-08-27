package com.instantdelay.mpie;

import javax.swing.SwingUtilities;

/**
 * The driver responsible for launching the application.
 * 
 * @author Spencer Van Hoose
 *
 */
public class AppDriver implements Runnable {

   public static MinecraftData minecraftData;
   
   public static void main(String[] args) {
//      try {
//         UIManager.setLookAndFeel(new InfoNodeLookAndFeel());
//      }
//      catch (UnsupportedLookAndFeelException ex) {
//         ex.printStackTrace();
//         // Use the default look and feel
//      }
      
      SwingUtilities.invokeLater(new AppDriver());
   }

   @Override
   public void run() {
      new LoadDataWindow();
   }

}

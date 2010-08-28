package com.instantdelay.mpie;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.jgoodies.looks.windows.WindowsLookAndFeel;

/**
 * The driver responsible for launching the application.
 * 
 * @author Spencer Van Hoose
 *
 */
public class AppDriver implements Runnable {

   public static MinecraftData minecraftData;
   
   public static void main(String[] args) {
      try {
         UIManager.setLookAndFeel(new WindowsLookAndFeel());
      }
      catch (UnsupportedLookAndFeelException ex) {
         ex.printStackTrace();
         // Use the default look and feel
      }
      
      SwingUtilities.invokeLater(new AppDriver());
   }

   @Override
   public void run() {
      SelectFileDialog dlg = new SelectFileDialog();
      dlg.show();
      //new LoadDataWindow();
   }

}

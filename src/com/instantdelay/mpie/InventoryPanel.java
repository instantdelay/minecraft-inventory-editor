package com.instantdelay.mpie;

import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class InventoryPanel extends JPanel {

   private MinecraftData minecraftData;
   
   public InventoryPanel(MinecraftData data) {
      minecraftData = data;
   }
   
   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      g.drawImage(minecraftData.inventoryBg, 0, 0, this.getWidth(), this.getHeight(), this);
   }
   
}

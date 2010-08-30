package com.instantdelay.mpie;

import java.awt.Graphics;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class InventorySlot extends JPanel {

   private byte slot;
   private InventoryEntry item;
   private InventoryPanel parent;
   
   public InventorySlot() {}
   
   public InventorySlot(byte slot) {
      this.slot = slot;
   }
   
   public void setSize(int size) {
      super.setSize(size, size);
   }
   
   public void setSlot(byte slot) {
      this.slot = slot;
   }
   
   public byte getSlot() {
      return slot;
   }

   public void setItem(InventoryEntry item) {
      this.item = item;
      if (item != null)
         item.setSlot(slot);
   }

   public InventoryEntry getItem() {
      return item;
   }

   @Override
   protected void paintComponent(Graphics g) {
      if (item == null || parent.getMinecraftData() == null)
         return;
      
      item.draw(g, this, 0, 0, this.getWidth(), this.getHeight(), parent.getMinecraftData());
   }

   public void setParentInventoryPanel(InventoryPanel parent) {
      this.parent = parent;
   }

   public InventoryPanel getParentInventoryPanel() {
      return parent;
   }
}

package com.instantdelay.mpie;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * Class which handles rendering of the player inventory.
 * 
 * @author Spencer Van Hoose
 *
 */
@SuppressWarnings("serial")
public class InventoryPanel extends JPanel implements
      ComponentListener, MouseMotionListener, MouseListener {

   public static final Dimension SMALL_SIZE = new Dimension(162, 76);
   public static final Dimension MEDIUM_SIZE = new Dimension(324, 152);
   public static final Dimension LARGE_SIZE = new Dimension(486, 228);
   
   private MinecraftData minecraftData;
   private InventorySlot[] slots = new InventorySlot[36];
   private ArrayList<InventoryEntry> inventory;
   
   private InventoryEntry movingItem = null;
   private Point mouseLoc;
   
   public InventoryPanel() {
      setPreferredSize(MEDIUM_SIZE);
      this.addComponentListener(this);
      this.addMouseListener(this);
      this.addMouseMotionListener(this);
      
      for (byte i = 0; i < slots.length; i++) {
         slots[i] = new InventorySlot(i);
         slots[i].setParentInventoryPanel(this);
         add(slots[i]);
      }
      this.setLayout(null);
   }
   
   public MinecraftData getMinecraftData() {
      return minecraftData;
   }
   
   public void setMinecraftData(MinecraftData d) {
      minecraftData = d;
   }
   
   @Override
   protected void paintComponent(Graphics g) {
      super.paintComponent(g);
      
      if (minecraftData == null)
         return;
      
      g.drawImage(minecraftData.inventoryBg, 0, 0, this.getWidth(), this.getHeight(), this);
   }
   
   @Override
   protected void paintChildren(Graphics g) {
      super.paintChildren(g);
      
      if (movingItem != null) {
         double scale = this.getWidth() / 162.0;
         int size = (int)(scale * 16);
         int offset = size / 2;
         
         movingItem.draw(g, this, mouseLoc.x - offset, mouseLoc.y - offset, size, size, minecraftData);
      }
   }

   public void setInventory(ArrayList<InventoryEntry> val) {
      this.inventory = val;
      clearSlots();
      for (InventoryEntry item : val) {
         if (item.getSlot() < slots.length)
            slots[item.getSlot()].setItem(item);
      }
      
      repaint();
   }

   public ArrayList<InventoryEntry> getInventory() {
      return inventory;
   }
   
   private void clearSlots() {
      for (byte i = 0; i < slots.length; i++) {
         slots[i].setItem(null);
      }
   }

   @Override
   public void componentHidden(ComponentEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void componentMoved(ComponentEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void componentResized(ComponentEvent e) {
      
      //
      // Resize and position the inventory slots
      //
      double scale = this.getWidth() / 162.0;
      int i;
      
      // Usable slots
      for (i = 0; i < 9; i++) {
         slots[i].setLocation((int)((18 * i + 1) * scale),
               (int)(59 * scale));
         slots[i].setSize((int)(16 * scale));
      }
      
      // Storage slots
      for (; i < 36; i++) {
         slots[i].setLocation((int)((18 * (i % 9) + 1) * scale),
               (int)((18 * (i / 9 - 1) + 1) * scale));
         slots[i].setSize((int)(16 * scale));
      }
   }

   @Override
   public void componentShown(ComponentEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void mouseDragged(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void mouseMoved(MouseEvent e) {
      if (movingItem != null) {
         mouseLoc = e.getPoint();
         repaint();
      }
   }

   @Override
   public void mouseClicked(MouseEvent e) {
   }

   @Override
   public void mouseEntered(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void mouseExited(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }

   @Override
   public void mousePressed(MouseEvent e) {
      Component c = this.getComponentAt(e.getPoint());
      if (!(c instanceof InventorySlot))
         return;
      
      InventorySlot slot = (InventorySlot) c;
      
      if (movingItem == null) {
         if (slot.getItem() == null) {
            return;
         }
         
         mouseLoc = e.getPoint();
         
         if (e.isControlDown()) {
            try {
               movingItem = (InventoryEntry) slot.getItem().clone();
            }
            catch (CloneNotSupportedException ex) {}
         }
         else if (e.getButton() == MouseEvent.BUTTON1 || slot.getItem().getCount() == 1){
            movingItem = slot.getItem();
            slot.setItem(null);
         }
         else {
            try {
               int count = slot.getItem().getCount();
               movingItem = (InventoryEntry) slot.getItem().clone();
               movingItem.setCount((byte) (count / 2));
               slot.getItem().setCount((byte) (count - movingItem.getCount()));
            }
            catch (CloneNotSupportedException ex) {}
         }
         repaint();
      }
      else {
         // drop item here
         if (slot.getItem() == null || slot.getItem().getId() != movingItem.getId()) {
            InventoryEntry newMovingItem = slot.getItem();
            slot.setItem(movingItem);
            movingItem = newMovingItem;
            repaint();
         }
         else if (slot.getItem().getId() == movingItem.getId()) {
            if (slot.getItem().getCount() == 64) {
               InventoryEntry newMovingItem = slot.getItem();
               slot.setItem(movingItem);
               movingItem = newMovingItem;
            }
            else {
               int newCount = slot.getItem().getCount() + movingItem.getCount();
               
               if (newCount > 64) {
                  slot.getItem().setCount((byte) 64);
                  movingItem.setCount((byte) (newCount - 64));
               }
               else {
                  slot.getItem().setCount((byte) newCount);
                  movingItem = null;
               }
               
               repaint();
            }
         }
      }
   }

   @Override
   public void mouseReleased(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }
}

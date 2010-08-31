package com.instantdelay.mpie;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

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
   }

   @Override
   public void mouseDragged(MouseEvent e) {
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
   }

   @Override
   public void mouseExited(MouseEvent e) {      
   }

   @Override
   public void mousePressed(MouseEvent e) {
      Component c = this.getComponentAt(e.getPoint());
      if (!(c instanceof InventorySlot))
         return;
      
      if (e.getButton() != MouseEvent.BUTTON1) {
         return;
      }
      
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
         else {
            movingItem = slot.getItem();
            inventory.remove(movingItem);
            slot.setItem(null);
         }
         repaint();
      }
      else {
         // dropping an item
         
         if (slot.getItem() == null || slot.getItem() instanceof Item ||
               slot.getItem().getCount() == 64 ||
               slot.getItem().getId() != movingItem.getId()) {
            InventoryEntry newMovingItem = slot.getItem();
            
            slot.setItem(movingItem);
            inventory.add(movingItem);
            
            movingItem = newMovingItem;
            inventory.remove(movingItem);
            repaint();
         }
         else if (slot.getItem().getId() == movingItem.getId()) {
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

   @Override
   public void mouseReleased(MouseEvent e) {
      Component c = this.getComponentAt(e.getPoint());
      if (!(c instanceof InventorySlot))
         return;
      
      final InventorySlot slot = (InventorySlot) c;
      
      if (e.getButton() == MouseEvent.BUTTON3) {
         JPopupMenu menu = new JPopupMenu();
         
         ActionListener l = new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
               if (e.getActionCommand().equals("Clear Slot")) {
                  slot.setItem(null);
                  repaint();
               }
               else if (e.getActionCommand().equals("")) {
                  
               }
            }
         };
         
         JMenuItem title = new JMenuItem();
         title.setEnabled(false);
         menu.add(title);
         
         if (slot.getItem() == null) {
            title.setText("(empty)");
         }
         else {
            title.setText(slot.getItem().getType() == null ? "(unknown)" : slot.getItem().getType().getName());
            JMenuItem clear = new JMenuItem("Clear Slot");
            clear.addActionListener(l);
            menu.add(clear);
         }
         
         menu.show(this, e.getX(), e.getY());
      }
   }
}

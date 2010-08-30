package com.instantdelay.mpie;

import java.awt.Graphics;
import java.awt.image.ImageObserver;

public abstract class InventoryEntry implements Cloneable {

   private byte slot;
   private short id;
   private byte count;
   
   public abstract void draw(Graphics g, ImageObserver observer, int x, int y, int width, int height, MinecraftData data);
   
   public byte getSlot() {
      return slot;
   }
   
   public void setSlot(byte slot) {
      this.slot = slot;
   }

   public void setId(short id) {
      this.id = id;
   }

   public short getId() {
      return id;
   }

   public void setCount(byte count) {
      this.count = count;
   }

   public byte getCount() {
      return count;
   }
   
   @Override
   public Object clone() throws CloneNotSupportedException {
      return super.clone();
   }
}

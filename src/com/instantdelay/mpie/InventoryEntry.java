package com.instantdelay.mpie;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.util.HashMap;
import java.util.Map;

import org.jnbt.ByteTag;
import org.jnbt.CompoundTag;
import org.jnbt.ShortTag;
import org.jnbt.Tag;

public abstract class InventoryEntry implements Cloneable {

   private BlockInfo type;
   private byte slot;
   private short id;
   private byte count;
   
   public CompoundTag toTagData() {
      Map<String, Tag> map = new HashMap<String, Tag>();
      
      map.put("Count", new ByteTag("Count", getCount()));
      map.put("Slot", new ByteTag("Slot", getSlot()));
      map.put("Damage", new ShortTag("Damage", getDamage()));
      map.put("id", new ShortTag("id", getId()));
      
      return new CompoundTag("Item", map);
   }
   
   public abstract void draw(Graphics g, ImageObserver observer, int x, int y, int width, int height, MinecraftData data);
   
   public byte getSlot() {
      return slot;
   }
   
   public void setSlot(byte slot) {
      this.slot = slot;
   }

   public void setId(short id) {
      this.id = id;
      this.type = BlockInfo.get(id);
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
   
   public short getDamage() {
      return 0;
   }
   
   public BlockInfo getType() {
      return type;
   }
   
   @Override
   public Object clone() throws CloneNotSupportedException {
      return super.clone();
   }
}

package com.instantdelay.mpie;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

import org.jnbt.ByteTag;
import org.jnbt.CompoundTag;
import org.jnbt.ShortTag;


/**
 * Represents an inventory item quantity.
 * 
 * @author Spencer Van Hoose
 *
 */
public class Item extends InventoryEntry {
   
   private static final String TAG_ERROR = "Invalid inventory data.";
   
   private short damage;
   
   /**
    * Create a new uninitialized <code>InventoryItem</code>.
    */
   public Item() {}
   
   /**
    * Creates an <code>InventoryItem</code> from a NBT
    * representation of the inventory slot quantity.
    * 
    * @param tag A {@link CompoundTag} containing the inventory item data.
    * @return A new <code>InventoryItem</code>.
    * @throws MinecraftDataException When there is an error reading the
    *    NBT data or it is not in the expected format.
    */
   public static Item fromTagData(CompoundTag tag) throws MinecraftDataException {
      Item item = new Item();
      
//      ByteTag countTag = TagUtils.getByteTag(tag, "Count", TAG_ERROR);
      ByteTag slotTag = TagUtils.getByteTag(tag, "Slot", TAG_ERROR);
      ShortTag damageTag = TagUtils.getShortTag(tag, "Damage", TAG_ERROR);
      ShortTag idTag = TagUtils.getShortTag(tag, "id", TAG_ERROR);
      
      item.setCount((byte) 1);
      item.setSlot(slotTag.getValue());
      item.damage = damageTag.getValue();
      item.setId(idTag.getValue());
      
      return item;
   }

   public void setDamage(short damage) {
      this.damage = damage;
   }

   @Override
   public short getDamage() {
      return damage;
   }
   
   @Override
   public String toString() {
      return String.format("{id: %d, slot: %d, count: %d, damage: %d}", getId(), getSlot(), getCount(), damage);
   }

   @Override
   public void draw(Graphics g, ImageObserver observer, int x, int y, int width, int height, MinecraftData data) {
      BlockInfo type = getType();
      if (type == null) {
         g.setColor(Color.WHITE);
         g.setFont(new Font("Dialog", Font.BOLD, 12));
         g.drawString("?", x + 7, y + 13);
         return;
      }
      
      int spriteX = type.getSprite().x * 16;
      int spriteY = type.getSprite().y * 16;
      
      g.drawImage(data.itemSprites,
            x, y, x + width, y + height, //destination
            spriteX, spriteY, spriteX + 16, spriteY + 16, //source
            observer);
   }
   
   @Override
   public void setCount(byte count) {
      super.setCount(count > 1 ? 1 : count);
   }
}

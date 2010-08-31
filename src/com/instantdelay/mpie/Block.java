package com.instantdelay.mpie;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

import org.jnbt.ByteTag;
import org.jnbt.CompoundTag;
import org.jnbt.ShortTag;

public class Block extends InventoryEntry {
   
   private static final String TAG_ERROR = "Invalid inventory data.";
   
   public Block() {}
   
   /**
    * Creates an <code>InventoryItem</code> from a NBT
    * representation of the inventory slot quantity.
    * 
    * @param tag A {@link CompoundTag} containing the inventory item data.
    * @return A new <code>InventoryItem</code>.
    * @throws MinecraftDataException When there is an error reading the
    *    NBT data or it is not in the expected format.
    */
   public static Block fromTagData(CompoundTag tag) throws MinecraftDataException {
      Block block = new Block();
      
      ByteTag countTag = TagUtils.getByteTag(tag, "Count", TAG_ERROR);
      ByteTag slotTag = TagUtils.getByteTag(tag, "Slot", TAG_ERROR);
//      ShortTag damageTag = TagUtils.getShortTag(tag, "Damage", TAG_ERROR);
      ShortTag idTag = TagUtils.getShortTag(tag, "id", TAG_ERROR);
      
      block.setCount(countTag.getValue());
      block.setSlot(slotTag.getValue());
      block.setId(idTag.getValue());
      
      return block;
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
      
      g.drawImage(data.terrainSprites,
            x, y, x + width, y + height, //destination
            spriteX, spriteY, spriteX + 16, spriteY + 16, //source
            observer);
      g.setColor(Color.WHITE);
      g.drawString("" + getCount(), x + 1, y + 15);
   }
}

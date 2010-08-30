package com.instantdelay.mpie;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

import org.jnbt.ByteTag;
import org.jnbt.CompoundTag;
import org.jnbt.ShortTag;

public class Block extends InventoryEntry {
   
   private static final String TAG_ERROR = "Invalid inventory data.";
   
   private static short[][] spriteLoc = new short[86][2];
   private static short[] allowedBlockIds = new short[] {
      1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
      11, 12, 13, 14, 15, 16, 17, 18, 19, 20,
      35, 37, 38, 39, 40,
      41, 42, 43
   };
   
   static {
      // 0 air
      spriteLoc[1] = new short[] {1,0}; // stone
      spriteLoc[2] = new short[] {0,0}; // grass
      spriteLoc[3] = new short[] {2,0}; // dirt
      spriteLoc[4] = new short[] {0,1}; // cobblestone
      spriteLoc[5] = new short[] {4,1}; // wood
      spriteLoc[6] = new short[] {15,0}; // sapling
      spriteLoc[7] = new short[] {1,1}; // bedrock
      spriteLoc[8] = new short[] {13,12}; // water ??
      spriteLoc[9] = new short[] {13,12}; // stationary water ??
      spriteLoc[10] = new short[] {15,15}; // lava
      spriteLoc[11] = new short[] {15,15}; // stationary lava
      spriteLoc[12] = new short[] {2,1}; // sand
      spriteLoc[13] = new short[] {3,1}; // gravel
      spriteLoc[14] = new short[] {0,2}; // gold ore
      spriteLoc[15] = new short[] {1,2}; // iron ore
      spriteLoc[16] = new short[] {2,2}; // coal ore
      spriteLoc[17] = new short[] {4,0}; // log
      spriteLoc[18] = new short[] {4,3}; // leaves
      spriteLoc[19] = new short[] {0,3}; // sponge
      spriteLoc[20] = new short[] {1,3}; // glass
      // 21-34 cloth colors
      spriteLoc[35] = new short[] {0,4}; // gray cloth
      // 36 white cloth
      spriteLoc[37] = new short[] {13,0}; // yellow flower
      spriteLoc[38] = new short[] {12,0}; // red rose
      spriteLoc[39] = new short[] {13,1}; // brown mushroom
      spriteLoc[40] = new short[] {12,1}; // red mushroom
      spriteLoc[41] = new short[] {7,2}; // gold block
      spriteLoc[42] = new short[] {6,2}; // iron block
      spriteLoc[43] = new short[] {15,8}; // double stair ??
      spriteLoc[44] = new short[] {15,8};
      spriteLoc[45] = new short[] {15,8};
      spriteLoc[46] = new short[] {15,8};
      spriteLoc[47] = new short[] {15,8};
      spriteLoc[48] = new short[] {15,8};
      spriteLoc[49] = new short[] {15,8};
      spriteLoc[50] = new short[] {15,8};
      spriteLoc[51] = new short[] {15,8};
      spriteLoc[52] = new short[] {15,8};
      spriteLoc[53] = new short[] {15,8};
      spriteLoc[54] = new short[] {15,8};
      spriteLoc[55] = new short[] {15,8};
      spriteLoc[56] = new short[] {15,8};
      spriteLoc[57] = new short[] {15,8};
      spriteLoc[58] = new short[] {15,8};
      spriteLoc[59] = new short[] {15,8};
      spriteLoc[60] = new short[] {15,8};
      spriteLoc[61] = new short[] {15,8};
      spriteLoc[62] = new short[] {15,8};
      spriteLoc[63] = new short[] {15,8};
      spriteLoc[64] = new short[] {15,8};
      spriteLoc[65] = new short[] {15,8};
      spriteLoc[66] = new short[] {15,8};
      spriteLoc[67] = new short[] {15,8};
      spriteLoc[68] = new short[] {15,8};
      spriteLoc[69] = new short[] {15,8};
      spriteLoc[70] = new short[] {15,8};
      spriteLoc[71] = new short[] {15,8};
      spriteLoc[72] = new short[] {15,8};
      spriteLoc[73] = new short[] {15,8};
      spriteLoc[74] = new short[] {15,8};
      spriteLoc[75] = new short[] {15,8};
      spriteLoc[76] = new short[] {15,8};
      spriteLoc[77] = new short[] {15,8};
      spriteLoc[78] = new short[] {15,8};
      spriteLoc[79] = new short[] {15,8};
      spriteLoc[80] = new short[] {15,8};
      spriteLoc[81] = new short[] {15,8};
      spriteLoc[82] = new short[] {15,8};
      spriteLoc[83] = new short[] {15,8};
      spriteLoc[84] = new short[] {15,8};
      spriteLoc[85] = new short[] {15,8};
   }
   
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
      short[] loc = spriteLoc[getId()];
      if (loc == null) {
         return;
      }
      
      int spriteX = loc[0] * 16;
      int spriteY = loc[1] * 16;
      
      g.drawImage(data.terrainSprites,
            x, y, x + width, y + height, //destination
            spriteX, spriteY, spriteX + 16, spriteY + 16, //source
            observer);
      g.setColor(Color.WHITE);
      g.drawString("" + getCount(), x + 1, y + 15);
   }
}

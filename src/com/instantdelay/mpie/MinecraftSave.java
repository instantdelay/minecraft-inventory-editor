package com.instantdelay.mpie;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import org.jnbt.CompoundTag;
import org.jnbt.ListTag;
import org.jnbt.NBTInputStream;
import org.jnbt.ShortTag;
import org.jnbt.Tag;

public class MinecraftSave {
   
   private CompoundTag playerTag;
   
   private File location;
   private String worldName;
   private ArrayList<InventoryEntry> inventory;
   
   private boolean loaded = false;
   
   private MinecraftSave(File location, String worldName) {
      this.location = location;
      this.worldName = worldName;
   }
   
   public static MinecraftSave preload(File worldDir) {
      if (!saveAppearsValid(worldDir))
         return null;
      
      return new MinecraftSave(worldDir, worldDir.getName());
   }
   
   public static boolean saveAppearsValid(File worldDir) {
      if (!worldDir.exists())
         return false;
      
      File levelDat = new File(worldDir, "level.dat");
      if (!levelDat.exists() || levelDat.length() <= 0)
         return false;
      
      return true;
   }
   
   public void load() throws MinecraftDataException {
      NBTInputStream nbtStream;
      try {
         nbtStream = new NBTInputStream(new FileInputStream(new File(location, "level.dat")));
      }
      catch (FileNotFoundException ex) {
         throw new MinecraftDataException("Invalid world. Missing level.dat.", ex);
      }
      catch (IOException ex) {
         throw new MinecraftDataException("Invalid world. An error occurred opening the level data.", ex);
      }
      
      CompoundTag root;
      try {
         root = (CompoundTag)nbtStream.readTag();
      }
      catch (IOException ex) {
         throw new MinecraftDataException("Invalid game file. An error occurred reading the level data.", ex);
      }
      
      CompoundTag dataTag = TagUtils.getCompoundTag(root, "Data", "Missing Data tag or wrong Data tag format.");
      playerTag = TagUtils.getCompoundTag(dataTag, "Player", "Missing Player tag or wrong Player tag format.");
      ListTag inventoryTag = TagUtils.getListTag(playerTag, "Inventory", "Missing Inventory tag or wrong Inventory tag format.");
      
      inventory = new ArrayList<InventoryEntry>();
      for (Tag inventoryItemRawTag : inventoryTag.getValue()) {
         if (!(inventoryItemRawTag instanceof CompoundTag)) {
            throw new MinecraftDataException("Inventory item tag with wrong type.");
         }
         
         InventoryEntry entry;
         ShortTag idTag = TagUtils.getShortTag((CompoundTag)inventoryItemRawTag, "id", "Invalid inventory item.");
         if (idTag.getValue() > 255) {
            entry = Item.fromTagData((CompoundTag)inventoryItemRawTag);
         }
         else {
            entry = Block.fromTagData((CompoundTag)inventoryItemRawTag);
         }

         inventory.add(entry);
      }
      
      loaded = true;
   }
   
   public boolean isLoaded() {
      return loaded;
   }
   
   @Override
   public String toString() {
      return worldName;
   }

   public ArrayList<InventoryEntry> getInventory() {
      return inventory;
   }
   
}

package com.instantdelay.mpie;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.zip.ZipFile;

import javax.imageio.ImageIO;

/**
 * Class responsible for loading resources from the
 * Minecraft user data directory.
 * 
 * @author Spencer Van Hoose
 *
 */
public class MinecraftData {

   public File userDataDirectory;
   public BufferedImage itemSprites;
   public BufferedImage terrainSprites;
   public Image inventoryBg;
   public ArrayList<String> savedGames = new ArrayList<String>(); //change to SavedGame[] class or w/e
   
   private boolean initialized = false;
   
   public MinecraftData() {
   }
   
   public boolean isInitialized() {
      return initialized;
   }
   
   public void load(File location) throws MinecraftDataException {
      String checkInstallMsg = "Please make sure that you have Minecraft installed.";
      
      if (!location.exists()) {
         throw new MinecraftDataException("MPIE was unable to find the Minecraft data " +
               "in its default location. " + checkInstallMsg);
      }
      
      File minecraftJar = new File(location, "bin" + File.separator + "minecraft.jar");
      if (!minecraftJar.exists()) {
         throw new MinecraftDataException("The Minecraft jar file could not be found " + 
               "in the default Minecraft data folder. " + checkInstallMsg);
      }
      
      userDataDirectory = location;
      ZipFile zip;
      
      try {
         zip = new ZipFile(minecraftJar);
      }
      catch (Exception ex) {
         throw new MinecraftDataException("There was an error reading the Minecraft jar. " +
               checkInstallMsg);
      }
      
      try {
         terrainSprites = ImageIO.read(zip.getInputStream(zip.getEntry("terrain.png")));
      }
      catch (Exception ex) {
         throw new MinecraftDataException("The terrain sprites could not be found in the " +
               "Minecraft jar. MPIE may need to be updated if they have moved.");
      }
      
      try {
         itemSprites = ImageIO.read(zip.getInputStream(zip.getEntry("gui/items.png")));
      }
      catch (Exception ex) {
         throw new MinecraftDataException("The item sprites could not be found in the " +
         "Minecraft jar. MPIE may need to be updated if they have moved.");
      }
      
      try {
         BufferedImage fullInventoryBg = ImageIO.read(zip.getInputStream(zip.getEntry("gui/inventory.png")));
         inventoryBg = fullInventoryBg.getSubimage(7, 83, 162, 76).getScaledInstance(162, 76, BufferedImage.SCALE_SMOOTH);
      }
      catch (Exception ex) {
         throw new MinecraftDataException("The inventory image could not be found in the " +
         "Minecraft jar. MPIE may need to be updated if they have moved.");
      }
      
      initialized = true;
   }
   
}

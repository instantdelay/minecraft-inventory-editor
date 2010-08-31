package com.instantdelay.mpie;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class BlockInfo {

   private static Map<Integer, BlockInfo> blocks = new HashMap<Integer, BlockInfo>();
   
   static {
      blocks.put(1, new BlockInfo("Stone", new Point(1, 0)));
      blocks.put(2, new BlockInfo("Grass", new Point(0, 0)));
      blocks.put(3, new BlockInfo("Dirt", new Point(2, 0)));
      blocks.put(4, new BlockInfo("Cobblestone", new Point(0, 1)));
      blocks.put(5, new BlockInfo("Wood", new Point(4, 1)));
      blocks.put(6, new BlockInfo("Sapling", new Point(15, 0)));
      blocks.put(12, new BlockInfo("Sand", new Point(2, 1)));
      blocks.put(13, new BlockInfo("Gravel", new Point(3, 1)));
      blocks.put(14, new BlockInfo("Gold ore", new Point(0, 2)));
      blocks.put(15, new BlockInfo("Iron ore", new Point(1, 2)));
      blocks.put(16, new BlockInfo("Coal ore", new Point(2, 2)));
      blocks.put(17, new BlockInfo("Log", new Point(4, 0)));
      blocks.put(18, new BlockInfo("Leaves", new Point(4, 3)));
      blocks.put(19, new BlockInfo("Sponge", new Point(0, 3)));
      blocks.put(20, new BlockInfo("Glass", new Point(1, 3)));
      blocks.put(35, new BlockInfo("White Cloth", new Point(0, 4)));
      blocks.put(37, new BlockInfo("Yellow flower", new Point(13, 0)));
      blocks.put(38, new BlockInfo("Red rose", new Point(12, 0)));
      blocks.put(39, new BlockInfo("Brown Mushroom", new Point(13, 1)));
      blocks.put(40, new BlockInfo("Red Mushroom", new Point(12, 1)));
      blocks.put(41, new BlockInfo("Gold Block", new Point(7, 2)));
      blocks.put(42, new BlockInfo("Iron Block", new Point(6, 2)));
      blocks.put(45, new BlockInfo("Brick", new Point(7, 0)));
      blocks.put(46, new BlockInfo("TNT", new Point(8, 0)));
      blocks.put(47, new BlockInfo("Bookcase", new Point(3, 2)));
      blocks.put(48, new BlockInfo("Mossy Cobblestone", new Point(4, 2)));
      blocks.put(49, new BlockInfo("Obsidian", new Point(5, 2)));
      blocks.put(50, new BlockInfo("Torch", new Point(0, 5)));
      blocks.put(54, new BlockInfo("Chest", new Point(11, 1)));
      blocks.put(55, new BlockInfo("Redstone Wire", new Point(4, 6)));
      blocks.put(56, new BlockInfo("Diamond Ore", new Point(2, 3)));
      blocks.put(57, new BlockInfo("Diamond Block", new Point(8, 2)));
      blocks.put(58, new BlockInfo("Workbench", new Point(11, 2)));
      blocks.put(61, new BlockInfo("Furnace", new Point(12, 2)));
      blocks.put(65, new BlockInfo("Ladder", new Point(3, 5)));
      blocks.put(66, new BlockInfo("Minecart Rail", new Point(0, 8)));
      blocks.put(69, new BlockInfo("Lever", new Point(0, 6)));
      blocks.put(70, new BlockInfo("Stone Pressure Plate", new Point(5, 0)));
      blocks.put(73, new BlockInfo("Redstone Ore", new Point(3, 3)));
      blocks.put(75, new BlockInfo("Redstone torch (off state)", new Point(3, 7)));
      blocks.put(76, new BlockInfo("Redstone torch (on state)", new Point(3, 6)));
      blocks.put(81, new BlockInfo("Cactus", new Point(5, 4)));
      
      // -------------------------------------------------------------------------
      
      blocks.put(256, new BlockInfo("Iron Spade", new Point(2, 5)));
      blocks.put(257, new BlockInfo("Iron Pickaxe", new Point(2, 6)));
      blocks.put(258, new BlockInfo("Iron Axe", new Point(2, 7)));
      blocks.put(259, new BlockInfo("Flint and Steel", new Point(5, 0)));
      blocks.put(260, new BlockInfo("Apple", new Point(11, 0)));
      blocks.put(261, new BlockInfo("Bow", new Point(5, 1)));
      blocks.put(262, new BlockInfo("Arrow", new Point(5, 2)));
      blocks.put(263, new BlockInfo("Coal", new Point(7, 0)));
      blocks.put(264, new BlockInfo("Diamond", new Point(7, 3)));
      blocks.put(265, new BlockInfo("Iron Ingot", new Point(7, 1)));
      blocks.put(266, new BlockInfo("Gold Ingot", new Point(7, 2)));
      blocks.put(267, new BlockInfo("Iron Sword", new Point(2, 4)));
      blocks.put(268, new BlockInfo("Wooden Sword", new Point(0, 4)));
      blocks.put(269, new BlockInfo("Wooden Spade", new Point(0, 5)));
      blocks.put(270, new BlockInfo("Wooden Pickaxe", new Point(0, 6)));
      blocks.put(271, new BlockInfo("Wooden Axe", new Point(0, 7)));
      blocks.put(272, new BlockInfo("Stone Sword", new Point(1, 4)));
      blocks.put(273, new BlockInfo("Stone Spade", new Point(1, 5)));
      blocks.put(274, new BlockInfo("Stone Pickaxe", new Point(1, 6)));
      blocks.put(275, new BlockInfo("Stone Axe", new Point(1, 7)));
      blocks.put(276, new BlockInfo("Diamond Sword", new Point(3, 4)));
      blocks.put(277, new BlockInfo("Diamond Spade", new Point(3, 5)));
      blocks.put(278, new BlockInfo("Diamond Pickaxe", new Point(3, 6)));
      blocks.put(279, new BlockInfo("Diamond Axe", new Point(3, 7)));
      blocks.put(280, new BlockInfo("Stick", new Point(5, 3)));
      blocks.put(281, new BlockInfo("Bowl", new Point(7, 4)));
      blocks.put(282, new BlockInfo("Mushroom Soup", new Point(8, 4)));
      blocks.put(283, new BlockInfo("Gold Sword", new Point(4, 4)));
      blocks.put(284, new BlockInfo("Gold Spade", new Point(4, 5)));
      blocks.put(285, new BlockInfo("Gold Pickaxe", new Point(4, 6)));
      blocks.put(286, new BlockInfo("Gold Axe", new Point(4, 7)));
      blocks.put(287, new BlockInfo("String", new Point(8, 0)));
      blocks.put(288, new BlockInfo("Feather", new Point(8, 1)));
      blocks.put(289, new BlockInfo("Gunpowder", new Point(8, 2)));
      blocks.put(290, new BlockInfo("Wooden Hoe", new Point(0, 8)));
      blocks.put(291, new BlockInfo("Stone Hoe", new Point(1, 8)));
      blocks.put(292, new BlockInfo("Iron Hoe", new Point(2, 8)));
      blocks.put(293, new BlockInfo("Diamond Hoe", new Point(3, 8)));
      blocks.put(294, new BlockInfo("Gold Hoe", new Point(4, 8)));
      blocks.put(295, new BlockInfo("Seeds", new Point(9, 0)));
      blocks.put(296, new BlockInfo("Wheat", new Point(9, 1)));
      blocks.put(297, new BlockInfo("Bread", new Point(9, 2)));
      blocks.put(298, new BlockInfo("Leather Helmet", new Point(0, 0)));
      blocks.put(299, new BlockInfo("Leather Chestplate", new Point(0, 1)));
      blocks.put(300, new BlockInfo("Leather Pants", new Point(0, 2)));
      blocks.put(301, new BlockInfo("Leather Boots", new Point(0, 3)));
      blocks.put(302, new BlockInfo("Chainmail Helmet", new Point(1, 0)));
      blocks.put(303, new BlockInfo("Chainmail Chestplate", new Point(1, 1)));
      blocks.put(304, new BlockInfo("Chainmail Pants", new Point(1, 2)));
      blocks.put(305, new BlockInfo("Chainmail Boots", new Point(1, 3)));
      blocks.put(306, new BlockInfo("Iron Helmet", new Point(2, 0)));
      blocks.put(307, new BlockInfo("Iron Chestplate", new Point(2, 1)));
      blocks.put(308, new BlockInfo("Iron Pants", new Point(2, 2)));
      blocks.put(309, new BlockInfo("Iron Boots", new Point(2, 3)));
      blocks.put(310, new BlockInfo("Diamond Helmet", new Point(3, 0)));
      blocks.put(311, new BlockInfo("Diamond Chestplate", new Point(3, 1)));
      blocks.put(312, new BlockInfo("Diamond Pants", new Point(3, 2)));
      blocks.put(313, new BlockInfo("Diamond Boots", new Point(3, 3)));
      blocks.put(314, new BlockInfo("Gold Helmet", new Point(4, 0)));
      blocks.put(315, new BlockInfo("Gold Chestplate", new Point(4, 1)));
      blocks.put(316, new BlockInfo("Gold Pants", new Point(4, 2)));
      blocks.put(317, new BlockInfo("Gold Boots", new Point(4, 3)));
      blocks.put(318, new BlockInfo("Flint", new Point(6, 0)));
      blocks.put(319, new BlockInfo("Pork", new Point(7, 5)));
      blocks.put(320, new BlockInfo("Grilled Pork", new Point(8, 5)));
      blocks.put(321, new BlockInfo("Paintings", new Point(10, 1)));
      blocks.put(322, new BlockInfo("Golden apple", new Point(11, 0)));
      blocks.put(323, new BlockInfo("Sign", new Point(10, 2)));
      blocks.put(324, new BlockInfo("Wooden door", new Point(11, 2)));
      blocks.put(325, new BlockInfo("Bucket", new Point(10, 4)));
      blocks.put(326, new BlockInfo("Water bucket", new Point(11, 4)));
      blocks.put(327, new BlockInfo("Lava bucket", new Point(12, 4)));
      blocks.put(328, new BlockInfo("Mine cart", new Point(7, 8)));
      blocks.put(329, new BlockInfo("Saddle", new Point(8, 6)));
      blocks.put(330, new BlockInfo("Iron door", new Point(12, 2)));
      blocks.put(331, new BlockInfo("Redstone", new Point(8, 3)));
      blocks.put(332, new BlockInfo("Snowball", new Point(14, 0)));
      blocks.put(333, new BlockInfo("Boat", new Point(8, 8)));
      blocks.put(334, new BlockInfo("Leather", new Point(7, 6)));
      blocks.put(335, new BlockInfo("Milk Bucket", new Point(13, 4)));
      blocks.put(336, new BlockInfo("Clay Brick", new Point(6, 1)));
      blocks.put(337, new BlockInfo("Clay Balls", new Point(9, 3)));
      blocks.put(338, new BlockInfo("Papyrus", new Point(11, 1)));
      blocks.put(339, new BlockInfo("Paper", new Point(10, 3)));
      blocks.put(340, new BlockInfo("Book", new Point(11, 3)));
      blocks.put(341, new BlockInfo("Slime Ball", new Point(14, 1)));
      blocks.put(342, new BlockInfo("Storage Minecart", new Point(7, 8)));
      blocks.put(343, new BlockInfo("Powered Minecart", new Point(7, 10)));
      blocks.put(344, new BlockInfo("Egg", new Point(12, 0)));
      blocks.put(2256, new BlockInfo("Gold Record", new Point(0, 15)));
      blocks.put(2257, new BlockInfo("Green Record", new Point(1, 15)));

   }
   
   private String name;
   private Point sprite;
   
   public BlockInfo(String name, Point sprite) {
      this.name = name;
      this.sprite = sprite;
   }

   public Point getSprite() {
      return sprite;
   }

   public String getName() {
      return name;
   }
   
   public static BlockInfo get(int id) {
      return blocks.get(id);
   }
}

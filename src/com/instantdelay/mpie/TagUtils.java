package com.instantdelay.mpie;

import org.jnbt.ByteTag;
import org.jnbt.CompoundTag;
import org.jnbt.IntTag;
import org.jnbt.ListTag;
import org.jnbt.ShortTag;
import org.jnbt.Tag;

public class TagUtils {
   
   private TagUtils() {}
   
   public static CompoundTag getCompoundTag(CompoundTag parent, String name, String messageIfError) throws MinecraftDataException {
      return TagUtils.<CompoundTag>getTypedTag(parent, name, CompoundTag.class, messageIfError);
   }
   
   public static ListTag getListTag(CompoundTag parent, String name, String messageIfError) throws MinecraftDataException {
      return TagUtils.<ListTag>getTypedTag(parent, name, ListTag.class, messageIfError);
   }
   
   public static IntTag getIntTag(CompoundTag parent, String name, String messageIfError) throws MinecraftDataException {
      return TagUtils.<IntTag>getTypedTag(parent, name, IntTag.class, messageIfError);
   }
   
   public static ByteTag getByteTag(CompoundTag parent, String name, String messageIfError) throws MinecraftDataException {
      return TagUtils.<ByteTag>getTypedTag(parent, name, ByteTag.class, messageIfError);
   }
   
   public static ShortTag getShortTag(CompoundTag parent, String name, String messageIfError) throws MinecraftDataException {
      return TagUtils.<ShortTag>getTypedTag(parent, name, ShortTag.class, messageIfError);
   }
   
   @SuppressWarnings("unchecked")
   public static <T extends Tag> T getTypedTag(CompoundTag parent, String name, Class<? extends Tag> type, String messageIfError) throws MinecraftDataException {
      Tag tag = parent.getValue().get(name);
      if (tag == null || tag.getClass() != type)
         throw new MinecraftDataException(messageIfError);
      
      return (T)tag;
   }
   
}

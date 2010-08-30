package com.instantdelay.mpie;

@SuppressWarnings("serial")
public class MinecraftDataException extends Exception {

   public MinecraftDataException(String message) {
      super(message);
   }
   
   public MinecraftDataException(String message, Throwable cause) {
      super(message, cause);
   }
   
}

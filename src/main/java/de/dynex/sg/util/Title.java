package de.dynex.sg.util;

import java.lang.reflect.Constructor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Title {
  public Title() {}

  public static String getVersion() {
    return Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
  }

  public static Class<?> getNMSClass(String name) {
    try {
      return Class.forName("net.minecraft.server." + getVersion() + "." + name);
    } catch (ClassNotFoundException ex) {
      ex.printStackTrace();
    }
    return null;
  }

  public static void sendPacket(
    Player player,
    Object packet
  ) {
    try {
      Object handle = player.getClass().getMethod("getHandle", new Class[0])
          .invoke(player, new Object[0]);
      Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
      playerConnection.getClass().getMethod("sendPacket", new Class[]
          {
              getNMSClass("Packet")
          }).invoke(playerConnection, new Object[]
          {
              packet
          });
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }

  @SuppressWarnings({"rawtypes", "unchecked"})
  public static void sendActionBar(
    Player player,
    String message
  ) {
    try {
      Class c_craftplayer = Class
          .forName("org.bukkit.craftbukkit." + getVersion() + ".entity.CraftPlayer");
      Object cp = c_craftplayer.cast(player);
      Object packet;
      String ver = getVersion();
      if (((ver.equalsIgnoreCase("v1_8_R1")) || (!ver.startsWith("v1_8_"))) && (!ver
          .startsWith("v1_9_"))) {
        Object comp = getNMSClass("IChatBaseComponent").cast(
            getNMSClass("ChatSerializer").getDeclaredMethod("a", new Class[]{String.class})
                .invoke(getNMSClass("ChatSerializer"),
                    new Object[]{"{\"text\": \"" + message + "\"}"}));
        packet = getNMSClass("PacketPlayOutChat").getConstructor(new Class[]
            {
                getNMSClass("IChatBaseComponent"), Byte.TYPE
            }).newInstance(new Object[]
            {
                comp, Byte.valueOf((byte) 2)
            });
      } else {
        Object o = getNMSClass("ChatComponentText").getConstructor(new Class[]
            {
                String.class
            }).newInstance(new Object[]
            {
              message
            });
        packet = getNMSClass("PacketPlayOutChat").getConstructor(new Class[]
            {
                getNMSClass("IChatBaseComponent"), Byte.TYPE
            }).newInstance(new Object[]
            {
                o, Byte.valueOf((byte) 2)
            });
      }
      Object handle = c_craftplayer.getDeclaredMethod("getHandle", new Class[0])
          .invoke(cp, new Object[0]);
      Object pc = handle.getClass().getDeclaredField("playerConnection").get(handle);
      pc.getClass().getDeclaredMethod("sendPacket", new Class[]
          {
              getNMSClass("Packet")
          }).invoke(pc, new Object[]
          {
              packet
          });
    } catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}

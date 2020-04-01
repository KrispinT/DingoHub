package me.krispin.hub.npc;


import me.krispin.hub.ConfigManager;


import me.krispin.hub.Hub;
import me.krispin.hub.utils.Utils;

import net.minecraft.server.v1_7_R4.*;
import net.minecraft.util.com.mojang.authlib.GameProfile;
import org.bukkit.Bukkit;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_7_R4.CraftServer;
import org.bukkit.craftbukkit.v1_7_R4.CraftWorld;
import org.bukkit.craftbukkit.v1_7_R4.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.HashMap;
import java.util.Map;


public class NPCs implements Listener {

        Map<String, Integer> entityId = new HashMap<>();

        @EventHandler
        public void addNPCs(PlayerJoinEvent e){
                final Player p = e.getPlayer();

                MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
                WorldServer world = ((CraftWorld) Bukkit.getWorlds().get(0)).getHandle();
                GameProfile profile = new GameProfile(p.getUniqueId(), Utils.color("&fClick to join!"));

                PlayerConnection connection = ((CraftPlayer)p).getHandle().playerConnection;

                //kitmap
                EntityPlayer kitmap = new EntityPlayer(server, world, profile, new PlayerInteractManager(world));
                Player kitmapP = kitmap.getBukkitEntity().getPlayer();
                entityId.put("kitmap",kitmapP.getEntityId());
                kitmapP.setPlayerListName("");

                double x = ConfigManager.getManager().getConfig().getDouble("KitmapNPC.x");
                double y = ConfigManager.getManager().getConfig().getDouble("KitmapNPC.y");
                double z = ConfigManager.getManager().getConfig().getDouble("KitmapNPC.z");
                String yaw =  ConfigManager.getManager().getConfig().getString("KitmapNPC.yaw");
                String pitch = ConfigManager.getManager().getConfig().getString("KitmapNPC.pitch");

                float yawFloat = Float.valueOf(yaw);
                float pitchFloat = Float.valueOf(pitch);

                kitmap.setLocation(x, y, z, yawFloat, pitchFloat);

                PacketPlayOutEntityHeadRotation kitmapRotation = new PacketPlayOutEntityHeadRotation(kitmap, (byte) ((yawFloat * 256.0F) / 360.0F));

                connection.sendPacket(new PacketPlayOutPlayerInfo().addPlayer(kitmap));
                connection.sendPacket(new PacketPlayOutNamedEntitySpawn(kitmap));
                connection.sendPacket(kitmapRotation);

                //squads
                EntityPlayer squads = new EntityPlayer(server, world, profile, new PlayerInteractManager(world));
                Player squadsP =  squads.getBukkitEntity().getPlayer();
                entityId.put("squads", squadsP.getEntityId());
                squadsP.setPlayerListName("");

                double sx = ConfigManager.getManager().getConfig().getDouble("SquadsNPC.x");
                double sy = ConfigManager.getManager().getConfig().getDouble("SquadsNPC.y");
                double sz = ConfigManager.getManager().getConfig().getDouble("SquadsNPC.z");
                String syaw =  ConfigManager.getManager().getConfig().getString("SquadsNPC.yaw");
                String spitch = ConfigManager.getManager().getConfig().getString("SquadsNPC.pitch");

                float syawFloat = Float.valueOf(syaw);
                float spitchFloat = Float.valueOf(spitch);

                squads.setLocation(sx, sy, sz, syawFloat, spitchFloat);

                PacketPlayOutEntityHeadRotation squadsRotation = new PacketPlayOutEntityHeadRotation(squads, (byte) ((syawFloat * 256.0F) / 360.0F));

                connection.sendPacket(new PacketPlayOutPlayerInfo().addPlayer(squads));
                connection.sendPacket(new PacketPlayOutNamedEntitySpawn(squads));
                connection.sendPacket(squadsRotation);

                //teams
                EntityPlayer teams = new EntityPlayer(server, world, profile, new PlayerInteractManager(world));
                Player teamsP = teams.getBukkitEntity().getPlayer();
                entityId.put("teams", teamsP.getEntityId());
                teamsP.setPlayerListName("");

                double tx = ConfigManager.getManager().getConfig().getDouble("TeamsNPC.x");
                double ty = ConfigManager.getManager().getConfig().getDouble("TeamsNPC.y");
                double tz = ConfigManager.getManager().getConfig().getDouble("TeamsNPC.z");
                String tyaw =  ConfigManager.getManager().getConfig().getString("TeamsNPC.yaw");
                String tpitch = ConfigManager.getManager().getConfig().getString("TeamsNPC.pitch");

                float tyawFloat = Float.valueOf(tyaw);
                float tpitchFloat = Float.valueOf(tpitch);

                teams.setLocation(tx, ty, tz, tyawFloat, tpitchFloat);

                PacketPlayOutEntityHeadRotation teamsRotation = new PacketPlayOutEntityHeadRotation(teams, (byte) (tyawFloat * 256.0F / 360.0F));

                connection.sendPacket(new PacketPlayOutPlayerInfo().addPlayer(teams));
                connection.sendPacket(new PacketPlayOutNamedEntitySpawn(teams));
                connection.sendPacket(teamsRotation);
        }

        @EventHandler
        public void onNPCInteract(PlayerInteractEntityEvent e){
                Player p = e.getPlayer();
                int name = e.getRightClicked().getEntityId();

                System.out.println(name);

                //kitmap
                if(name == entityId.get("kitmap")){
                        try{
                                ByteArrayOutputStream b = new ByteArrayOutputStream();
                                DataOutputStream out = new DataOutputStream(b);
                                out.writeUTF("Connect");
                                out.writeUTF("kitmap");
                                p.sendPluginMessage(Hub.getPlugin(Hub.class), "BungeeCord", b.toByteArray());
                                b.close();
                                out.close();
                        }catch(Exception ex){
                                p.sendMessage(ChatColor.RED+"Error when trying to connect to: "+ ChatColor.WHITE + "kitmap");
                        }
                }

                //squads
                else if(name == entityId.get("squads")){
                        try{
                                ByteArrayOutputStream b = new ByteArrayOutputStream();
                                DataOutputStream out = new DataOutputStream(b);
                                out.writeUTF("Connect");
                                out.writeUTF("squads");
                                p.sendPluginMessage(Hub.getPlugin(Hub.class), "BungeeCord", b.toByteArray());
                                b.close();
                                out.close();
                        }catch(Exception ex){
                                p.sendMessage(ChatColor.RED+"Error when trying to connect to: "+ ChatColor.WHITE + "squads");
                        }
                }

                //teams
                else if(name == entityId.get("teams")){
                        try{
                                ByteArrayOutputStream b = new ByteArrayOutputStream();
                                DataOutputStream out = new DataOutputStream(b);
                                out.writeUTF("Connect");
                                out.writeUTF("teams");
                                p.sendPluginMessage(Hub.getPlugin(Hub.class), "BungeeCord", b.toByteArray());
                                b.close();
                                out.close();
                        }catch(Exception ex){
                                p.sendMessage(ChatColor.RED+"Error when trying to connect to: "+ ChatColor.WHITE + "teams");
                        }
                }else{
                        return;
                }


        }

        public void getForceSpawn(Player p, double x, double y, double z, float yaw, float pitch){
                MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
                WorldServer world = ((CraftWorld) Bukkit.getWorlds().get(0)).getHandle();
                GameProfile profile = new GameProfile(p.getUniqueId(), Utils.color("&fClick to Join!"));

                PlayerConnection connection = ((CraftPlayer)p).getHandle().playerConnection;

                //kitmap
                EntityPlayer kitmap = new EntityPlayer(server, world, profile, new PlayerInteractManager(world));
                Player kitmapP = kitmap.getBukkitEntity().getPlayer();
                kitmapP.setPlayerListName("");

                kitmap.setLocation(x, y, z, yaw, pitch);

                PacketPlayOutEntityHeadRotation kitmapRotation = new PacketPlayOutEntityHeadRotation(kitmap, (byte) ((yaw * 256.0F) / 360.0F));

                connection.sendPacket(new PacketPlayOutPlayerInfo().addPlayer(kitmap));
                connection.sendPacket(new PacketPlayOutNamedEntitySpawn(kitmap));
                connection.sendPacket(kitmapRotation);
        }
}

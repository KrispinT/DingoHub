package me.krispin.hub.commands;

import me.krispin.hub.ConfigManager;
import me.krispin.hub.npc.NPCs;
import me.krispin.hub.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetNPCCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
         if(!(sender instanceof Player)){
             sender.sendMessage("Only players can execute this command!");
         }else{
             Player p = (Player) sender;

             if(cmd.getName().equalsIgnoreCase("setnpc")){
                 if(p.hasPermission("dingohub.setnpc")){
                     if(args.length == 0){
                         p.sendMessage(Utils.color("&cUsage: /setnpc <servername>"));
                         return true;
                     }
                     if(args.length == 1){
                         String name = String.valueOf(args[0]);

                         if(name.equalsIgnoreCase("kitmap")) {
                             ConfigManager.getManager().getConfig().set("KitmapNPC.x", p.getLocation().getX());
                             ConfigManager.getManager().getConfig().set("KitmapNPC.y", p.getLocation().getY());
                             ConfigManager.getManager().getConfig().set("KitmapNPC.z", p.getLocation().getZ());
                             ConfigManager.getManager().getConfig().set("KitmapNPC.yaw", p.getLocation().getYaw());
                             ConfigManager.getManager().getConfig().set("KitmapNPC.pitch", p.getLocation().getPitch());
                             ConfigManager.getManager().saveConfig();

                             p.sendMessage(Utils.color("&aSuccessfully set Kitmap NPC Location!"));
                             return true;
                         }  else if(name.equalsIgnoreCase("squads")){
                                 ConfigManager.getManager().getConfig().set("SquadsNPC.x", p.getLocation().getX());
                                 ConfigManager.getManager().getConfig().set("SquadsNPC.y", p.getLocation().getY());
                                 ConfigManager.getManager().getConfig().set("SquadsNPC.z", p.getLocation().getZ());
                                 ConfigManager.getManager().getConfig().set("SquadsNPC.yaw", p.getLocation().getYaw());
                                 ConfigManager.getManager().getConfig().set("SquadsNPC.pitch", p.getLocation().getPitch());
                                 ConfigManager.getManager().saveConfig();

                             p.sendMessage(Utils.color("&aSuccessfully set Squads NPC Location!"));
                             return true;
                             }
                         else if(name.equalsIgnoreCase("teams")){
                             ConfigManager.getManager().getConfig().set("TeamsNPC.x", p.getLocation().getX());
                             ConfigManager.getManager().getConfig().set("TeamsNPC.y", p.getLocation().getY());
                             ConfigManager.getManager().getConfig().set("TeamsNPC.z", p.getLocation().getZ());
                             ConfigManager.getManager().getConfig().set("TeamsNPC.yaw", p.getLocation().getYaw());
                             ConfigManager.getManager().getConfig().set("TeamsNPC.pitch", p.getLocation().getPitch());
                             ConfigManager.getManager().saveConfig();

                             p.sendMessage(Utils.color("&aSuccessfully set Teams NPC Location!"));
                             return true;
                         }
                         else if(name.equalsIgnoreCase("force")){
                             NPCs npc = new NPCs();
                             npc.getForceSpawn(p, p.getLocation().getX(), p.getLocation().getY(), p.getLocation().getZ(), p.getLocation().getYaw(), p.getLocation().getPitch());
                             p.sendMessage("forced npc spawn");
                             return true;
                         }

                         else{
                             p.sendMessage(Utils.color("&cUsage: /setnpc <servername>"));
                             return true;
                         }
                     }
                 }else{
                     sender.sendMessage(Utils.color("&cYou do not have permission to execute this command!"));
                     return true;
                 }
                 return true;
             }
         }
        return false;
    }
}

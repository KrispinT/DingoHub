package me.krispin.hub.commands;

import me.krispin.hub.ConfigManager;
import me.krispin.hub.utils.Utils;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sun.security.krb5.Config;


public class SetSpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("Only players can execute this command!");
        }else{

            final Player p = (Player) sender;

            if(cmd.getName().equalsIgnoreCase("setspawn")){
                if(p.hasPermission("dingohub.setspawn")){
                    if(args.length == 0){

                        ConfigManager.getManager().getConfig().set("SpawnLocation.x", p.getLocation().getX());
                        ConfigManager.getManager().getConfig().set("SpawnLocation.y", p.getLocation().getY());
                        ConfigManager.getManager().getConfig().set("SpawnLocation.z", p.getLocation().getZ());
                        ConfigManager.getManager().getConfig().set("SpawnLocation.yaw", p.getLocation().getYaw());
                        ConfigManager.getManager().getConfig().set("SpawnLocation.pitch", p.getLocation().getPitch());

                        ConfigManager.getManager().saveConfig();
                        sender.sendMessage(Utils.color("&aServer spawn successfully set!"));
                        return true;
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

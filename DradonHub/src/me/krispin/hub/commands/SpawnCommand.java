package me.krispin.hub.commands;

import me.krispin.hub.ConfigManager;
import me.krispin.hub.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        double x = ConfigManager.getManager().getConfig().getDouble("SpawnLocation.x");
        double y = ConfigManager.getManager().getConfig().getDouble("SpawnLocation.y");
        double z = ConfigManager.getManager().getConfig().getDouble("SpawnLocation.z");
        float pitch = ConfigManager.getManager().getConfig().getFloat("SpawnLocation.pitch");
        float yaw = ConfigManager.getManager().getConfig().getFloat("SpawnLocation.yaw");

        if(!(sender instanceof Player)){
            if(cmd.getName().equalsIgnoreCase("spawn")){
                if(args.length == 0){
                    sender.sendMessage(Utils.color("&cUsage: /spawn <player>"));
                    return true;
                }

                if(args.length < 2){
                    Player target = Bukkit.getPlayer(args[0]);

                    if(target != null && target.isOnline()){
                        Location spawnLoc = new Location(Bukkit.getWorld("world"), x, y, z, yaw, pitch);

                        target.teleport(spawnLoc);
                        target.sendMessage(Utils.color("&fCONSOLE &bteleport you to spawn!"));
                        return true;
                    }else{
                        sender.sendMessage(Utils.color("&cCould not find player!"));
                        return true;
                    }
                }

            }
        }else{

            final Player p = (Player) sender;

            if(cmd.getName().equalsIgnoreCase("spawn")){
                if(p.hasPermission("dingohub.spawn")){
                    if(args.length == 0){
                       Location spawnLoc = new Location(Bukkit.getWorld("world"), x, y, z, yaw, pitch);
                       p.teleport(spawnLoc);
                       p.sendMessage(Utils.color("&bYou have been teleported to spawn!"));

                       return true;
                    }

                    if(p.hasPermission("dingohub.spawn.others")){
                        if(args.length < 2){
                            Player target = Bukkit.getPlayer(args[0]);

                            if(target != null && target.isOnline()){
                                Location spawnLoc = new Location(Bukkit.getWorld("world"), x, y, z, yaw, pitch);
                                target.teleport(spawnLoc);

                                p.sendMessage(Utils.color("&bYou have teleported &a" + target.getName() + " to spawn!"));
                                target.sendMessage(Utils.color("&bYou have been teleported to spawn by &a" + p.getName()));

                                return true;
                            }else{
                                sender.sendMessage(Utils.color("&cCould not find player!"));
                                return true;
                            }
                        }
                    }else{
                        sender.sendMessage(Utils.color("&cYou do not have permission to execute this command!"));
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

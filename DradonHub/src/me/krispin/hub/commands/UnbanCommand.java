package me.krispin.hub.commands;

import me.krispin.hub.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnbanCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(!(sender instanceof Player)){
            if(cmd.getName().equalsIgnoreCase("unban")){
                if(args.length == 0){
                    sender.sendMessage(Utils.color("&cUsage: /unban <player>"));
                    return true;
                }
                if(args.length < 2){
                    String playername = args[0];
                    Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "pardon " + playername );
                    sender.sendMessage(Utils.color("&fCONSOLE &bhas unbanned &a" + playername));
                    return true;
                }
                return true;
            }
        }else{
            final Player p = (Player) sender;

            if(cmd.getName().equals("unban")){
                if(p.hasPermission("dingohub.unban")){
                    if(args.length == 0){
                        sender.sendMessage(Utils.color("&cUsage: /unban <player>"));
                        return true;
                    }

                    if(args.length < 2){
                        String playername = args[0];
                        Bukkit.dispatchCommand(Bukkit.getServer().getConsoleSender(), "pardon " + playername );
                        sender.sendMessage(Utils.color("&fCONSOLE &bhas unbanned &a" + playername));
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

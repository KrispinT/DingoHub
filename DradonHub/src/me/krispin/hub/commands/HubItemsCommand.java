package me.krispin.hub.commands;

import me.krispin.hub.utils.ItemUtils;
import me.krispin.hub.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HubItemsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        //console send
        if (!(sender instanceof Player)) {
            if(cmd.getName().equalsIgnoreCase("hubitems")){
                if (args.length == 0) {
                    sender.sendMessage(Utils.color("&cUsage: /hubitems <player>"));
                    return true;
                }

                if (args.length < 2) {
                    Player target = Bukkit.getPlayer(args[0]);

                    if (target != null && target.isOnline()) {
                        target.getInventory().clear();

                        target.getInventory().setItem(4, ItemUtils.getSelector());
                        target.getInventory().setItem(8, ItemUtils.getVanishPlayersOff());
                        sender.sendMessage(Utils.color("&bYou have given &a" + target.getName() + "&b hub items!"));
                        return true;
                    } else {
                        sender.sendMessage(Utils.color("&cCould not find player!"));
                        return true;
                    }
                }
            }
        } else {

            //player send
            final Player p = (Player) sender;

            if (cmd.getName().equals("hubitems")) {
                if (p.hasPermission("dingohub.hubitems")) {
                    if (args.length == 0) {
                        sender.sendMessage(Utils.color("&cUsage: /hubitems <player>"));
                        return true;
                    }

                    if (args.length < 2) {
                        Player target = Bukkit.getPlayer(args[0]);

                        if (target != null && target.isOnline()) {
                            target.getInventory().clear();

                            target.getInventory().setItem(4, ItemUtils.getSelector());
                            target.getInventory().setItem(8, ItemUtils.getVanishPlayersOff());
                            sender.sendMessage(Utils.color("&bYou have given &a" + target.getName() + "&b hub items!"));
                            return true;
                        } else {
                            sender.sendMessage(Utils.color("&cCould not find player!"));
                            return true;
                        }
                    }
                } else {
                    sender.sendMessage(Utils.color("&cYou do not have permission to execute this command!"));
                    return true;
                }
                return true;
            }
        }
        return false;
    }
}
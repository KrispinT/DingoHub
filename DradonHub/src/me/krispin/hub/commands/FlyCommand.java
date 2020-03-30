package me.krispin.hub.commands;

import me.krispin.hub.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("Only players can execute this command!");
        }else{

            final Player p = (Player) sender;

            if(cmd.getName().equalsIgnoreCase("fly")){
                if(p.hasPermission("dingohub.fly")){
                    if(args.length == 0){
                        if(p.isFlying()){

                            p.setAllowFlight(false);
                            p.setFlying(false);
                            p.sendMessage(Utils.color("&bYou have &cdisabled &bflight!"));

                            return true;
                        }else{

                            p.setAllowFlight(true);
                            p.setFlying(true);
                            p.sendMessage(Utils.color("&bYou have &aenabled &bflight!"));

                            return true;
                        }
                    }

                    if(p.hasPermission("dingohub.fly.others")) {
                        if (args.length < 2) {
                            Player target = Bukkit.getPlayer(args[0]);

                            if (target != null && target.isOnline()) {
                                if(target.isFlying()){
                                    target.setAllowFlight(false);
                                    target.setFlying(false);

                                    p.sendMessage(Utils.color("&bYou set &a" + target.getName() + " &bflight: &ffalse."));
                                    target.sendMessage(Utils.color("&a" + p.getName() + " &bset your flight: &ffalse."));

                                    return true;
                                }else{
                                    target.setAllowFlight(true);
                                    target.setFlying(false);

                                    p.sendMessage(Utils.color("&bYou set &a" + target.getName() + " &bflight: &ftrue."));
                                    target.sendMessage(Utils.color("&a" + p.getName() + " &bset your flight: &ftrue."));

                                    return true;
                                }
                            }
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

package me.krispin.hub.staffpin;

import me.krispin.hub.ConfigManager;
import me.krispin.hub.Hub;
import me.krispin.hub.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetPinCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can execute this command!");
        } else {
            final Player p = (Player) sender;

            if(cmd.getName().equalsIgnoreCase("setpin")) {
                if (p.hasPermission("dingohub.staff")) {
                    if (args.length == 0) {
                        sender.sendMessage(Utils.color("&cUsage: /setpin <6-digit>"));
                        return true;
                    }


                    if(args.length > 1){
                        p.sendMessage(Utils.color("&cUsage: /login <6-digit pin>"));
                        return true;
                    }


                    try {

                        int pin = Integer.valueOf(args[0]);

                        if (String.valueOf(pin).length() > 6) {
                            p.sendMessage(Utils.color("&cPlease set a 6-digit pin!"));
                            return true;
                        }


                        if (ConfigManager.getManager().getUserData().contains("Staff_Pin." + p.getUniqueId())) {
                            p.sendMessage(Utils.color("&cYou have already set a pin!"));
                            p.sendMessage(Utils.color("* &cIf you have forgotten your pin please contact management! &f*"));
                        } else {
                            ConfigManager.getManager().getUserData().set("Staff_Pin." + p.getUniqueId(), pin);
                            ConfigManager.getManager().saveUserData();
                            Hub.getMain().getNotPlayed().remove(p.getUniqueId());
                            p.sendMessage(Utils.color("&aYou have set your pin! Use this to login."));
                        }
                        return true;

                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        p.sendMessage(Utils.color("&cInvalid input! Please use digits only!"));
                        return true;
                    }
                }else{
                    sender.sendMessage(Utils.color("&cYou do not have permission to execute this command!"));
                    return true;
                }

            }
        }
        return false;
    }
}
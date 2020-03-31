package me.krispin.hub.staffpin;

import me.krispin.hub.ConfigManager;
import me.krispin.hub.Hub;
import me.krispin.hub.utils.Utils;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PinCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage("Only players can execute this command!");
        }else{
            final Player p = (Player) sender;

            if(cmd.getName().equalsIgnoreCase("login")){
                if(p.hasPermission("dingohub.staff")){
                    if(args.length == 0){
                        p.sendMessage(Utils.color("&cUsage: /login <6-digit pin>"));
                        return true;
                    }

                    if(args.length > 1){
                        p.sendMessage(Utils.color("&cUsage: /login <6-digit pin>"));
                        return true;
                    }

                    try{

                        int pin = Integer.valueOf(args[0]);

                        if (ConfigManager.getManager().getUserData().contains("Staff_Pin." + p.getUniqueId())) {

                            int pinSet = ConfigManager.getManager().getUserData().getInt("Staff_Pin." + p.getUniqueId());

                            if(!Hub.getMain().getPinNeeded().contains(p.getUniqueId())){
                               p.sendMessage(Utils.color("&cYou are not required to login!"));
                               return true;
                            }

                            if (pinSet == pin) {
                                Hub.getMain().getPinNeeded().remove(p.getUniqueId());
                                p.sendMessage(Utils.color("&aSuccessfully logged in!"));
                            } else {
                                p.sendMessage(Utils.color("&cIncorrect pin!"));

                                //Compromised account:
                                if(!Hub.getMain().getIncorrectEntries().containsKey(p.getUniqueId())){
                                    Hub.getMain().getIncorrectEntries().put(p.getUniqueId(), 1);
                                }else{
                                    int numOfEntries = Hub.getMain().getIncorrectEntries().get(p.getUniqueId()) + 1;
                                    Hub.getMain().getIncorrectEntries().put(p.getUniqueId(), numOfEntries);

                                    if(numOfEntries > 2){
                                        String reason = Utils.color("&cCompromised Account. \n&7If this is a mistake contact mangement!");
                                        String kickReason = Utils.color("You are banned from this server!\n Reason: &cCompromised Account. \n&7If this is a mistake contact mangement!");

                                        Bukkit.getBanList(BanList.Type.NAME).addBan(p.getName(), reason, null,  null);
                                        p.kickPlayer(kickReason);
                                        Hub.getMain().getIncorrectEntries().remove(p.getUniqueId());
                                    }
                                }
                            }
                        } else {
                            p.sendMessage(Utils.color("&cPlease use /setpin to set your 6-digit pin!"));
                            p.sendMessage(Utils.color("&c* If you have already set your pin please contact management! *"));
                        }
                        return true;
                    }catch(NumberFormatException e){
                        e.printStackTrace();
                        p.sendMessage(Utils.color("&cInvalid input. Please use digits only!"));
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

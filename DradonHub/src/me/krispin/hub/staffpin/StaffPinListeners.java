package me.krispin.hub.staffpin;

import me.krispin.hub.ConfigManager;
import me.krispin.hub.Hub;
import me.krispin.hub.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;

import java.net.InetAddress;
import java.util.UUID;

public class StaffPinListeners implements Listener {

    //spawn location
    double x = ConfigManager.getManager().getConfig().getDouble("SpawnLocation.x");
    double y = ConfigManager.getManager().getConfig().getDouble("SpawnLocation.y");
    double z = ConfigManager.getManager().getConfig().getDouble("SpawnLocation.z");

    float yaw = ConfigManager.getManager().getConfig().getFloat("SpawnLocation.yaw");
    float pitch = ConfigManager.getManager().getConfig().getFloat("SpawnLocation.pitch");

    //server check

    @EventHandler
    public void onLogin(PlayerLoginEvent e){
        final Player p = e.getPlayer();

            if(ConfigManager.getManager().getUserData().contains("Staff_Pin." + p.getUniqueId()) && p.hasPermission("dingohub.staff")){
                if(Hub.getMain().getPinRequiredCheck().containsKey(p.getUniqueId())){
                    InetAddress ip = Hub.getMain().getPinRequiredCheck().get(p.getUniqueId());
                    if(e.getRealAddress().equals(ip)){
                        //no pin required
                        return;
                    }else{
                        //ask for pin
                        //NOTE - update ip on correct pin entry
                        Hub.getMain().getPinNeeded().add(p.getUniqueId());
                        Hub.getMain().getPinRequiredCheck().put(p.getUniqueId(), e.getRealAddress());
                        Bukkit.getServer().getScheduler().runTaskLaterAsynchronously(Hub.getPlugin(Hub.class), () -> p.sendMessage(Utils.color("&bPlease use /login to gain access to dingo network!")),2);
                    }
                }else{
                    Hub.getMain().getPinRequiredCheck().put(p.getUniqueId(), e.getRealAddress());
                    //ask for pin
                    Hub.getMain().getPinNeeded().add(p.getUniqueId());
                    Bukkit.getServer().getScheduler().runTaskLaterAsynchronously(Hub.getPlugin(Hub.class), () -> p.sendMessage(Utils.color("&bPlease use /login to gain access to dingo network!")),2);
                }
            }else if(p.hasPermission("dingohub.staff")){

                Hub.getMain().getNotPlayed().add(p.getUniqueId());
                Bukkit.getServer().getScheduler().runTaskLaterAsynchronously(Hub.getPlugin(Hub.class), () -> p.sendMessage(Utils.color("&cPlease set a 6-digit pin. Use /setpin <pin>.")), 2);

            }else{
                return;
            }


    }



    //player movement
    @EventHandler
    public void onMove(PlayerMoveEvent e){
        final Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();

        if(Hub.getMain().getNotPlayed().contains(uuid) || Hub.getMain().getPinNeeded().contains(uuid)){
            Location loc = new Location(p.getLocation().getWorld(), x, y, z, yaw, pitch);
            p.teleport(loc);
        }
    }

    //player opening inventory
    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        final Player p = e.getPlayer();
        UUID uuid = p.getUniqueId();

        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (Hub.getMain().getNotPlayed().contains(uuid)) {
                p.closeInventory();
                e.setCancelled(true);
            }
            if (Hub.getMain().getPinNeeded().contains(uuid)) {
                p.closeInventory();
                e.setCancelled(true);
            }
        }
    }

}

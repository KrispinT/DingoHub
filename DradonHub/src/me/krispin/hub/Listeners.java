package me.krispin.hub;

import me.krispin.hub.inventories.SelectorInventory;
import me.krispin.hub.utils.ItemUtils;
import me.krispin.hub.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;


public class Listeners implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        final Player p = e.getPlayer();

        //spawn location
        double x = ConfigManager.getManager().getConfig().getDouble("SpawnLocation.x");
        double y = ConfigManager.getManager().getConfig().getDouble("SpawnLocation.y");
        double z = ConfigManager.getManager().getConfig().getDouble("SpawnLocation.z");

        float pitch = ConfigManager.getManager().getConfig().getFloat("SpawnLocation.pitch");
        float yaw = ConfigManager.getManager().getConfig().getFloat("SpawnLocation.yaw");

        Location loc = new Location(p.getLocation().getWorld(), x, y, z, yaw, pitch);

        //teleport spawn
        p.teleport(loc);

        //join msg = null, clear inv, add items
        e.setJoinMessage(null);

        //join message
        p.sendMessage(Utils.color("&7&m-------------------------------------------------------"));
        p.sendMessage(Utils.color(""));
        p.sendMessage(Utils.color("&fWelcome to the &bDingo Network&b!"));
        p.sendMessage(Utils.color(""));
        p.sendMessage(Utils.color("&f➢ &3Discord: &fdiscord.dingomc.com"));
        p.sendMessage(Utils.color("&f➢ &3Store: &fstore.dingomc.com"));
        p.sendMessage(Utils.color("&f➢ &3Twitter: &ftwitter.com/dingonetworkmc"));
        p.sendMessage(Utils.color(""));
        p.sendMessage(Utils.color("&7&m-------------------------------------------------------"));

        p.getInventory().clear();

        p.getInventory().setItem(4, ItemUtils.getSelector());
        p.getInventory().setItem(8, ItemUtils.getVanishPlayersOn());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e){
        final Player p = e.getPlayer();
        e.setQuitMessage(null);

        if(Hub.getMain().getIncorrectEntries().containsKey(p.getUniqueId())){
            Hub.getMain().getIncorrectEntries().remove(p.getUniqueId());
        }

        if(Hub.getMain().getPinNeeded().contains(p.getUniqueId())){
            Hub.getMain().getPinNeeded().remove(p.getUniqueId());
        }
    }

    //disable inventory move
    @EventHandler
    public void invMove(InventoryClickEvent e){
        final Player p = (Player) e.getWhoClicked();

        if(p.getGameMode().equals(GameMode.SURVIVAL)){
            if(e.getCurrentItem().isSimilar(ItemUtils.getSelector())){
                e.setCancelled(true);
            }
            if(e.getCurrentItem().isSimilar(ItemUtils.getVanishPlayersOn())){
                e.setCancelled(true);
            }
            if(e.getCurrentItem().isSimilar(ItemUtils.getVanishPlayersOff())){
                e.setCancelled(true);
            }
            e.setCancelled(true);
        }else{
            return;
        }
    }

    //items listener
    @EventHandler
    public void onInteract(PlayerInteractEvent e){
        final Player p = e.getPlayer();

        //server selector
        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(p.getItemInHand().isSimilar(ItemUtils.getSelector())){

                //kitmap
                if(Hub.getMain().serverOfflineCheck(25567)){
                    Hub.getMain().getServerStatus().put("kitmap", true);
                    Hub.getMain().refreshOnline("kitmap");
                }else{
                    Hub.getMain().getServerStatus().put("kitmap", false);
                }

                //squads
                if(Hub.getMain().serverOfflineCheck(25568)){
                    Hub.getMain().getServerStatus().put("squads", true);
                    Hub.getMain().refreshOnline("squads");
                }else{
                    Hub.getMain().getServerStatus().put("squads", false);
                }

                //teams
                if(Hub.getMain().serverOfflineCheck(25569)){
                    Hub.getMain().getServerStatus().put("teams", true);
                    Hub.getMain().refreshOnline("teams");
                }else{
                    Hub.getMain().getServerStatus().put("teams", false);
                }

                //bunkers
                if(Hub.getMain().serverOfflineCheck(25570)){
                    Hub.getMain().getServerStatus().put("bunkers", true);
                    Hub.getMain().refreshOnline("bunkers");
                }else{
                    Hub.getMain().getServerStatus().put("bunkers", false);
                }

                //practice
                if(Hub.getMain().serverOfflineCheck(25571)){
                    Hub.getMain().getServerStatus().put("practice", true);
                    Hub.getMain().refreshOnline("practice");
                }else{
                    Hub.getMain().getServerStatus().put("practice", false);
                }

                //open inventory
                SelectorInventory.getInstance().openInventory(p);
            }
        }

        //toggle players
        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK){
            if(p.getItemInHand().isSimilar(ItemUtils.getVanishPlayersOn())){
                for(Player pl : Bukkit.getOnlinePlayers()){
                    p.hidePlayer(pl);
                }
                p.getInventory().setItem(8, ItemUtils.getVanishPlayersOff());
                p.sendMessage(Utils.color("&aYou have hidden all players!"));
                return;
            }else if(p.getItemInHand().isSimilar(ItemUtils.getVanishPlayersOff())){
                    for(Player pl : Bukkit.getOnlinePlayers()){
                        p.showPlayer(pl);
                    }
                    p.getInventory().setItem(8, ItemUtils.getVanishPlayersOn());
                    p.sendMessage(Utils.color("&aYou have unhidden all players!"));
                    return;

            }else{
                return;
            }
        }
    }

    //drop items
    @EventHandler
    public void onDrop(PlayerDropItemEvent e){
        final Player p = e.getPlayer();
        if(p.getGameMode().equals(GameMode.SURVIVAL)){
            e.setCancelled(true);
        }
    }

    //place blocks
    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        final Player p = e.getPlayer();
        if(p.getGameMode().equals(GameMode.SURVIVAL)){
            e.setCancelled(true);
        }
    }

    //break blocks
    @EventHandler
    public void onBreak(BlockBreakEvent e){
        final Player p = e.getPlayer();
        if(p.getGameMode().equals(GameMode.SURVIVAL)){
            e.setCancelled(true);
        }
    }

    //deny damage
    @EventHandler
    public void onDamage(EntityDamageEvent e){
        if(e.getEntity() instanceof Player){
            final Player p = (Player) e.getEntity();

            //Spawn location
            double x = ConfigManager.getManager().getConfig().getDouble("SpawnLocation.x");
            double y = ConfigManager.getManager().getConfig().getDouble("SpawnLocation.y");
            double z = ConfigManager.getManager().getConfig().getDouble("SpawnLocation.z");

            float pitch = ConfigManager.getManager().getConfig().getFloat("SpawnLocation.pitch");
            float yaw = ConfigManager.getManager().getConfig().getFloat("SpawnLocation.yaw");

            Location spawnLoc = new Location(p.getLocation().getWorld(), x, y, z, yaw, pitch);

            if(e.getCause().equals(EntityDamageEvent.DamageCause.VOID)){
                p.teleport(spawnLoc);
                e.setCancelled(true);
            }else{
                e.setCancelled(true);
            }
        }
    }

    //disable chat for everyone below kingfish
    @EventHandler
    public void disableChat(AsyncPlayerChatEvent e){
        final Player p = e.getPlayer();
        if(!p.hasPermission("dingohub.staff")){
            e.setCancelled(true);
        }

        if(p.hasPermission("dingohub.chat")){
            e.setCancelled(false);
        }
    }

    //Food level fix
    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e){
        if(e.getEntity() instanceof Player){
            e.setCancelled(true);
        }
    }

    //Weather change fix
    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e){
        e.setCancelled(true);
    }

}

package me.krispin.hub.inventories;

import me.krispin.hub.Hub;
import me.krispin.hub.utils.ItemUtils;
import me.krispin.hub.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.plugin.Plugin;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SelectorInventory implements Listener {
    private SelectorInventory(){}
    private static SelectorInventory instance = new SelectorInventory();
    public static SelectorInventory getInstance(){
        return instance;
    }

    private Inventory inv;

    public void setup(Plugin pl){
        inv = Bukkit.createInventory(new InvHolder(), 54, ChatColor.LIGHT_PURPLE + "Server Selector");
    }

    public void refreshInv(){
        inv.clear();

        //teams online set
        if(Hub.getMain().getServerStatus().get("teams")){
            int teamsPlayers;
            if(Hub.getMain().getOnline().get("teams") == null){
                //teams onlinePlayers = 0
                List<String> lore = new ArrayList<>();
                lore.add(Utils.color("&7&m----------------------"));
                lore.add(Utils.color("&f» &e0 Online"));
                lore.add("");
                lore.add(Utils.color("&f⋆ &d10 mans!"));
                lore.add(Utils.color("&f⋆ &d15 minute koths!"));
                lore.add(Utils.color("&f⋆ &dConquest each Saturday!"));
                lore.add("");
                lore.add(Utils.color("&fClick to join!"));
                lore.add(Utils.color("&7&m----------------------"));
                ItemUtils.setTeamsLore(lore);
                inv.setItem(10, ItemUtils.getTeams());
            }else{
                //active teams players
                List<String> lore = new ArrayList<>();
                teamsPlayers = Hub.getMain().getOnline().get("teams");
                lore.add(Utils.color("&7&m----------------------"));
                lore.add(Utils.color("&f» &e" + teamsPlayers + " &eOnline"));
                lore.add("");
                lore.add(Utils.color("&f⋆ &d10 mans!"));
                lore.add(Utils.color("&f⋆ &d15 minute koths!"));
                lore.add(Utils.color("&f⋆ &dConquest each Saturday!"));
                lore.add("");
                lore.add(Utils.color("&fClick to join!"));
                lore.add(Utils.color("&7&m----------------------"));
                ItemUtils.setTeamsLore(lore);
                inv.setItem(10, ItemUtils.getTeams());
            }
        }else{
            //teams offline
            List<String> lore = new ArrayList<>();
            lore.add(Utils.color("&7&m----------------------"));
            lore.add(Utils.color("&f» &cServer offline"));
            lore.add("");
            lore.add(Utils.color("&f⋆ &d10 mans!"));
            lore.add(Utils.color("&f⋆ &d15 minute koths!"));
            lore.add(Utils.color("&f⋆ &dConquest each Saturday!"));
            lore.add("");
            lore.add(Utils.color("&fClick to join!"));
            lore.add(Utils.color("&7&m----------------------"));
            ItemUtils.setTeamsLore(lore);
            inv.setItem(10, ItemUtils.getTeams());
        }

        //squads online set
        if(Hub.getMain().getServerStatus().get("squads")){
            int squadsPlayers;
            if(Hub.getMain().getOnline().get("squads") == null){
                //squads onlinePlayers = 0
                List<String> lore = new ArrayList<>();
                lore.add(Utils.color("&7&m----------------------"));
                lore.add(Utils.color("&f» &e0 Online"));
                lore.add("");
                lore.add(Utils.color("&f⋆ &d5 mans!"));
                lore.add(Utils.color("&f⋆ &dUnique Magical Items!"));
                lore.add(Utils.color("&f⋆ &dConquest Saturday 3pm!"));
                lore.add("");
                lore.add(Utils.color("&fClick to join!"));
                lore.add(Utils.color("&7&m----------------------"));
                ItemUtils.setSquadsLore(lore);
                inv.setItem(13, ItemUtils.getSquads());
            }else{
                //active squads players
                squadsPlayers = Hub.getMain().getOnline().get("squads");
                List<String> lore = new ArrayList<>();
                lore.add(Utils.color("&7&m----------------------"));
                lore.add(Utils.color("&f» &e" + squadsPlayers + " &eOnline"));
                lore.add("");
                lore.add(Utils.color("&f⋆ &d5 mans!"));
                lore.add(Utils.color("&f⋆ &dUnique Magical Items!"));
                lore.add(Utils.color("&f⋆ &dConquest Saturday 3pm!"));
                lore.add("");
                lore.add(Utils.color("&fClick to join!"));
                lore.add(Utils.color("&7&m----------------------"));
                ItemUtils.setSquadsLore(lore);
                inv.setItem(13, ItemUtils.getSquads());
            }
        }else{
            //squads offline
            List<String> lore = new ArrayList<>();
            lore.add(Utils.color("&7&m----------------------"));
            lore.add(Utils.color("&f» &cServer offline"));
            lore.add("");
            lore.add(Utils.color("&f⋆ &d5 mans!"));
            lore.add(Utils.color("&f⋆ &dUnique Magical Items!"));
            lore.add(Utils.color("&f⋆ &dConquest Saturday 3pm!"));
            lore.add("");
            lore.add(Utils.color("&fClick to join!"));
            lore.add(Utils.color("&7&m----------------------"));
            ItemUtils.setSquadsLore(lore);
            inv.setItem(13, ItemUtils.getSquads());
        }


        //kitmap online set
        if(Hub.getMain().getServerStatus().get("kitmap")){
            int kitmapPlayers;
            if(Hub.getMain().getOnline().get("kitmap") == null){
                //kitmap onlinePlayers = 0
                List<String> lore = new ArrayList<>();
                lore.add(Utils.color("&7&m----------------------"));
                lore.add(Utils.color("&f» &e0 Online"));
                lore.add("");
                lore.add(Utils.color("&f⋆ &dUnique Magical Items!"));
                lore.add(Utils.color("&f⋆ &dConsecutive Koths!"));
                lore.add(Utils.color("&f⋆ &dCustom kill streaks!"));
                lore.add("");
                lore.add(Utils.color("&fClick to join!"));
                lore.add(Utils.color("&7&m----------------------"));
                ItemUtils.setKitmapLore(lore);
                inv.setItem(16, ItemUtils.getKitMap());
            }else{
                //active players online
                List<String> lore = new ArrayList<>();
                kitmapPlayers = Hub.getMain().getOnline().get("kitmap");
                lore.add(Utils.color("&7&m----------------------"));
                lore.add(Utils.color("&f» &e" + kitmapPlayers + " &eOnline"));
                lore.add("");
                lore.add(Utils.color("&f⋆ &dUnique Magical Items!"));
                lore.add(Utils.color("&f⋆ &dConsecutive Koths!"));
                lore.add(Utils.color("&f⋆ &dCustom kill streaks!"));
                lore.add("");
                lore.add(Utils.color("&fClick to join!"));
                lore.add(Utils.color("&7&m----------------------"));
                ItemUtils.setKitmapLore(lore);
                inv.setItem(16, ItemUtils.getKitMap());
            }
        }else{
            //kitmap offline
            List<String> lore = new ArrayList<>();
            lore.add(Utils.color("&7&m----------------------"));
            lore.add(Utils.color("&f» &cServer offline"));
            lore.add("");
            lore.add(Utils.color("&f⋆ &dUnique Magical Items!"));
            lore.add(Utils.color("&f⋆ &dConsecutive Koths!"));
            lore.add(Utils.color("&f⋆ &dCustom kill streaks!"));
            lore.add("");
            lore.add(Utils.color("&fClick to join!"));
            lore.add(Utils.color("&7&m----------------------"));
            ItemUtils.setKitmapLore(lore);
            inv.setItem(16, ItemUtils.getKitMap());
        }

        //practice online set
        inv.setItem(29, ItemUtils.getPractice());


        //bunkers online set
        inv.setItem(33, ItemUtils.getBunkers());


        inv.setItem(53, ItemUtils.getDev());
    }

    public void openInventory(Player p){
        this.refreshInv();
        p.openInventory(inv);
    }

    @EventHandler
    public void onClick(InventoryClickEvent e){
        final Player p = (Player) e.getWhoClicked();

        if(e.getInventory().getHolder() instanceof InvHolder){
            if(e.getInventory().equals(p.getInventory())){
                return;
            }

            //teams
            if(e.getCurrentItem().isSimilar(ItemUtils.getTeams())){
                try{
                    ByteArrayOutputStream b = new ByteArrayOutputStream();
                    DataOutputStream out = new DataOutputStream(b);
                    out.writeUTF("Connect");
                    out.writeUTF("teams");
                    p.sendPluginMessage(Hub.getPlugin(Hub.class), "BungeeCord", b.toByteArray());
                    b.close();
                    out.close();
                }catch(Exception ex){
                    p.sendMessage(ChatColor.RED+"Error when trying to connect to: "+ ChatColor.WHITE + "teams");
                }
            }

            //squads
            if(e.getCurrentItem().isSimilar(ItemUtils.getSquads())){
                try{
                    ByteArrayOutputStream b = new ByteArrayOutputStream();
                    DataOutputStream out = new DataOutputStream(b);
                    out.writeUTF("Connect");
                    out.writeUTF("squads");
                    p.sendPluginMessage(Hub.getPlugin(Hub.class), "BungeeCord", b.toByteArray());
                    b.close();
                    out.close();
                }catch(Exception ex){
                    p.sendMessage(ChatColor.RED+"Error when trying to connect to: "+ ChatColor.WHITE + "squads");
                }
            }

            //kitmap
            if(e.getCurrentItem().isSimilar(ItemUtils.getKitMap())){
                try{
                    ByteArrayOutputStream b = new ByteArrayOutputStream();
                    DataOutputStream out = new DataOutputStream(b);
                    out.writeUTF("Connect");
                    out.writeUTF("kitmap");
                    p.sendPluginMessage(Hub.getPlugin(Hub.class), "BungeeCord", b.toByteArray());
                    b.close();
                    out.close();
                }catch(Exception ex){
                    p.sendMessage(ChatColor.RED+"Error when trying to connect to: "+ ChatColor.WHITE + "kitmap");
                }
            }

            //dev
            if(e.getCurrentItem().isSimilar(ItemUtils.getDev())){
                try{
                    ByteArrayOutputStream b = new ByteArrayOutputStream();
                    DataOutputStream out = new DataOutputStream(b);
                    out.writeUTF("Connect");
                    out.writeUTF("dev");
                    p.sendPluginMessage(Hub.getPlugin(Hub.class), "BungeeCord", b.toByteArray());
                    b.close();
                    out.close();
                }catch(Exception ex){
                    p.sendMessage(ChatColor.RED+"Error when trying to connect to: "+ ChatColor.WHITE + "dev");
                }
            }


            e.setCancelled(true);


        }else{
            return;
        }
    }
}
class InvHolder implements InventoryHolder {

    @Override
    public Inventory getInventory() {
        return null;
    }

}

package me.krispin.hub.utils;

import com.mysql.jdbc.NonRegisteringDriver;
import com.sun.org.apache.xpath.internal.objects.XString;
import net.minecraft.server.v1_7_R4.NBTTagCompound;
import net.minecraft.server.v1_7_R4.NBTTagList;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_7_R4.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemUtils {

    public static ItemStack addGlow(ItemStack item){
        net.minecraft.server.v1_7_R4.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound nbt = null;
        if(!nmsStack.hasTag()){
            nbt = new NBTTagCompound();
            nmsStack.setTag(nbt);
        }
        if(nbt == null) nbt = nmsStack.getTag();
        NBTTagList ench = new NBTTagList();
        nbt.set("ench", ench);
        nmsStack.setTag(nbt);
        return CraftItemStack.asCraftMirror(nmsStack);
    }

    public static ItemStack getSelector(){
        ItemStack i = new ItemStack(Material.WATCH);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(ChatColor.LIGHT_PURPLE + ChatColor.BOLD.toString() + "Server Selector");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + " - Use this to select a server!");
        m.setLore(lore);
        i.setItemMeta(m);
        return i;

    }

    public static ItemStack getVanishPlayersOff(){
        ItemStack i = new ItemStack(Material.REDSTONE_TORCH_ON);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(ChatColor.RED + "Toggle Players " + ChatColor.DARK_RED + "(Off)");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + " - Hide or show players!");
        m.setLore(lore);
        i.setItemMeta(m);
        return addGlow(i);
    }

    public static ItemStack getVanishPlayersOn(){
        ItemStack i = new ItemStack(Material.REDSTONE_TORCH_ON);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(ChatColor.RED + "Toggle Players " + ChatColor.GREEN + "(On)");
        List<String> lore = new ArrayList<>();
        lore.add(ChatColor.GRAY + " - Hide or show players!");
        m.setLore(lore);
        i.setItemMeta(m);
        return addGlow(i);
    }

    public static List<String> teamsLore = new ArrayList<>();

    public static List<String> getTeamsLore(){
        return teamsLore;
    }

    public static void setTeamsLore(List<String> lore){
        teamsLore = lore;
    }

    public static ItemStack getTeams(){
        ItemStack i = new ItemStack(Material.DIAMOND_CHESTPLATE);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(Utils.color("&c&lTeams"));
        m.setLore(teamsLore);
        i.setItemMeta(m);
        return i;
    }

    public static List<String> squadsLore = new ArrayList<>();

    public static List<String> getSquadsLore(){
        return squadsLore;
    }

    public static void setSquadsLore(List<String> lore){
        squadsLore = lore;
    }

    public static ItemStack getSquads(){
        ItemStack i = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(Utils.color("&c&lSquads"));
        m.setLore(squadsLore);
        i.setItemMeta(m);
        return i;
    }

    public static ItemStack getDev(){
        ItemStack i = new ItemStack(Material.EXP_BOTTLE);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(Utils.color("&9Dev"));
        List<String> lore = new ArrayList<>();
        lore.add("");
        lore.add(Utils.color("&bShipsterns' cave. No entry :>."));
        lore.add("");
        m.setLore(lore);
        i.setItemMeta(m);
        return i;
    }

    public static List<String> kitmapLore = new ArrayList<>();

    public static List<String> getKitmapLore(){
        return kitmapLore;
    }

    public static void setKitmapLore(List<String> lore){
        kitmapLore = lore;
    }

    public static ItemStack getKitMap(){
        ItemStack i = new ItemStack(Material.GOLDEN_APPLE);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(Utils.color("&c&lKitmap"));
        m.setLore(kitmapLore);
        i.setItemMeta(m);
        return i;
    }

    public static List<String> bunkersLore = new ArrayList<>();

    public static List<String> getBunkersLore(){
        return bunkersLore;
    }

    public static void setBunkersLore(List<String> lore){
        bunkersLore = lore;
    }

    public static ItemStack getBunkers(){
        ItemStack i = new ItemStack(Material.BEACON);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(Utils.color("&c&lBunkers"));
        m.setLore(bunkersLore);
        i.setItemMeta(m);
        return i;
    }

    public static List<String> practiceLore = new ArrayList<>();

    public static List<String> getPracticeLore(){
        return practiceLore;
    }

    public static void setPracticeLore(List<String> lore){
        practiceLore = lore;
    }

    public static ItemStack getPractice(){
        ItemStack i = new ItemStack(Material.POTION, 1, (byte) 8261);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(Utils.color("&c&lPractice"));
        m.setLore(practiceLore);
        i.setItemMeta(m);
        return i;
    }
}

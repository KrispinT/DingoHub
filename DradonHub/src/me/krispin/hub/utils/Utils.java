package me.krispin.hub.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import ru.tehkode.permissions.PermissionUser;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class Utils {

    public static String color (String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static String getPexPrefix(Player p){

        PermissionUser user = PermissionsEx.getUser(p);

        String prefix = user.getPrefix();

        if(prefix.equals("&8[&4Owner&8] &4")) {
            return "&4Owner";
        }else if(prefix.equals("&8[&6Manager&8] &6")){
            return "&6Manager";
        }else if(prefix.equals("&8[&cSenior Admin&8] &c")){
            return "&cSenior Admin";
        }else if(prefix.equals("&8[&cAdmin&8] &c")){
            return "&cAdmin";
        }else if(prefix.equals("&8[&9Senior Mod&8] &9")){
            return "&9Senior Mod";
        }else if(prefix.equals("&8[&3Mod&8] &3")){
            return "&3Mod";
        }else if(prefix.equals("&8[&eTrail Mod&8] &e")){
            return "&eTrial Mod";
        }else if(prefix.equals("&8[&d&oPartner&8] &d&o")){
            return "&d&oPartner";
        }else if(prefix.equals("&8[&dFamous&8] &d")){
            return "&dFamous";
        }else if(prefix.equals("&d")){
            return "&dYoutuber";
        }else if(prefix.equals("&5")){
            return "&5Kingfish";
        }else if(prefix.equals("&6")){
            return "&6Snapper";
        }else if(prefix.equals("&a")){
            return "&aBream";
        }else if(prefix.equals("&f")){
            return "&fDefault";
        }else{
            return null;
        }

    }

}

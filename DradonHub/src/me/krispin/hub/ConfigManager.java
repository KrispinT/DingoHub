package me.krispin.hub;


import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class ConfigManager {
    private ConfigManager(){}
    private static ConfigManager mng = new ConfigManager();
    public static ConfigManager getManager(){
        return mng;
    }

    File configF, userDataF;
    FileConfiguration config, userData;

    public void setup(Plugin pl){

        configF = new File(pl.getDataFolder(), "config.yml");
            if(!configF.exists()){
                configF.getParentFile().mkdir();
                pl.saveDefaultConfig();
                pl.saveResource("config.yml", false);
            }

            config = new YamlConfiguration();

            try{
                config.load(configF);
            }catch (IOException | InvalidConfigurationException e){
                e.printStackTrace();
            }

            userDataF = new File(pl.getDataFolder(), "userdata.yml");
            if(!userDataF.exists()){
                userDataF.getParentFile().mkdir();
                pl.saveResource("userdata.yml", false);
            }

            userData = new YamlConfiguration();

            try{
                userData.load(userDataF);
            }catch(IOException | InvalidConfigurationException e){
                e.printStackTrace();
            }

    }

    public FileConfiguration getConfig(){return config;}
    public FileConfiguration getUserData(){return userData;}

    public void saveConfig(){
        try{
            config.save(configF);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void saveUserData(){
        try{
            userData.save(userDataF);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

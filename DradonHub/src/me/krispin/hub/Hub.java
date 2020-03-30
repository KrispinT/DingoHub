package me.krispin.hub;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.krispin.hub.commands.*;
import me.krispin.hub.inventories.SelectorInventory;
import me.krispin.hub.scoreboard.DingoScoreboard;
import me.krispin.hub.staffpin.PinCommand;
import me.krispin.hub.staffpin.SetPinCommand;
import me.krispin.hub.staffpin.StaffPinListeners;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.plugin.messaging.PluginMessageListener;
import ru.tehkode.permissions.bukkit.PermissionsEx;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.*;

public class Hub extends JavaPlugin implements PluginMessageListener {

    private PermissionsEx perms;
    private static Hub main;
    private Map<String, Integer> online;
    private Set<UUID> noPin;
    private Set<UUID> pinNeeded;
    private Map<UUID, InetAddress> pinRequiredCheck;
    private Map<UUID, Integer> incorrectEntries;
    private Map<String, Boolean> serverStatus;

    @Override
    public void onEnable(){

        main = this;

        online = new HashMap<>();
        noPin = new HashSet<>();
        pinNeeded = new HashSet<>();
        incorrectEntries = new HashMap<>();
        serverStatus = new HashMap<>();
        pinRequiredCheck = new HashMap<>();

        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
        this.getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", this);

        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new Runnable() {
            @Override
            public void run() {
                refreshOnline("ALL");
            }
        }, 50L, 50L);

        SelectorInventory.getInstance().setup(this);
        ConfigManager.getManager().setup(this);

        Bukkit.getServer().getPluginManager().registerEvents(SelectorInventory.getInstance(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new Listeners(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new DingoScoreboard(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new StaffPinListeners(), this);

        getCommand("hubitems").setExecutor(new HubItemsCommand());
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        getCommand("setpin").setExecutor(new SetPinCommand());
        getCommand("login").setExecutor(new PinCommand());
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("unban").setExecutor(new UnbanCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());

        Bukkit.getServer().getScheduler().runTaskTimerAsynchronously(this, () -> Hub.getMain().getPinRequiredCheck().clear(), 5L, 216000L);
    }

    @Override
    public void onDisable(){

    }


    public static Hub getMain(){
        return main;
    }

    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("BungeeCord")) {
            return;
        }

        try {
            ByteArrayDataInput in = ByteStreams.newDataInput(message);
            String subchannel = in.readUTF();
            if (subchannel.equals("PlayerCount")) {

                String server = in.readUTF();
                int online = in.readInt();

                this.online.put(server, online);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void refreshOnline(String server){
        try{
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("PlayerCount");
            out.writeUTF(server);

            Bukkit.getServer().sendPluginMessage(this, "BungeeCord", out.toByteArray());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public Map<String, Integer> getOnline(){return online;}

    public Map<UUID, Integer> getIncorrectEntries(){return incorrectEntries;}

    public Set<UUID> getNotPlayed() {
        return noPin;
    }

    public Map<String, Boolean> getServerStatus() {return  serverStatus;}

    public Map<UUID, InetAddress> getPinRequiredCheck(){return pinRequiredCheck;}

    public Set<UUID> getPinNeeded(){return pinNeeded;}

    public PermissionsEx getPex(){
        return perms;
    }

    public boolean serverOfflineCheck(int port) {
            try{
                Socket s = new Socket();
                s.connect(new InetSocketAddress("192.168.0.36", port), 20);
                s.close();
                return true;
            }catch (UnknownHostException e){
                return false;
            } catch (IOException e){
                return false;
            }
    }

}


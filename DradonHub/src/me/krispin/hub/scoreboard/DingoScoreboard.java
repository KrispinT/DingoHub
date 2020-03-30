package me.krispin.hub.scoreboard;

import me.krispin.hub.Hub;
import me.krispin.hub.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;


import org.bukkit.scoreboard.*;


public class DingoScoreboard implements Listener {

    //onJoin
    @EventHandler
    public void onJoin(PlayerJoinEvent e){

            int onlineCount;

            //init onlinecount
            if(Hub.getMain().getOnline().size() != 0){
                   onlineCount = Hub.getMain().getOnline().get("ALL");
            }else{
                    onlineCount = 1;
            }

            final Player p = e.getPlayer();

            //Scoreboard
                        ScoreboardManager m = Bukkit.getScoreboardManager();
                        Scoreboard b = m.getNewScoreboard();

                        Objective o = b.registerNewObjective("DingoMC", "");
                        o.setDisplaySlot(DisplaySlot.SIDEBAR);
                        o.setDisplayName(Utils.color("&b&lDingo &7⎜ &rHub"));

                        Team team = b.registerNewTeam("sperator");
                        team.addEntry(ChatColor.GRAY.toString() + ChatColor.GRAY);
                        team.setPrefix(Utils.color("&7&m-----------"));
                        team.setSuffix(Utils.color("&7&m-----------"));

                        Team team2 = b.registerNewTeam("sperator2");
                        team2.addEntry(ChatColor.GRAY.toString() + ChatColor.RED);
                        team2.setPrefix(Utils.color("&7&m-----------"));
                        team2.setSuffix(Utils.color("&7&m-----------"));

                        Score splitter = o.getScore(ChatColor.GRAY.toString() + ChatColor.GRAY);
                        Score splitter2 = o.getScore(ChatColor.GRAY.toString() + ChatColor.RED);

                        Score online = o.getScore(Utils.color("&3Online:"));
                        Score count = o.getScore(String.valueOf(onlineCount));

                        Score spacer = o.getScore("  ");

                        Score rank = o.getScore(Utils.color("&3Rank:"));
                        Score userRank = o.getScore(Utils.color(Utils.getPexPrefix(p)));

                        Score spacer1 = o.getScore(" ");

                        Score ip = o.getScore(Utils.color("&3dingomc.com"));

                        splitter2.setScore(9);
                        online.setScore(8);
                        count.setScore(7);
                        spacer1.setScore(6);
                        rank.setScore(5);
                        userRank.setScore(4);
                        spacer.setScore(3);
                        ip.setScore(2);
                        splitter.setScore(1);

                        p.setScoreboard(b);
                }


            }






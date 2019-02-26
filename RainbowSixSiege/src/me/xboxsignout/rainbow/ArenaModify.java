package me.xboxsignout.rainbow;

import org.bukkit.event.Listener;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

public class ArenaModify implements Listener
{
	private Inventory inv;
   
	private Settings settings;
	String arenaname;
	
    public ArenaModify(Plugin p, Settings s) {
            inv = Bukkit.getServer().createInventory(null, 9, "Modify Arena");
            
            ItemStack casual = new ItemStack(Material.IRON_INGOT);
            ItemMeta casualmeta = casual.getItemMeta();
            casualmeta.setDisplayName(ChatColor.YELLOW + "Casual");
            ArrayList<String> lore = new ArrayList<String>();
            lore.add("Set this arenas mode to Casual");
            casualmeta.setLore(lore);
            casual.setItemMeta(casualmeta);
            
            ItemStack ranked = new ItemStack(Material.DIAMOND);
            ItemMeta rankedmeta = ranked.getItemMeta();
            rankedmeta.setDisplayName(ChatColor.YELLOW + "Ranked");
            ArrayList<String> lore2 = new ArrayList<String>();
            lore2.add("Set this arenas mode to Ranked");
            rankedmeta.setLore(lore2);
            ranked.setItemMeta(rankedmeta);
            
            ItemStack bombA = new ItemStack(Material.TNT);
            ItemMeta bombam = bombA.getItemMeta();
            bombam.setDisplayName(ChatColor.YELLOW + "Bomb A");
            ArrayList<String> lore3 = new ArrayList<String>();
            lore3.add("Set the first possible bomb spot to your position");
            bombam.setLore(lore3);
            bombA.setItemMeta(bombam);
            
            ItemStack bombB = new ItemStack(Material.TNT);
            ItemMeta bombbm = bombB.getItemMeta();
            bombbm.setDisplayName(ChatColor.YELLOW + "Bomb B");
            ArrayList<String> lore4 = new ArrayList<String>();
            lore4.add("Set the second possible bomb spot to your position");
            bombbm.setLore(lore4);
            bombB.setItemMeta(bombbm);
            
            ItemStack exit = new ItemStack(Material.BARRIER);
            ItemMeta exitmeta = exit.getItemMeta();
            exitmeta.setDisplayName(ChatColor.YELLOW + "Exit");
            exit.setItemMeta(exitmeta);
           
            inv.setItem(0, casual);
            inv.setItem(1, ranked);
            inv.setItem(2, bombA);
            inv.setItem(3, bombB);
            inv.setItem(8, exit);
            
            settings = s;
            
           
            Bukkit.getServer().getPluginManager().registerEvents(this, p);
    }
   
    public void show(Player p, String arena) 
    {
    	arenaname = arena;
        p.openInventory(inv);
    }
   
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
            if (!e.getInventory().getName().equalsIgnoreCase(inv.getName())) return;
            if (e.getCurrentItem().getItemMeta() == null) return;
            if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Exit")) {
                    e.setCancelled(true);
                    e.getWhoClicked().closeInventory();
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Ranked")) {
                    e.setCancelled(true);
                    Player p = (Player) e.getWhoClicked();
                    p.sendMessage("Set " + arenaname + "'s mode to ranked");
                    settings.getData().set("arenas." + arenaname + ".mode", "ranked");
                    settings.saveData();
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Casual")) {
                    e.setCancelled(true);
                    Player p = (Player) e.getWhoClicked();
                    p.sendMessage("Set " + arenaname + "'s mode to casual");
                    settings.getData().set("arenas." + arenaname + ".mode", "casual");
                    settings.saveData();
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Bomb A")) {
                e.setCancelled(true);
                Player p = (Player) e.getWhoClicked();
                p.sendMessage("Set " + arenaname + "'s Bomb A position!");
                settings.getData().set("arenas." + arenaname + ".bomba.x", p.getLocation().getX());
                settings.getData().set("arenas." + arenaname + ".bomba.y", p.getLocation().getY());
                settings.getData().set("arenas." + arenaname + ".bomba.z", p.getLocation().getZ());
                settings.saveData();
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Bomb B")) {
                e.setCancelled(true);
                Player p = (Player) e.getWhoClicked();
                p.sendMessage("Set " + arenaname + "'s Bomb B position!");
                settings.getData().set("arenas." + arenaname + ".bombb.x", p.getLocation().getX());
                settings.getData().set("arenas." + arenaname + ".bombb.y", p.getLocation().getY());
                settings.getData().set("arenas." + arenaname + ".bombb.z", p.getLocation().getZ());
                settings.saveData();
            }
    }
}

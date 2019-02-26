package me.xboxsignout.rainbow;

import org.bukkit.event.Listener;

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

public class OperatorSelect implements Listener
{
	private Inventory inv;
   
    public OperatorSelect(Plugin p) {
            inv = Bukkit.getServer().createInventory(null, 9, "Operator Select");
            
            ItemStack finka = new ItemStack(Material.STICK);
            ItemMeta finkameta = finka.getItemMeta();
            finkameta.setDisplayName(ChatColor.YELLOW + "Finka");
            finka.setItemMeta(finkameta);
            
            ItemStack montagne = new ItemStack(Material.FLINT);
            ItemMeta montagneameta = montagne.getItemMeta();
            montagneameta.setDisplayName(ChatColor.YELLOW + "Montagne");
            montagne.setItemMeta(montagneameta);
            
            ItemStack frost = new ItemStack(Material.SULPHUR);
            ItemMeta frostmeta = frost.getItemMeta();
            frostmeta.setDisplayName(ChatColor.YELLOW + "Frost");
            frost.setItemMeta(frostmeta);
            
            ItemStack mute = new ItemStack(Material.PAPER);
            ItemMeta mutemeta = mute.getItemMeta();
            mutemeta.setDisplayName(ChatColor.YELLOW + "Mute");
            mute.setItemMeta(mutemeta);
           
            inv.setItem(0, montagne);
            inv.setItem(1, finka);
            inv.setItem(7, frost);
            inv.setItem(8, mute);
           
            Bukkit.getServer().getPluginManager().registerEvents(this, p);
    }
   
    public void show(Player p) {
            p.openInventory(inv);
    }
   
    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) {
            if (!e.getInventory().getName().equalsIgnoreCase(inv.getName())) return;
            if (e.getCurrentItem().getItemMeta() == null) return;
            if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Finka")) {
                    e.setCancelled(true);
                    e.getWhoClicked().getInventory().addItem(new ItemStack(Material.PAPER));
                    e.getWhoClicked().closeInventory();
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Montagne")) {
                    e.setCancelled(true);
                    e.getWhoClicked().getInventory().addItem(new ItemStack(Material.SLIME_BALL));
                    e.getWhoClicked().closeInventory();
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Frost")) {
                    e.setCancelled(true);
                    e.getWhoClicked().getInventory().addItem(new ItemStack(Material.ANVIL));
                    e.getWhoClicked().closeInventory();
            }
            if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Mute")) {
                e.setCancelled(true);
                e.getWhoClicked().getInventory().addItem(new ItemStack(Material.MINECART));
                e.getWhoClicked().closeInventory();
        }
    }
}

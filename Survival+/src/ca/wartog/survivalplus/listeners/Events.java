package ca.wartog.survivalplus.listeners;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.block.data.type.Chest.Type;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class Events implements Listener{
	
	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		Player p = e.getEntity();
		Location loc = p.getLocation();
		List<ItemStack> drops = e.getDrops();
		
		double x = loc.getX();
		double y = loc.getY();
        
        Location x1 = loc.clone();
        Location x2 = loc.clone();
        Location x3 = loc.clone();
     
        x2.setX(x + 1);
        x3.setY(y + 1);
     
        Block block1 = x1.getBlock();
        Block block2 = x2.getBlock();
        Block block3 = x3.getBlock();
        
        org.bukkit.block.data.type.Chest chestData1;
        org.bukkit.block.data.type.Chest chestData2;
        
        if(drops.size() < 27) {
        	block1.setType(Material.CHEST);
        } else {
        	block1.setType(Material.CHEST);
        	block2.setType(Material.CHEST);
        	
        	Chest chest1 = (Chest) block1.getState();
        	Chest chest2 = (Chest) block2.getState();
        	
        	chestData1 = (org.bukkit.block.data.type.Chest) chest1.getBlockData();
        	chestData2 = (org.bukkit.block.data.type.Chest) chest2.getBlockData();
        	
        	chestData1.setType(Type.LEFT);
        	block1.setBlockData(chestData1, true);
        	chestData2.setType(Type.RIGHT);
        	block2.setBlockData(chestData2, true);
        }
        
        Chest bChest = (Chest) x1.getBlock().getState();
        bChest.getInventory().setContents(drops.toArray(new ItemStack[drops.size()]));
        e.getDrops().clear();
        
        block3.setType(Material.OAK_SIGN);
        Sign sign = (Sign) x3.getBlock().getState();
        sign.setLine(1, p.getName() + "'s grave");
        sign.update();
        
        p.sendMessage("§6You died at " + "X: §c" + loc.getBlockX() + "§6 Y: §c" + loc.getBlockY() + "§6 Z: §c" + loc.getBlockZ());
        System.out.println("[SurvivalPlus] " + p.getName() + " died at X: " + loc.getBlockX() + " Y: " + loc.getBlockY() + " Z: " + loc.getBlockZ());
	}
	
}

package io.github.merenze.reporttps;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_12_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_12_R1.PacketPlayOutTitle.EnumTitleAction;

public class ReportTask extends BukkitRunnable {
	private ReportTps plugin;
	
	private long startTime;
	private long endTime;
	
	public ReportTask(ReportTps plugin) {
		this.plugin = plugin;
		this.startTime = System.currentTimeMillis();
	}

	public void run() {
		this.endTime = System.currentTimeMillis();
		String msg = "" + calculateTps(startTime, endTime) + "tps";
		for (Player player:Bukkit.getServer().getOnlinePlayers()) {
			sendTitle(player, msg);
		}
		this.startTime = System.currentTimeMillis();
	}
	
	private void sendTitle(Player player, String text) { 
		IChatBaseComponent chatTitle = ChatSerializer.a("{\"text\": \"" + text + "\"}");
		PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, chatTitle);
		PacketPlayOutTitle length = new PacketPlayOutTitle(5, 20, 5);
		
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(length);
		
	}
	
	private long calculateTps(long startTime, long endTime) {
		long actualTime = endTime-startTime;
		
		return ((long)200) / (actualTime/1000);
	}

}

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
	
	public ReportTask(ReportTps plugin) {
		this.plugin = plugin;
	}

	public void run() {
		// TODO Auto-generated method stub
		for (Player player:Bukkit.getServer().getOnlinePlayers()) {
			sendTitle(player, "FOO!");
		}
	}
	
	private void sendTitle(Player player, String text) { 
		IChatBaseComponent chatTitle = ChatSerializer.a("{\"text\": \"" + text + "\"}");
		PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, chatTitle);
		PacketPlayOutTitle length = new PacketPlayOutTitle(5, 20, 5);
		
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(length);
		
		//Bukkit.getServer().broadcastMessage("Title has been sent!");
	}

}

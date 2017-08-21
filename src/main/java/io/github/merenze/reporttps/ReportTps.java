package io.github.merenze.reporttps;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_12_R1.PacketPlayOutTitle;
import net.minecraft.server.v1_12_R1.PacketPlayOutTitle.EnumTitleAction;

public class ReportTps extends JavaPlugin implements Listener {
	@Override
	public void onEnable() {
		Bukkit.getServer().getPluginManager().registerEvents(this,this);
		//Schedule task here
	}
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		sendTitle(event.getPlayer(), "Foo!");
	}
	
	//I don't remotely understand how this works cause I just took a bunch of code and shoved it in a method
	private void sendTitle(Player player, String text) { 
		IChatBaseComponent chatTitle = ChatSerializer.a("{\"text\": \"" + text + "\"}");
		PacketPlayOutTitle title = new PacketPlayOutTitle(EnumTitleAction.TITLE, chatTitle);
		PacketPlayOutTitle length = new PacketPlayOutTitle(5, 20, 5);
		
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(title);
		((CraftPlayer) player).getHandle().playerConnection.sendPacket(length);
		
		Bukkit.getServer().broadcastMessage("Title has been sent!");
	}
	
}

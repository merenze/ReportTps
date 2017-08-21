package io.github.merenze.reporttps;

import java.util.Timer;

import org.bukkit.plugin.java.JavaPlugin;

import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.IChatBaseComponent.ChatSerializer;

public class ReportTps extends JavaPlugin {
	@Override
	public void onEnable() {
		String text = "Foo";
		IChatBaseComponent chatTitle = ChatSerializer.a("{\"text\": \"" + text + "}");

	}
}

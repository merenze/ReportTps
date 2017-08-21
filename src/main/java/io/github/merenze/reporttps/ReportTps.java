package io.github.merenze.reporttps;

import java.util.Timer;

import org.bukkit.plugin.java.JavaPlugin;

public class ReportTps extends JavaPlugin {
	@Override
	public void onEnable() {
		Timer timer = new Timer();
		timer.schedule(new TimerTitle(), 0, 30000);
	}
}

package io.github.merenze.reporttps;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public class ReportTps extends JavaPlugin{
	@Override
	public void onEnable() {
		//Schedule task here
		BukkitTask report = new ReportTask(this).runTaskTimer(this, 0, 20*30);
	}
}

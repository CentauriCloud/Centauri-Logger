package org.centauri.cloud.logger;

import lombok.Getter;
import org.centauri.cloud.cloud.Cloud;
import org.centauri.cloud.cloud.module.AbstractModule;
import org.centauri.cloud.logger.config.Config;
import org.centauri.cloud.logger.listener.EventListener;

public class CentauriCloudLogger extends AbstractModule {
	
	@Getter private static CentauriCloudLogger instance;
	@Getter private Config config;
	
	@Override
	public void onEnable() {
		instance = this;
		
		try {
			this.config = new Config();
			this.config.loadDefaults();
			this.config.loadValues();
		} catch (Exception ex) {
			Cloud.getLogger().error("Exception", ex);
		}
		
		Cloud.getInstance().getEventManager().registerEventHandler(new EventListener());

		Cloud.getLogger().info("CentauriCloudLogger enabled!");
	}
	
	@Override
	public String getName() {
		return "CentauriCloudLogger";
	}

	@Override
	public String getVersion() {
		return "1.0";
	}

	@Override
	public String getAuthor() {
		return "Centauri Developer Team";
	}

}
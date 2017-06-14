package org.centauri.cloud.logger;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.centauri.cloud.cloud.Cloud;
import org.centauri.cloud.cloud.plugin.AbstractModule;
import org.centauri.cloud.logger.config.Config;
import org.centauri.cloud.logger.listener.EventListener;

@Log4j2
public class CentauriCloudLogger extends AbstractModule {
	
	@Getter private static CentauriCloudLogger instance;
	@Getter private Config config;
	
	@Override
	public void onEnable() {
		instance = this;
		
		Cloud.getInstance().getEventManager().registerEventHandler(new EventListener());
		try {
			this.config = new Config();
			this.config.loadDefaults();
			this.config.loadValues();
		} catch (Exception ex) {
			this.log.catching(ex);
		}
		this.log.info("CentauriCloudLogger enabled!");
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
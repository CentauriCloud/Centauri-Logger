package org.centauri.cloud.logger;

import lombok.extern.log4j.Log4j2;
import org.centauri.cloud.cloud.Cloud;
import org.centauri.cloud.cloud.plugin.AbstractModule;
import org.centauri.cloud.logger.listener.EventListener;

@Log4j2
public class CentauriCloudLogger extends AbstractModule {
	
	@Override
	public void onEnable() {
		Cloud.getInstance().getEventManager().registerEventHandler(new EventListener());
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
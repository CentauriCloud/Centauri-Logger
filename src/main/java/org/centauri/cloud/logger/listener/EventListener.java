package org.centauri.cloud.logger.listener;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.io.FileUtils;
import org.centauri.cloud.cloud.event.Listener;
import org.centauri.cloud.cloud.event.events.DaemonLoadEvent;
import org.centauri.cloud.cloud.event.events.ServerConnectEvent;
import org.centauri.cloud.cloud.event.events.ServerDenyEvent;
import org.centauri.cloud.cloud.event.events.ServerDisconnectEvent;
import org.centauri.cloud.cloud.event.events.RequestServerEvent;
import org.centauri.cloud.logger.CentauriCloudLogger;
import org.centauri.cloud.logger.config.Config;

@Log4j2
public class EventListener {
	
	private Config config = CentauriCloudLogger.getInstance().getConfig();
	
	@Listener
	public void onServerConnect(ServerConnectEvent event) {
		if(config.getServerConnect().isConsole())
			this.log.info("Server connected: {}", event.getServer().getName());
	}

	@Listener
	public void onServerDisconnect(ServerDisconnectEvent event) {
		if(config.getServerDisconnect().isConsole())
			this.log.info("Server disconnected: {}", event.getServer().getName());
	}
	
	@Listener
	public void onServerDeny(ServerDenyEvent event) {
		if(config.getServerDeny().isConsole())
			this.log.info("Server connection denied: {}", event.getHost());
	}
	
	@Listener
	public void onServerLoad(DaemonLoadEvent event) {
		if(config.getServerLoad().isConsole())
			this.log.info("Server: {} | CpuLoad: {} | free Ram: {}",event.getServer().getName(), event.getCpuLoad(), FileUtils.byteCountToDisplaySize(event.getFreeRam()));
	}
	
	@Listener
	public void onRequestServer(RequestServerEvent event) {
		if(config.getRequestServer().isConsole())
			this.log.info("Request server for template {}!", event.getTemplate().getName());
	}
	
}
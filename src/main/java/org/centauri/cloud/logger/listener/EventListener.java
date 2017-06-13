package org.centauri.cloud.logger.listener;

import lombok.extern.log4j.Log4j2;
import org.centauri.cloud.cloud.event.Listener;
import org.centauri.cloud.cloud.event.events.ServerConnectEvent;
import org.centauri.cloud.cloud.event.events.ServerDenyEvent;
import org.centauri.cloud.cloud.event.events.ServerDisconnectEvent;

@Log4j2
public class EventListener {
	
	@Listener
	public void onServerConnect(ServerConnectEvent event) {
		this.log.info("Server connected: {}", event.getServer().getName());
	}

	@Listener
	public void onServerDisconnect(ServerDisconnectEvent event) {
		this.log.info("Server disconnected: {}", event.getServer().getName());
	}
	
	@Listener
	public void onServerDeny(ServerDenyEvent event) {
		this.log.info("Server connection denied: {}", event.getHost());
	}
	
}
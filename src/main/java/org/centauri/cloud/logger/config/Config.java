package org.centauri.cloud.logger.config;

import com.timvisee.yamlwrapper.YamlConfiguration;
import java.io.File;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.nio.file.Files;
import lombok.Getter;
import org.centauri.cloud.logger.CentauriCloudLogger;

@Getter
public class Config {

	private File configFile;
	private YamlConfiguration yamlConfig;
	
	@LogAble private LoggerSetting consoleCommand;
	@LogAble private LoggerSetting serverConnect;
	@LogAble private LoggerSetting serverDisconnect;
	@LogAble private LoggerSetting serverDeny;
	@LogAble private LoggerSetting serverLoad;
	
	public Config() throws Exception {
		new File("modules/CentauriCloudLogger/").mkdir();
		this.configFile = new  File("modules/CentauriCloudLogger/config.yml");
		if(!this.configFile.exists())
			Files.copy(this.getClass().getResourceAsStream("/config.yml"), this.configFile.toPath());
		
		this.yamlConfig = YamlConfiguration.loadFromFile(this.configFile);
	}

	public void loadDefaults() throws Exception {
		//Use only for newer versions, not basic; write basic things into config.yml(resources)
		boolean save = false;
		
		save = save || setDefault("example", true);//Template
		
		if(save)
			this.yamlConfig.save(this.configFile);
	}
	
	public void loadValues() throws Exception {
		for(Field field : Config.class.getDeclaredFields()) {
			if(field.getAnnotation(LogAble.class) != null && field.getName() != null) {
				LoggerSetting setting = new LoggerSetting(field.getName());
				field.setAccessible(true);
				field.set(this, setting);
				field.setAccessible(false);
			}
		}
	}
	
	private boolean setDefault(String key, Object value) {
		if(!this.yamlConfig.isSet(key)) {
			this.yamlConfig.set(key, value);
			return true;
		}
		
		return false;
	}
	
	public static class LoggerSetting {
		@Getter private boolean console;
		@Getter private boolean log;
		
		public LoggerSetting(String path) {
			this.console = CentauriCloudLogger.getInstance().getConfig().yamlConfig.getBoolean(path + ".console");
			this.log = CentauriCloudLogger.getInstance().getConfig().yamlConfig.getBoolean(path + ".log");
		}
	}
	
	@Retention(RetentionPolicy.RUNTIME)
	public static @interface LogAble {
		
	}
}
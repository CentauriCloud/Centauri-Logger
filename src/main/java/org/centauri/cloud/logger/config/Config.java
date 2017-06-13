package org.centauri.cloud.logger.config;

import com.timvisee.yamlwrapper.YamlConfiguration;
import java.io.File;
import java.nio.file.Files;

public class Config {

	private File configFile;
	private YamlConfiguration yamlConfig;
	
	public Config() throws Exception {
		new File("modules/CentauriCloudLogger/").mkdir();
		this.configFile = new  File("modules/CentauriCloudLogger/config.yml");
		if(!this.configFile.exists())
			Files.copy(this.getClass().getResourceAsStream("/config.yml"), this.configFile.toPath());
		
		this.yamlConfig = YamlConfiguration.loadFromFile(this.configFile);
		this.loadDefaults();
	}

	private void loadDefaults() throws Exception {
		//Use only for newer versions, not basic; write basic things into config.yml(resources)
		boolean save = false;
		
		save = save || setDefault("example", true);//Template
		
		if(save)
			this.yamlConfig.save(this.configFile);
	}
	
	private boolean setDefault(String key, Object value) {
		if(!this.yamlConfig.isSet(key)) {
			this.yamlConfig.set(key, value);
			return true;
		}
		
		return false;
	}
}
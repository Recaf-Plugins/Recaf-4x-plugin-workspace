package org.example.plugin;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import software.coley.recaf.analytics.logging.Logging;
import software.coley.recaf.plugin.Plugin;
import software.coley.recaf.plugin.PluginInformation;

@Dependent
@PluginInformation(id = "##ID##", version = "##VERSION##", name = "##NAME##", description = "##DESC##")
public class ExamplePlugin implements Plugin {
	private static final Logger logger = Logging.get(ExamplePlugin.class);

	@Inject
	public ExamplePlugin() {
		// You can inject Recaf's services in this constructor.
		//  - https://recaf.coley.software/dev/plugins-and-scripts/plugins.html
	}

	@Override
	public void onEnable() {
		logger.info("Hello from the example plugin");
	}

	@Override
	public void onDisable() {
		logger.info("goodbye from the example plugin");
	}
}

package org.example.plugin;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import software.coley.recaf.analytics.logging.Logging;
import software.coley.recaf.plugin.Plugin;
import software.coley.recaf.plugin.PluginInformation;

// Things worth noting when creating plugins:
//
// 1. The templated ##ID## and others are filled in when running 'gradlew build'
//    - Edit values via 'build.gradle' in the ext { ... } block.
//
// 2. Plugins can @Inject any of Recaf's services into the plugin constructor.
//    - Just add the parameter of the service you want to inject.
//
// 3. Plugins operate as their own dependency-injection containers.
//    - Plugins can define their own @ApplicationScoped and @Dependent scoped beans
//      and inject them within the plugin anywhere.
//    - They also inherit all of Recaf's beans / services, so you can make
//      an @ApplicationScoped bean in your plugin code, and inject Recaf services
//      in its constructor as well.
//
// 4. Keep UI-only behavior in a separate bean that observes UiInitializationEvent.
//    - See ExampleUi: headless startup does not fire this event, so the plugin's
//      core behavior can still load when JavaFX is unavailable.
//    - Use Instance<MainMenuProvider> so the UI service is only requested once
//      the UI initialization event is received.
//
@Dependent
@PluginInformation(id = "##ID##", version = "##VERSION##", name = "##NAME##", description = "##DESC##")
public class ExamplePlugin implements Plugin {
	private static final Logger logger = Logging.get(ExamplePlugin.class);

	@Inject
	public ExamplePlugin() {
		// You can add Recaf's services in this constructor as parameters to inject them
		//  - https://recaf.coley.software/dev/plugins-and-scripts/plugins.html
	}

	@Override
	public void onEnable() {
		logger.info("Hello from the example plugin");
	}

	@Override
	public void onDisable() {
		logger.info("Goodbye from the example plugin");
	}
}

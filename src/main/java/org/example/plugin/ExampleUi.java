package org.example.plugin;

import jakarta.annotation.PreDestroy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.event.Observes;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import org.slf4j.Logger;
import software.coley.recaf.analytics.logging.Logging;
import software.coley.recaf.cdi.UiInitializationEvent;
import software.coley.recaf.ui.menubar.MainMenuProvider;
import software.coley.recaf.util.FxThreadUtil;

/**
 * When creating a plugin, you should separate your core logic from your user interface.
 * This will allow your plugin to only create JavaFX UI elements when JavaFX is present.
 * Recaf may not have JavaFX available if it is run via {@code --headless}.
 */
@ApplicationScoped
public class ExampleUi {
	private static final Logger logger = Logging.get(ExampleUi.class);

	// Keep the UI service optional until the UI initialization event is received.
	private final Instance<MainMenuProvider> mainMenuProvider;
	private Menu menu;

	@Inject
	public ExampleUi(Instance<MainMenuProvider> mainMenuProvider) {
		this.mainMenuProvider = mainMenuProvider;
	}

	/**
	 * Add a small example menu once that logs a message.
	 * This method isn't run when Recaf is launched with {@code --headless}.
	 *
	 * @param event
	 *		UI initialization event.
	 */
	public void onUiInitialization(@Observes UiInitializationEvent event) {
		Menu exampleMenu = new Menu("Example plugin");
		MenuItem logItem = new MenuItem("Log a message");
		logItem.setOnAction(ignored -> logger.info("Hello from the example plugin UI"));
		exampleMenu.getItems().add(logItem);

		mainMenuProvider.get().getMainMenu().getMenus().add(exampleMenu);
		menu = exampleMenu;
		logger.info("Example plugin UI initialized");
	}

	/**
	 * When the plugin is shut down, remove the menu.
	 */
	@PreDestroy
	public void onDestroy() {
		Menu existingMenu = menu;
		if (existingMenu != null)
			FxThreadUtil.run(() -> mainMenuProvider.get().getMainMenu().getMenus().remove(existingMenu));
	}
}

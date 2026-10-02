package com.northarrow;

import com.google.inject.Provides;
import javax.inject.Inject;
import net.runelite.client.config.ConfigManager;
import net.runelite.client.plugins.Plugin;
import net.runelite.client.plugins.PluginDescriptor;
import net.runelite.client.ui.overlay.OverlayManager;

@PluginDescriptor(
	name = "North Arrow",
	description = "Draws an arrow next to your character that always points north",
	tags = {"north", "arrow", "compass", "direction", "orientation"}
)
public class NorthArrowPlugin extends Plugin
{
	@Inject
	private OverlayManager overlayManager;

	@Inject
	private NorthArrowOverlay overlay;

	@Override
	protected void startUp()
	{
		overlayManager.add(overlay);
	}

	@Override
	protected void shutDown()
	{
		overlayManager.remove(overlay);
	}

	@Provides
	NorthArrowConfig provideConfig(ConfigManager configManager)
	{
		return configManager.getConfig(NorthArrowConfig.class);
	}
}

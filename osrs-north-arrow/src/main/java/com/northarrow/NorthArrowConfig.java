package com.northarrow;

import java.awt.Color;
import net.runelite.client.config.Alpha;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.Range;

@ConfigGroup("northarrow")
public interface NorthArrowConfig extends Config
{
	@Alpha
	@ConfigItem(
		keyName = "color",
		name = "Arrow color",
		description = "Color of the north arrow",
		position = 0
	)
	default Color color()
	{
		return new Color(255, 40, 40, 220);
	}

	@Range(min = 1, max = 10)
	@ConfigItem(
		keyName = "distance",
		name = "Distance (tiles)",
		description = "How far north of your character the arrow tip sits",
		position = 1
	)
	default int distance()
	{
		return 2;
	}

	@Range(min = 4, max = 40)
	@ConfigItem(
		keyName = "headSize",
		name = "Arrowhead size",
		description = "Size of the arrowhead in pixels",
		position = 2
	)
	default int headSize()
	{
		return 12;
	}

	@ConfigItem(
		keyName = "showShaft",
		name = "Show shaft",
		description = "Draw a line from your character to the arrowhead",
		position = 3
	)
	default boolean showShaft()
	{
		return true;
	}

	@ConfigItem(
		keyName = "showLabel",
		name = "Show \"N\" label",
		description = "Draw an N just past the arrow tip",
		position = 4
	)
	default boolean showLabel()
	{
		return true;
	}
}

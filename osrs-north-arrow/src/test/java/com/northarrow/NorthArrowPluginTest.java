package com.northarrow;

import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class NorthArrowPluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(NorthArrowPlugin.class);
		RuneLite.main(args);
	}
}

package com.markus1002.incubation.core.util;

import net.minecraft.util.ResourceLocation;

public class Reference
{
	public static final String MOD_ID = "incubation";
	
	public static ResourceLocation location(String name)
	{
		return new ResourceLocation(MOD_ID, name);
	}
}
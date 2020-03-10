package com.markus1002.incubation.core;

import com.markus1002.incubation.core.registry.ModFeatures;
import com.markus1002.incubation.core.util.EventHandler;
import com.markus1002.incubation.core.util.VanillaCompatibility;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod("incubation")
public class Incubation
{
	public Incubation()
	{
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
		
		MinecraftForge.EVENT_BUS.register(new EventHandler());
		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setup(final FMLCommonSetupEvent event)
	{
		VanillaCompatibility.setupVanillaCompatibility();
		ModFeatures.setupFeatures();	
	}
}
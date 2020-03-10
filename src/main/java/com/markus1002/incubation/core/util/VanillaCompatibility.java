package com.markus1002.incubation.core.util;

import com.markus1002.incubation.core.registry.ModBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ComposterBlock;
import net.minecraft.block.FireBlock;
import net.minecraft.util.IItemProvider;

public class VanillaCompatibility
{
	public static void setupVanillaCompatibility()
	{
		registerCompostable(ModBlocks.CHICKEN_NEST.asItem(), 0.65F);

		registerFlammable(ModBlocks.CHICKEN_NEST, 60, 20);
	}

	public static void registerCompostable(IItemProvider itemIn, float chance)
	{
		ComposterBlock.CHANCES.put(itemIn, chance);
	}

	public static void registerFlammable(Block blockIn, int encouragement, int flammability)
	{
		((FireBlock)Blocks.FIRE).setFireInfo(blockIn, encouragement, flammability);
	}
}
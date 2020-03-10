package com.markus1002.incubation.core.registry;

import com.markus1002.incubation.common.block.ChickenNestBlock;
import com.markus1002.incubation.core.util.Reference;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlocks
{
    public static final Block CHICKEN_NEST = new ChickenNestBlock(Block.Properties.create(Material.ORGANIC, MaterialColor.YELLOW).hardnessAndResistance(0.5F).sound(SoundType.PLANT));
    
    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event)
    {
    	registerBlock(CHICKEN_NEST, ItemGroup.DECORATIONS, "chicken_nest");
    }
    
	private static void registerBlock(Block block, ItemGroup group, String name)
	{
		registerBlock(block, name);
		BlockItem blockItem = new BlockItem(block, new Item.Properties().group(group));
		blockItem.setRegistryName(block.getRegistryName());
        ForgeRegistries.ITEMS.register(blockItem);
	}
	
	private static void registerBlock(Block block, String name)
	{
		block.setRegistryName(Reference.location(name));
        ForgeRegistries.BLOCKS.register(block);
	}
}
package com.markus1002.incubation.core.registry;

import com.markus1002.incubation.common.item.ModFoods;
import com.markus1002.incubation.common.item.ScrambledEggsItem;
import com.markus1002.incubation.core.util.Reference;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItems
{
	public static final Item FRIED_EGG = new Item((new Item.Properties()).group(ItemGroup.FOOD).food(ModFoods.FRIED_EGG));
	public static final Item SCRAMBLED_EGGS = new ScrambledEggsItem((new Item.Properties()).maxStackSize(1).group(ItemGroup.FOOD).food(ModFoods.SCRAMBLED_EGGS));
	
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event)
    {
    	registerItem(FRIED_EGG, "fried_egg");
    	registerItem(SCRAMBLED_EGGS, "scrambled_eggs");
	}
    
	private static void registerItem(Item item, String name)
	{
		item.setRegistryName(Reference.location(name));
        ForgeRegistries.ITEMS.register(item);
	}
}
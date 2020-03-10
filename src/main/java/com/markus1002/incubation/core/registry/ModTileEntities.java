package com.markus1002.incubation.core.registry;

import com.markus1002.incubation.common.tileentity.ChickenNestTileEntity;
import com.markus1002.incubation.core.util.Reference;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModTileEntities
{
	public static final TileEntityType<?> CHICKEN_NEST_TILE = TileEntityType.Builder.create(ChickenNestTileEntity::new, ModBlocks.CHICKEN_NEST).build(null);

	@SubscribeEvent
	public static void registerTileEntityTypes(RegistryEvent.Register<TileEntityType<?>> event)
	{
		registerTileEntity(CHICKEN_NEST_TILE, "chicken_nest");
	}

	private static void registerTileEntity(TileEntityType<?> tileentity, String name)
	{
		tileentity.setRegistryName(Reference.location(name));
		ForgeRegistries.TILE_ENTITIES.register(tileentity);
	}
}
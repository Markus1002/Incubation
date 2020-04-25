package com.markus1002.incubation.core.registry;

import com.markus1002.incubation.common.world.gen.feature.ChickenNestFeature;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModFeatures
{
	public static final Feature<NoFeatureConfig> CHICKEN_NEST = new ChickenNestFeature(NoFeatureConfig::deserialize);

	@SubscribeEvent
	public static void registerFeatures(RegistryEvent.Register<Feature<?>> event)
	{
		registerFeature(CHICKEN_NEST, "chicken_nest");
	}

	private static void registerFeature(Feature<?> feature, String name)
	{
		feature.setRegistryName(name);
		ForgeRegistries.FEATURES.register(feature);
	}

	public static void setupFeatures()
	{
		for(Biome biome : ForgeRegistries.BIOMES.getValues())
		{
			if (BiomeDictionary.getTypes(biome).contains(Type.FOREST) && doesCreatureSpawnInBiome(EntityType.CHICKEN, EntityClassification.CREATURE, biome))
			{
				biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, CHICKEN_NEST.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.CHANCE_HEIGHTMAP_DOUBLE.configure(new ChanceConfig(40))));
			}
		}
	}

	private static boolean doesCreatureSpawnInBiome(EntityType<?> entityType, EntityClassification classification, Biome biome)
	{
		for (Biome.SpawnListEntry entry : biome.getSpawns(classification))
		{
			if (entry.entityType == entityType)
			{
				return true;
			}
		}

		return false;
	}
}
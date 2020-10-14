package com.markus1002.incubation.core.registry;

import com.markus1002.incubation.common.world.gen.feature.ChickenNestFeature;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.world.MobSpawnInfoBuilder;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModFeatures
{
	public static final Feature<NoFeatureConfig> CHICKEN_NEST = new ChickenNestFeature(NoFeatureConfig.field_236558_a_);

	public static void registerFeatures(RegistryEvent.Register<Feature<?>> event)
	{
		// make sure it's the expected registry
		final ResourceLocation regName = ForgeRegistries.FEATURES.getRegistryName();
		if (!event.getName().equals(regName)) {
			return;
		}
		IForgeRegistry<Feature<?>> reg = event.getRegistry();

		// register all features below this point
		registerFeature(reg, CHICKEN_NEST, "chicken_nest");
	}

	private static void registerFeature(IForgeRegistry<Feature<?>> reg, Feature<?> feature, String name)
	{
		feature.setRegistryName(name);
		reg.register(feature);
	}


	public static void addFeaturesToBiome(BiomeLoadingEvent biome)
	{
		if (biome.getCategory() == Biome.Category.FOREST && doesCreatureSpawnInBiome(EntityType.CHICKEN, biome.getSpawns()))

			biome.getGeneration().getFeatures(GenerationStage.Decoration.VEGETAL_DECORATION).add(
					configuredFeatureSupplier(CHICKEN_NEST)
			);
	}

	private static Supplier<ConfiguredFeature<?,?>> configuredFeatureSupplier(Feature<NoFeatureConfig> feature) {
		return () -> feature.withConfiguration(IFeatureConfig.NO_FEATURE_CONFIG).withPlacement(Placement.field_242898_b.configure(new ChanceConfig(32)));
	}

	private static boolean doesCreatureSpawnInBiome(EntityType<?> entityType, MobSpawnInfoBuilder spawns)
	{
		for (MobSpawnInfo.Spawners spawner : spawns.getSpawner(EntityClassification.CREATURE)) {
			if (spawner.field_242588_c == entityType) {
				return true;
			}
		}
		return false;
	}
}

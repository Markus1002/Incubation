package com.markus1002.incubation.common.world.gen.feature;

import java.util.Random;
import java.util.function.Function;

import com.markus1002.incubation.common.block.ChickenNestBlock;
import com.markus1002.incubation.core.registry.ModBlocks;
import com.mojang.datafixers.Dynamic;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationSettings;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class ChickenNestFeature extends Feature<NoFeatureConfig>
{
	public ChickenNestFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> configFactoryIn)
	{
		super(configFactoryIn);
	}

	public boolean place(IWorld worldIn, ChunkGenerator<? extends GenerationSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config)
	{
		BlockState blockstate = ModBlocks.CHICKEN_NEST.getDefaultState().with(ChickenNestBlock.EGGS, 1 + rand.nextInt(3));

		int i = worldIn.getHeight(Heightmap.Type.WORLD_SURFACE, pos.getX(), pos.getZ());
		BlockPos blockpos = new BlockPos(pos.getX(), i, pos.getZ());

		if (worldIn.isAirBlock(blockpos) && worldIn.getBlockState(blockpos.down()).getBlock() == Blocks.GRASS_BLOCK)
		{
			worldIn.setBlockState(blockpos, blockstate, 2);
		}

		return true;
	}
}
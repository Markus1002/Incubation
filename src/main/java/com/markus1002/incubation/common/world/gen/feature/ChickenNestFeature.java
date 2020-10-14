package com.markus1002.incubation.common.world.gen.feature;

import java.util.Random;

import com.markus1002.incubation.common.block.ChickenNestBlock;
import com.markus1002.incubation.core.registry.ModBlocks;
import com.mojang.serialization.Codec;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

public class ChickenNestFeature extends Feature<NoFeatureConfig>
{
	public ChickenNestFeature(Codec<NoFeatureConfig> config)
	{
		super(config);
	}

	@Override
	public boolean func_241855_a(ISeedReader worldIn, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
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

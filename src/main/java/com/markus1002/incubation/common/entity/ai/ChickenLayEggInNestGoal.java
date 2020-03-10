package com.markus1002.incubation.common.entity.ai;

import java.util.Random;

import com.markus1002.incubation.common.block.ChickenNestBlock;
import com.markus1002.incubation.core.registry.ModBlocks;

import net.minecraft.block.BlockState;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorldReader;

public class ChickenLayEggInNestGoal extends MoveToBlockGoal
{
	private final ChickenEntity chicken;

	public ChickenLayEggInNestGoal(ChickenEntity chickenIn, double speedIn)
	{
		super(chickenIn, speedIn, 16);
		this.chicken = chickenIn;
	}

	public boolean shouldExecute()
	{
		return !this.chicken.isChild() && !this.chicken.isChickenJockey() && this.chicken.timeUntilNextEgg < 800 && super.shouldExecute();
	}

	protected int getRunDelay(CreatureEntity creatureIn)
	{
		return 40;
	}

	public void tick()
	{
		super.tick();
		if (this.getIsAboveDestination() && !this.chicken.isChild() && !this.chicken.isChickenJockey() && this.chicken.timeUntilNextEgg < 800)
		{
			BlockPos blockpos = this.destinationBlock.up();
			BlockState blockstate = this.chicken.world.getBlockState(blockpos);

			if (blockstate.getBlock() == ModBlocks.CHICKEN_NEST)
			{
				int i = blockstate.get(ChickenNestBlock.EGGS);
				if (i < 6)
				{
					this.chicken.world.setBlockState(blockpos, blockstate.with(ChickenNestBlock.EGGS, Integer.valueOf(i + 1)), 2);

					Random random = chicken.getRNG();
					this.chicken.playSound(SoundEvents.ENTITY_CHICKEN_EGG, 1.0F, (random.nextFloat() - random.nextFloat()) * 0.2F + 1.0F);
					this.chicken.timeUntilNextEgg = random.nextInt(6000) + 6000;
				}
			}
		}
	}

	protected boolean shouldMoveTo(IWorldReader worldIn, BlockPos pos)
	{
		BlockState blockstate = this.chicken.world.getBlockState(pos.up());
		if (blockstate.getBlock() == ModBlocks.CHICKEN_NEST && blockstate.get(ChickenNestBlock.EGGS) < 6 && this.chicken.timeUntilNextEgg < 800)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
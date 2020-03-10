package com.markus1002.incubation.common.block;

import com.markus1002.incubation.common.tileentity.ChickenNestTileEntity;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.ContainerBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

public class ChickenNestBlock extends ContainerBlock
{
	protected static final VoxelShape SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 3.0D, 16.0D);
	public static final IntegerProperty EGGS = IntegerProperty.create("eggs", 0, 6);

	public ChickenNestBlock(Properties properties)
	{
		super(properties);
		this.setDefaultState(this.stateContainer.getBaseState().with(EGGS, Integer.valueOf(0)));
	}

	public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context)
	{
		return SHAPE;
	}

	public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos)
	{
		return !stateIn.isValidPosition(worldIn, currentPos) ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
	}

	public boolean onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit)
	{
		if (player.isAllowEdit())
		{
			int i = state.get(EGGS);
			ItemStack itemstack = player.getHeldItem(handIn);
			if (itemstack.getItem() == Items.EGG)
			{
				if (i < 6)
				{
					if (!player.abilities.isCreativeMode && !worldIn.isRemote)
					{
						itemstack.shrink(1);
					}
					worldIn.setBlockState(pos, state.with(EGGS, Integer.valueOf(i + 1)), 2);

					return true;
				}
			}
			if (i > 0)
			{
				spawnAsEntity(worldIn, pos, new ItemStack(Items.EGG));
				worldIn.setBlockState(pos, state.with(EGGS, Integer.valueOf(i - 1)), 2);

				return true;
			}
			return false;
		}
		else
		{
			return super.onBlockActivated(state, worldIn, pos, player, handIn, hit);
		}
	}

	public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos)
	{
		return worldIn.getBlockState(pos.down()).getMaterial().isSolid();
	}

	protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
	{
		builder.add(EGGS);
	}

	public TileEntity createNewTileEntity(IBlockReader worldIn)
	{
		return new ChickenNestTileEntity();
	}

	public BlockRenderType getRenderType(BlockState state)
	{
		return BlockRenderType.MODEL;
	}
}
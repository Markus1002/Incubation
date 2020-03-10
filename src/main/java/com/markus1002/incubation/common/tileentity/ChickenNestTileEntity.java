package com.markus1002.incubation.common.tileentity;

import com.markus1002.incubation.common.block.ChickenNestBlock;
import com.markus1002.incubation.core.registry.ModTileEntities;
import com.markus1002.incubation.core.util.InventoryUtil;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tileentity.HopperTileEntity;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;

public class ChickenNestTileEntity extends TileEntity implements ITickableTileEntity
{
	public ChickenNestTileEntity()
	{
		super(ModTileEntities.CHICKEN_NEST_TILE);
	}
	
	public void tick()
	{
		if (this.world != null && !this.world.isRemote)
		{
			BlockState blockstate = this.world.getBlockState(this.pos);
			
			int i = blockstate.get(ChickenNestBlock.EGGS);
			BlockPos blockpos = pos.down();
			
			if (i > 0 && this.world.getBlockState(blockpos).hasTileEntity())
			{
				TileEntity tileentity = this.world.getTileEntity(blockpos);
				if (tileentity instanceof HopperTileEntity)
				{
					if (!((HopperTileEntity)tileentity).isOnTransferCooldown() && InventoryUtil.insertItemToHopper(tileentity, new ItemStack(Items.EGG), -1))
					{
						this.world.setBlockState(pos, blockstate.with(ChickenNestBlock.EGGS, Integer.valueOf(i - 1)), 2);
					}
				}
			}
		}
	}
}
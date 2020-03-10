package com.markus1002.incubation.core.util;

import javax.annotation.Nonnull;

import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.HopperTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.VanillaHopperItemHandler;

public class InventoryUtil
{
	public static boolean insertItemToHopper(TileEntity tileentity, @Nonnull ItemStack stack, int preferredSlot)
	{
		if (stack.isEmpty()) return false;

		HopperTileEntity hopper = (HopperTileEntity) tileentity;
		VanillaHopperItemHandler inventory = new VanillaHopperItemHandler(hopper);

		ItemStack remainder = stack;

		if (preferredSlot >= 0 && preferredSlot < inventory.getSlots())
		{
			remainder = inventory.insertItem(preferredSlot, stack, false);
		}

		if (!remainder.isEmpty())
		{
			remainder = ItemHandlerHelper.insertItemStacked(inventory, remainder, false);
		}

		if (!remainder.isEmpty())
		{
			return false;
		}

		if (!hopper.mayTransfer())
		{
			hopper.setTransferCooldown(8);
		}

		return true;
	}
}
package com.markus1002.incubation.core.util;

import com.markus1002.incubation.common.entity.ai.ChickenLayEggInNestGoal;

import net.minecraft.entity.passive.ChickenEntity;
import net.minecraftforge.event.entity.EntityEvent.EnteringChunk;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventHandler
{
	@SubscribeEvent
	public void onEnterChunk(EnteringChunk event)
	{
		if(event.getEntity() instanceof ChickenEntity)
		{
			ChickenEntity chicken = (ChickenEntity)event.getEntity();

			if(!chicken.goalSelector.goals.stream().anyMatch((goal) -> goal.getGoal() instanceof ChickenLayEggInNestGoal))
			{
				chicken.goalSelector.addGoal(2, new ChickenLayEggInNestGoal(chicken, 1.0D));
			}
		}
	}
}
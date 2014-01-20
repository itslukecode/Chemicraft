package com.itsluke.chemicraft.main;

import net.minecraft.util.DamageSource;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class ChemicraftEventHooks {

	@ForgeSubscribe
	public void onEntityUpdate(LivingUpdateEvent event) {
		if(event.entityLiving.isPotionActive(ChemicraftMain.chemicalPoisoning)) {
			if(event.entityLiving.worldObj.rand.nextInt(200) == 0){
				event.entityLiving.attackEntityFrom(DamageSource.generic, 2);
			}
		}
	}
}

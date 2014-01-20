package com.itsluke.chemicraft.potions;

import net.minecraft.potion.Potion;

public class PotionChemicalPoisoning extends Potion{
	
	public PotionChemicalPoisoning(int par1, boolean par2, int par3){
		super(par1, par2, par3);
		this.setPotionName("potion.chemicalpoisoning");
	}
	
	public Potion setIconIndex(int par1, int par2) {
		super.setIconIndex(par1, par2);
		return this;
	}
}

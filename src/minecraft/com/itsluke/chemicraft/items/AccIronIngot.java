package com.itsluke.chemicraft.items;

import com.itsluke.chemicraft.main.ChemicraftMain;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionAbsoption;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionHealthBoost;
import net.minecraft.potion.PotionHelper;
import net.minecraft.world.World;

public class AccIronIngot extends Item{
	
	public AccIronIngot(int par1) {
		super(par1);
	}
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer) {
		par3EntityPlayer.addPotionEffect(new PotionEffect(ChemicraftMain.chemicalPoisoning.id, 200, 0));
		
		return par1ItemStack;
	}
}
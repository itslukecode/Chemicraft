package com.itsluke.chemicraft.main;

import java.lang.reflect.Field;
import com.itsluke.chemicraft.potions.*;
import java.lang.reflect.Modifier;
import net.minecraft.block.Block;
import net.minecraft.block.BlockOre;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.LanguageRegistry;

import com.itsluke.chemicraft.generators.*;

@Mod(modid = "chemicraft", name = "Chemicraft", version = "0.1.0 Pre-Alpha")
@NetworkMod(clientSideRequired = true, serverSideRequired = false)

public class ChemicraftMain {

	public static final Block acceloratableIron = new Block(2001, Material.ground).setCreativeTab(CreativeTabs.tabBlock)
			.setUnlocalizedName("acceloratableIron").setLightValue(10.0F)
			.setStepSound(Block.soundStoneFootstep).setTextureName("chemicraft:ironOre").setHardness(2.0F);
	
	public static Potion chemicalPoisoning;
	public static final Item accIronIngot = new Item(1999).setCreativeTab(CreativeTabs.tabMaterials).setUnlocalizedName("accironingot");
	@EventHandler
	public void preinit(FMLPreInitializationEvent ev)
	{
		Potion[] potionTypes = null;
		
		for(Field f : Potion.class.getDeclaredFields()){
			f.setAccessible(true);
			try{
				if(f.getName().equals("potionTypes") || f.getName().equals("field_76425_a")){
					Field modfield = Field.class.getDeclaredField("modifiers");
					modfield.setAccessible(true);
					modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);
					
					potionTypes = (Potion[])f.get(null);
					final Potion[] newPotionTypes = new Potion[256];
					System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
					f.set(null, newPotionTypes);
				}
			}
			catch(Exception e) {
				System.err.println("Severe error, please report this to itsluke:");
				System.err.println(e);
			}
		}
		
		MinecraftForge.EVENT_BUS.register(new ChemicraftEventHooks());
	}
	
	@EventHandler
	public void load(FMLInitializationEvent ev)
	{
		chemicalPoisoning = (new PotionChemicalPoisoning(32, false, 0));
		LanguageRegistry.addName(acceloratableIron, "Acceloratable Iron");
		LanguageRegistry.addName(accIronIngot, "Acceloratable Iron Ingot");
		GameRegistry.registerBlock(acceloratableIron, "acceloratable iron");
		GameRegistry.registerItem(accIronIngot, "accel iron ingot");
		GameRegistry.registerWorldGenerator(new AcceloratableIronGenerator());
		MinecraftForge.setBlockHarvestLevel(acceloratableIron, "pickaxe", 2);
		GameRegistry.addSmelting(acceloratableIron.blockID, new ItemStack(accIronIngot), 2F);
	}
}

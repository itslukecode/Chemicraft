package com.itsluke.chemicraft.generators;

import java.util.Random;

import com.itsluke.chemicraft.main.ChemicraftMain;

import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class AcceloratableIronGenerator implements IWorldGenerator{

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
		switch(world.provider.dimensionId){
		case -1:
			generateNether(world, random, chunkX *16, chunkZ * 16);
			break;
		case 0:
			generateSurface(world, random, chunkX * 16, chunkZ * 16);
			break;
		case 1:
			generateEnd(world, random, chunkX * 16, chunkZ * 16);
			break;
	}
	}
	
	private void generateEnd(World world, Random random, int i, int j) {}
	
	private void generateSurface(World world, Random random, int i, int j) {
		for(int k = 0; k < 10; k++) {
		    int acironXCoord = i + random.nextInt(16);
		    int acironYCoord = random.nextInt(64);
		    int acironZCoord = j + random.nextInt(16);
		    
		    (new WorldGenMinable(ChemicraftMain.acceloratableIron.blockID, 13)).generate(world, random, acironXCoord, acironYCoord, acironZCoord);
		}
	}
	
	private void generateNether(World world, Random random, int i, int j) {}
}
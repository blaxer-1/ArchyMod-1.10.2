package fr.darkprod.archymod.proxy;

import fr.darkprod.archymod.blocks.BlockRenderRegister;
import fr.darkprod.archymod.events.ClientEvents;
import fr.darkprod.archymod.init.ModBlocks;
import fr.darkprod.archymod.init.ModItems;
import net.minecraftforge.common.MinecraftForge;

public class ClientProxy implements CommonProxy {

	@Override
	public void preInit() {
		
	}
	@Override
	public void init() {
		ModItems.registerRenders();
		BlockRenderRegister.init();
		MinecraftForge.EVENT_BUS.register(new ClientEvents());
	}


	@Override
	public void postInit() {
	}

}

package fr.darkprod.archymod.fuel;

import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.IFuelHandler;

public class IFuelHandlerArchydium implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel) {
		// TODO Auto-generated method stub
		return 200*10;
	}

}

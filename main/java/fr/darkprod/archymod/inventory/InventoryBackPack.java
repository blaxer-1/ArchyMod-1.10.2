package fr.darkprod.archymod.inventory;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;

public class InventoryBackPack extends InventoryBasic {
	private final ItemStack stack;
	
	
	
	public InventoryBackPack(ItemStack stack) {
		super("BackPack",false,45);
		this.stack = stack;
	}
	@Override
	public void openInventory(EntityPlayer p) {
		readFromNBT();
		super.openInventory(p);
	}
	@Override
	public void closeInventory(EntityPlayer p) {
		writeToNBT();
		super.openInventory(p);
	}
	
	@Override
	public void markDirty() {
		writeToNBT();
		super.markDirty();
	}
	public void writeToNBT() {
		NBTTagCompound tag = stack.getTagCompound();
		if (tag == null) {
			tag =  new NBTTagCompound();
			stack.setTagCompound(tag = new NBTTagCompound());
		}
		NBTTagList list = new NBTTagList();
		for(int i = 0; i < getSizeInventory(); i++) {
			ItemStack stack = getStackInSlot(i);
			
			if (stack != null) {
				NBTTagCompound slotTag = new NBTTagCompound();
				slotTag.setInteger("slotIndex", i);
				stack.writeToNBT(slotTag);
				list.appendTag(slotTag);
			}
			
			
		}
		tag.setTag("inventory", list);
	}
	public void readFromNBT() {
		NBTTagCompound tag = stack.getTagCompound();
		if (tag == null) {
			writeToNBT();
		}
		NBTTagList list = tag.getTagList("inventory", Constants.NBT.TAG_COMPOUND);
		if (list == null) {
			writeToNBT();
			return;
	
		}
		for(int i = 0; i < list.tagCount(); i++) {
			NBTTagCompound slotTag = list.getCompoundTagAt(i);
			int index = slotTag.getInteger("slotIndex");
			ItemStack stack= ItemStack.loadItemStackFromNBT(slotTag);
			setInventorySlotContents(index, stack);
		}
	}
}

package boozemod;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
//import net.minecraft.util.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import boozemod.BoozeFood;

public class EventItemTooltip {
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void appendTooltip(ItemTooltipEvent event) {
        ItemStack stack = event.getItemStack();
        Item item = stack.getItem();

        // Only apply tooltip to our foods, instances of BoozeFood.
        if(item == null || !(item instanceof BoozeFood)) return;

        event.getToolTip().add("Hello, there!");
    }
}

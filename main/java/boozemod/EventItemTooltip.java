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
        if(item == null) return;
        if(item instanceof BoozeFood) {
            //item = (BoozeFood)item;
            // all BoozeFood children should have getTaste, right?
            String tasteString;
            tasteString = getTasteString(item);
            event.getToolTip().add("Hello, there!" + tasteString);
            event.getToolTip().add("This food is " + tasteString + ".");
        }
        // if not BoozeFood, do nothing
    }

    private String getTasteString(Item item) {
        int taste = ((BoozeFood)item).getTaste(); // bad design!
        switch(taste) {
            case 0:
                return "nutty";
            case 1:
                return "spicy";
            case 2:
                return "sweet";
            default:
                return "strange";
        }
    }
}

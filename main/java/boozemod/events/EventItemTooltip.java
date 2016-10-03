package boozemod.events;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
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

        // The tooltip only applies to BoozeFoods that are not null and have NBT data.
        if(item != null && item instanceof BoozeFood) {
            // assumes stack item will have nbt?
            // we can force every stack to have nbt?
            NBTTagCompound nbt;
            if(!stack.hasTagCompound()) {
                stack.setTagCompound(new NBTTagCompound());
            }
            nbt = stack.getTagCompound();

            int code;
            if(!nbt.hasKey("taste")) {
                nbt.setInteger("taste", 3);
            }
            code = nbt.getInteger("taste");

            String tasteString;
            tasteString = getTasteString(code);
            event.getToolTip().add("This food tastes " + tasteString + ".");
            event.getToolTip().add("This is an instance of BoozeFood.");
        }
        // if not BoozeFood, do nothing
    }

    private String getTasteString(int code) {
        switch(code) {
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

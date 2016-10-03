package boozemod;

//import net.minecraft.item.Item;
//import net.minecraft.item.ItemStack;
//import net.minecraft.util.nbt.NBTTagCompound;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class EventItemTooltip {
    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void appendTooltip(ItemTooltipEvent event) {
        event.getToolTip().add("Hello, there!");
        //ItemStack stack = event.getItemStack();
        //Item item = stack.getItem();
        //if(item == null) return;
    }
}

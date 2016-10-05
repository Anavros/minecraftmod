package boozemod.items;

import boozemod.DynamicFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.EnumAction;

public class ItemDrink extends DynamicFood {
    public ItemDrink() {
        super();
        this.setUnlocalizedName("Drink");
        this.setRegistryName("drink");
    }

    @Override
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.DRINK;
    }
}

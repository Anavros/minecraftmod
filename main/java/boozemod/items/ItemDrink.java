package boozemod.items;

import boozemod.BoozeFood;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.item.EnumAction;

public class ItemDrink extends BoozeFood {
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

package boozemod.items;

import java.util.Random;
import net.minecraft.item.ItemFood;
import boozemod.BoozeFood;

public class ItemMeal extends BoozeFood {
    public ItemMeal() {
        super(10, 5.0f, 3);
        this.setUnlocalizedName("Meal");
        this.setRegistryName("meal");
    }
}

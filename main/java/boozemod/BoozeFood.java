package boozemod;

import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class BoozeFood extends ItemFood {

    public BoozeFood(int healAmount, float saturation){

        super(healAmount, saturation, false);
    }
}



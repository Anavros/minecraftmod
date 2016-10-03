package boozemod;

import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class BoozeFood extends ItemFood {

    public int taste;

    public BoozeFood(int healAmount, float saturation, int taste) {
        super(healAmount, saturation, false);
        this.taste = taste;
    }

    public int getTaste() {
        return this.taste;
    }
}

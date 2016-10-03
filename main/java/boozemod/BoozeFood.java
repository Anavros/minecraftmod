package boozemod;

import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;

public class BoozeFood extends ItemFood {
    /* taste codes
    * 0 - nutty
    * 1 - spicy
    * 2 - sweet
    * otherwise - strange
    * TODO: make into enum
    */
    public int taste;

    public BoozeFood(int healAmount, float saturation, int taste) {
        super(healAmount, saturation, false);
        this.taste = taste;
    }

    public int getTaste() {
        return this.taste;
    }
}

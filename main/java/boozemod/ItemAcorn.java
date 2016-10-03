package boozemod;

import boozemod.BoozeFood;
import net.minecraft.item.ItemFood;

public class ItemAcorn extends BoozeFood {
    public ItemAcorn() {
        super(2, 1.5f);
        this.setUnlocalizedName("Acorn");
        this.setRegistryName("acorn");
    }
}

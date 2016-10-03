package boozemod;

import net.minecraft.item.ItemFood;

public class ItemAcorn extends ItemFood {
    public ItemAcorn() {
        super(2, 1.5f, false);
        this.setUnlocalizedName("Acorn");
        this.setRegistryName("acorn");
    }
}

package boozemod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BlockChopping extends Block {
    public BlockChopping() {
        super(Material.IRON);
        this.setUnlocalizedName("ChoppingBlock");
        this.setRegistryName("chopping_block");
    }
}

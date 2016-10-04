package boozemod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;

public class BlockTeapot extends Block {
    public BlockTeapot() {
        super(Material.IRON);
        this.setUnlocalizedName("Teapot");
        this.setRegistryName("teapot");
    }

    @Override
    public boolean isOpaqueCube(IBlockState bs) {
        return false;
    }
}

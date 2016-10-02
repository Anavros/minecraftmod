package boozemod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import boozemod.TileEntityTeapot;

//public class BlockTeapot extends Block implements ITileEntityProvider {
public class BlockTeapot extends BlockContainer {
    public BlockTeapot() {
        super(Material.IRON);
        this.setUnlocalizedName("Teapot");
        this.setRegistryName("teapot");
    }

/*    
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityTeapot();
    }

    @Override
    public boolean onBlockActivated(
            World world,
            BlockPos pos,
            IBlockState state,
            EntityPlayer player,
            EnumHand hand,
            ItemStack heldItem,
            EnumFacing side,
            float hitX,
            float hitY,
            float hitZ)
    {
        return false
    }
*/
}

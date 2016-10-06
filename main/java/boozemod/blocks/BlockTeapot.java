package boozemod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockTeapot extends Block implements ITileEntityProvider{
    public BlockTeapot() {
        super(Material.IRON);
        this.setUnlocalizedName("Teapot");
        this.setRegistryName("teapot");
    }

    @Override
    public TileEntity createNewTileEntity(World world, int meta) {
        return new TileEntityTeapot();
    }

    @Override
    public boolean isOpaqueCube(IBlockState bs) {
        return false;
    }

    @Override
    public boolean onBlockActivated(World world, BlockPos pos, IBlockState bs,
                                    EntityPlayer player, EnumHand hand, ItemStack stack, EnumFacing side,
                                    float hitX, float hitY, float hitZ)
    {
        if(stack == null) {
            return true;
        }
        //Take bucket of water from player.
        else {

            if(stack.getItem() == Items.WATER_BUCKET)
                player.setHeldItem(hand, new ItemStack(Items.BUCKET));

            return false;
        }
    }
}

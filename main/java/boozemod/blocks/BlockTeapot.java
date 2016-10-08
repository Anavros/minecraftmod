package boozemod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
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

    //To create a rotating object requires everything from here...
    public static final PropertyDirection ORIENTATION = PropertyDirection.create("facing", EnumFacing.Plane.HORIZONTAL);

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, new IProperty[] {ORIENTATION});
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing facing = EnumFacing.getHorizontal(meta);
        return this.getDefaultState().withProperty(ORIENTATION, facing);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        EnumFacing facing = (EnumFacing)state.getValue(ORIENTATION);
        int facedir = facing.getHorizontalIndex();
        return facedir;
    }

    @Override
    public IBlockState onBlockPlaced(World worldIn, BlockPos pos, EnumFacing blockFaceClicked, float x, float y,
                                     float z, int meta, EntityLivingBase placer) {
        EnumFacing enumfacing = (placer == null) ? EnumFacing.NORTH : EnumFacing.fromAngle(placer.rotationYaw);

        return this.getDefaultState().withProperty(ORIENTATION, enumfacing);
    }
    //...to here
}

package boozemod.blocks;

import boozemod.FoodProfile;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;

import boozemod.DynamicFood;

public class BlockChopping extends Block {
    public BlockChopping() {
        super(Material.IRON);
        this.setUnlocalizedName("ChoppingBlock");
        this.setRegistryName("chopping_block");
    }

    @Override
    public boolean onBlockActivated( World world, BlockPos pos, IBlockState bs,
            EntityPlayer player, EnumHand hand, ItemStack stack, EnumFacing side,
            float hitX, float hitY, float hitZ)
    {
        // TODO: Automatically goes from solid to liquid.
        if(stack == null) return false;
        // Only affect children of DynamicFood.
        if(stack.getItem() instanceof DynamicFood) {
            FoodProfile prof = new FoodProfile(stack);
            System.out.println(prof.state);
            if(prof.state < 3) {
                prof.state++;
                prof.chopped = true;
                prof.modifier = "Chopped";
                world.playSound(player, pos, SoundEvents.BLOCK_WOOD_BREAK, SoundCategory.BLOCKS, 1.0f, 1.0f);
            } else {
                prof.state = 3;
            }
            prof.apply(stack);
        }
        return true;
    }
}

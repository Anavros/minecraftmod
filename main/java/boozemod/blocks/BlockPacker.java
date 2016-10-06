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
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;

import boozemod.DynamicFood;

public class BlockPacker extends Block {
    public BlockPacker() {
        super(Material.IRON);
        this.setUnlocalizedName("Packer");
        this.setRegistryName("packer");
    }

    @Override
    public boolean onBlockActivated( World world, BlockPos pos, IBlockState bs,
            EntityPlayer player, EnumHand hand, ItemStack stack, EnumFacing side,
            float hitX, float hitY, float hitZ)
    {
        if(stack == null) return false;
        // Only affect children of DynamicFood.
        if(stack.getItem() instanceof DynamicFood) {
            FoodProfile prof = new FoodProfile(stack);
            // state reminder: 0->solid 1->pieces 2->mash 3->liquid
            if(prof.state > 0) {
                prof.state--;
                prof.packed = true;
                prof.modifier = "Packed";
                world.playSound(player, pos, SoundEvents.BLOCK_BREWING_STAND_BREW, SoundCategory.BLOCKS, 1.0f, 1.0f);
            } else {
                prof.state = 0;
            }
            prof.apply(stack);
        }
        // else: do nothing
        return true;
    }
}

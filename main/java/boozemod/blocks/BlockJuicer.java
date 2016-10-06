package boozemod.blocks;

import boozemod.FoodProfile;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCauldron;
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

public class BlockJuicer extends Block {
    public BlockJuicer() {
        super(Material.IRON);
        this.setUnlocalizedName("Juicer");
        this.setRegistryName("juicer");
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
            // TODO: ?
            if(prof.state == 0) {
                prof.state = 3;
                prof.juiced = true;
                prof.modifier = "Juiced";
                prof.apply(stack);
                world.playSound(player, pos, SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.BLOCKS, 1.0f, 1.0f);
            } // if not a solid, do nothing
        }
        // else: do nothing
        return true;
    }
}

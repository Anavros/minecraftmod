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

public class BlockDryer extends Block {
    public BlockDryer() {
        super(Material.IRON);
        this.setUnlocalizedName("Dryer");
        this.setRegistryName("dryer");
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
            int potentialJuiciness = prof.get("juiciness") - 1;
            if(0 <= potentialJuiciness && potentialJuiciness <= 2) {
                prof.set("juiciness", potentialJuiciness);
                world.playSound(player, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 1.0f, 1.0f);
            } else {
                prof.set("juiciness", 3); // Hyperliquid, used as error code
            }
            prof.apply(stack);
        }
        // if not an instance of DynamicFood: do nothing
        return true;
    }
}

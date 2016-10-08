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

public class BlockJuicer extends Block {
    public BlockJuicer() {
        super(Material.IRON);
        this.setUnlocalizedName("Juicer");
        this.setRegistryName("juicer");
    }

    @Override
    // Juicing Mechanics:
    // Juicy Solid|Pieces -> Liquid
    // Tender Solid|Pieces -> Mash
    // Dry Solid|Piecies -> Powder
    // TODO: Liquid, mash, and powder can all be combined, using juiciness as a distinction.
    // i.e. Juicy Grind == Liquid, Tender Grind == Mash, Dry Grind == Powder
    public boolean onBlockActivated( World world, BlockPos pos, IBlockState bs,
            EntityPlayer player, EnumHand hand, ItemStack stack, EnumFacing side,
            float hitX, float hitY, float hitZ)
    {
        if(stack == null) return false;
        // Only affect children of DynamicFood.
        if(stack.getItem() instanceof DynamicFood) {
            FoodProfile prof = new FoodProfile(stack);
            // state reminder: 0->solid 1->pieces 2->mash 3->liquid
            if(prof.get("state") == 0 || prof.get("state") == 1) {
                prof.set("state", 3);
                switch(prof.get("juiciness")) {
                    case 0: // Dry
                        prof.modifier = "Ground";
                    case 1: // Tender
                        prof.modifier = "Mashed";
                    case 2: // Juicy
                        prof.modifier = "Juiced";
                    default: // Hyperliquid (error case)
                        prof.modifier = "Surrealized";
                }
                prof.apply(stack);
                world.playSound(player, pos, SoundEvents.BLOCK_GRASS_BREAK, SoundCategory.BLOCKS, 1.0f, 1.0f);
            } // if not a solid: do nothing
        }
        // if not an instance of DynamicFood: do nothing
        return true;
    }
}

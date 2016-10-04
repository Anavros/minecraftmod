package boozemod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;

import boozemod.BoozeFood;

public class BlockChopping extends Block {
    public BlockChopping() {
        super(Material.IRON);
        this.setUnlocalizedName("ChoppingBlock");
        this.setRegistryName("chopping_block");
    }

    @Override
    public boolean onBlockActivated(
            World world,
            BlockPos pos,
            IBlockState bs,
            EntityPlayer player,
            EnumHand hand,
            ItemStack stack,
            EnumFacing side,
            float hitX,
            float hitY,
            float hitZ)
    {
        // check if the stack item is a child of BoozeFood
        if(stack == null) return false;
        Item item = stack.getItem();
        if(item != null && item instanceof BoozeFood) {
            int[] flavor = BoozeFood.getFlavor(stack);
            // for right now, we'll set the taste to nutty (0) if it is strange (3)
            if(flavor[0] == 3) {
                BoozeFood.setFlavor(stack, 0, flavor[1], flavor[2]);
                System.out.println("I did it!");
            } else {
                System.out.println("Not the right flavor.");
            }
        } else {
            System.out.println("Null item or not an instance of BoozeFood.");
        }
        return true;
    }
}

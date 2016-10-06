package boozemod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;

public class BlockHunger extends Block {
    public BlockHunger() {
        super(Material.IRON);
        this.setUnlocalizedName("HungerBlock");
        this.setRegistryName("hunger_block");
    }

    @Override
    // Inflict the player with hunger when block is right-clicked.
    public boolean onBlockActivated( World world, BlockPos pos, IBlockState bs,
            EntityPlayer player, EnumHand hand, ItemStack stack, EnumFacing side,
            float hitX, float hitY, float hitZ)
    {
        if(player != null) {
            player.addPotionEffect(new PotionEffect(Potion.getPotionById(17), 20, 10)); // #17 -> Hunger
            world.playSound(player, pos, SoundEvents.AMBIENT_CAVE, SoundCategory.AMBIENT, 1.0f, 1.0f);
            return false;
        } else {
            return true;
        }
    }
}

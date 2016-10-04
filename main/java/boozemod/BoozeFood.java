package boozemod;

import java.util.List;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import boozemod.blocks.BlockChopping;

public class BoozeFood extends ItemFood {
    public BoozeFood() {
        // Temporary until we override healing values per NBT attributes.
        super(1, 0.0f, false);
    }

    @Override
    @SideOnly(Side.CLIENT)
    // Creates a string description of a food using its taste data.
    // Data is stored in each ItemStack's NBT tags.
    // If an ItemStack does not have NBT tags, they will be added.
    public void addInformation(
            ItemStack stack,
            EntityPlayer player,
            List tooltip,
            boolean advanced)  // not sure what this does? hold shift for adv. details?
    {
        int[] flavor;
        String taste;
        String sweet;
        String heavy;
        NBTTagCompound nbt;
        String desc;

        flavor = getFlavor(stack);
        switch(flavor[0]) {
            case 0: taste = "nutty"; break;
            case 1: taste = "spicy"; break;
            case 2: taste = "sweet"; break;
            default: taste = "strange"; break;
        }
        switch(flavor[1]) {
            case 0: sweet = "savory"; break;
            case 1: sweet = "mild"; break;
            case 2: sweet = "sweet"; break;
            default: sweet = "bitter"; break;
        }
        switch(flavor[2]) {
            case 0: heavy = "light"; break;
            case 1: heavy = "medium"; break;
            case 2: heavy = "heavy"; break;
            default: heavy = "ethereal"; break;
        }
        tooltip.add(String.format("This food is %s, %s, and %s.", taste, sweet, heavy));
    }

    // Get three flavor variables from an ItemStack.
    // NBT data is saved per-stack, not per-class, so each food can be different.
    // If the food does not have NBT tags already, add stubs.
    public static int[] getFlavor(ItemStack stack) {
        int[] flavor = new int[3];
        NBTTagCompound nbt;

        // Ensure every stack has at least an empty NBT compound.
        if(!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
        // Now we can be sure that every stack has at least some NBT tag for us to use.
        nbt = stack.getTagCompound();

        // taste -> 0:nutty 1:spicy 2:sweet 3:strange TODO to be removed
        if(!nbt.hasKey("taste")) nbt.setInteger("taste", 3);
        flavor[0] = nbt.getInteger("taste");
        // sweet -> 0:savory 1:mild 2:sweet 3:bitter
        if(!nbt.hasKey("sweet")) nbt.setInteger("sweet", 3);
        flavor[1] = nbt.getInteger("sweet");
        // heavy -> 0:light 1:medium 2:heavy 3:ethereal
        if(!nbt.hasKey("heavy")) nbt.setInteger("heavy", 3);
        flavor[1] = nbt.getInteger("heavy");
        return flavor;
    }

    public static void setFlavor(ItemStack stack, int taste, int sweet, int heavy) {
        NBTTagCompound nbt;
        // Ensure every stack has at least an empty NBT compound.
        if(!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
        // Now we can be sure that every stack has at least some NBT tag for us to use.
        nbt = stack.getTagCompound();
        nbt.setInteger("taste", taste);
        nbt.setInteger("sweet", sweet);
        nbt.setInteger("heavy", heavy);
    }

    /*
    // Probably won't work, because on right click the player should eat the food, right?
    // Unless we can code that explicitly in this function.
    @Override
    public EnumActionResult onItemUse(
            ItemStack stack,
            EntityPlayer player,
            World world,
            BlockPos pos,
            EnumHand hand,
            EnumFacing facing,
            float hitX,
            float hitY,
            float hitZ)
    {
    }
    */
}

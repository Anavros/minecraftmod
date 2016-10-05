package boozemod;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
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
    public String getUnlocalizedName(ItemStack stack) {
        String[] s = getFlavorStrings(stack);
        return String.format("%s %s %s %s", s[0], s[1], s[2], s[3]);
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
        String[] strings = getFlavorStrings(stack);
        tooltip.add(String.format("This food is a %s.", strings[3]));
        tooltip.add(String.format("It tastes %s.", strings[0]));
        tooltip.add(String.format("It is %s and %s on the tongue.", strings[1], strings[2]));
    }

    private static String[] getFlavorStrings(ItemStack stack) {
        String[] strings = new String[4];
        int[] flavor = getFlavor(stack);
        switch(flavor[0]) {
            case 0: strings[0] = "nutty"; break;
            case 1: strings[0] = "spicy"; break;
            case 2: strings[0] = "sweet"; break;
            default: strings[0] = "strange"; break;
        }
        switch(flavor[1]) {
            case 0: strings[1] = "savory"; break;
            case 1: strings[1] = "mild"; break;
            case 2: strings[1] = "sweet"; break;
            default: strings[1] = "bitter"; break;
        }
        switch(flavor[2]) {
            case 0: strings[2] = "light"; break;
            case 1: strings[2] = "medium"; break;
            case 2: strings[2] = "heavy"; break;
            default: strings[2] = "ethereal"; break;
        }
        switch(flavor[3]) {
            case 0: strings[3] = "chunk"; break;
            case 1: strings[3] = "bunch of pieces"; break;
            case 2: strings[3] = "paste"; break;
            default: strings[3] = "liquid"; break;
        }
        return strings;
    }

    // Get three flavor variables from an ItemStack.
    // NBT data is saved per-stack, not per-class, so each food can be different.
    // If the food does not have NBT tags already, add stubs.
    public static int[] getFlavor(ItemStack stack) {
        int[] flavor = new int[4];
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
        flavor[2] = nbt.getInteger("heavy");
        // state -> 0:chunk 1:pieces 2:paste 3:liquid
        if(!nbt.hasKey("state")) nbt.setInteger("state", 3);
        flavor[3] = nbt.getInteger("state");
        return flavor;
    }

    public static void setFlavor(ItemStack stack, int taste, int sweet, int heavy, int state) {
        NBTTagCompound nbt;
        // Ensure every stack has at least an empty NBT compound.
        if(!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
        // Now we can be sure that every stack has at least some NBT tag for us to use.
        nbt = stack.getTagCompound();
        nbt.setInteger("taste", taste);
        nbt.setInteger("sweet", sweet);
        nbt.setInteger("heavy", heavy);
        nbt.setInteger("state", state);
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

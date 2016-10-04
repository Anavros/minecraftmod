package boozemod;

import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class BoozeFood extends ItemFood {
    // how do we ensure every BoozeFood ItemStack has nbt tags?
    public BoozeFood(int healAmount, float saturation, int taste) {
        super(healAmount, saturation, false);
    }

    // Creates a string description of a food using its taste data.
    // Data is stored in each ItemStack's NBT tags.
    // If an ItemStack does not have NBT tags, they will be added.
    // This might not be the best place for this function.
    public static String description(ItemStack stack) {
        int t;
        int s;
        int h;
        Item item;
        NBTTagCompound nbt;
        String desc;

        // Ensure every stack has at least an empty NBT compound.
        if(!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
        // Now we can be sure that every stack has at least some NBT tag for us to use.
        nbt = stack.getTagCompound();

        // taste -> 0:nutty 1:spicy 2:sweet 3:strange TODO to be removed
        if(!nbt.hasKey("taste")) nbt.setInteger("taste", 3);
        t = nbt.getInteger("taste");
        // sweet -> 0:savory 1:mild 2:sweet 3:bitter
        if(!nbt.hasKey("sweet")) nbt.setInteger("sweet", 3);
        s = nbt.getInteger("sweet");
        // heavy -> 0:light 1:medium 2:heavy 3:ethereal
        if(!nbt.hasKey("heavy")) nbt.setInteger("heavy", 3);
        h = nbt.getInteger("heavy");

        String taste;
        String sweet;
        String heavy;
        switch(t) {
            case 0: taste = "nutty"; break;
            case 1: taste = "spicy"; break;
            case 2: taste = "sweet"; break;
            default: taste = "strange"; break;
        }
        switch(s) {
            case 0: sweet = "savory"; break;
            case 1: sweet = "mild"; break;
            case 2: sweet = "sweet"; break;
            default: sweet = "bitter"; break;
        }
        switch(h) {
            case 0: heavy = "light"; break;
            case 1: heavy = "medium"; break;
            case 2: heavy = "heavy"; break;
            default: heavy = "ethereal"; break;
        }
        return String.format("This food is %s, %s, and %s.", taste, sweet, heavy);
    }

    public static void setAttributes(ItemStack stack, int taste, int sweet, int heavy) {
        NBTTagCompound nbt;
        // Ensure every stack has at least an empty NBT compound.
        if(!stack.hasTagCompound()) stack.setTagCompound(new NBTTagCompound());
        // Now we can be sure that every stack has at least some NBT tag for us to use.
        nbt = stack.getTagCompound();
        nbt.setInteger("taste", taste);
        nbt.setInteger("sweet", sweet);
        nbt.setInteger("heavy", heavy);
    }
}

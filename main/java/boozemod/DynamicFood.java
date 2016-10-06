package boozemod;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class DynamicFood extends ItemFood {
    public DynamicFood() {
        super(0, 0.0f, false); // Overriden with NBT attributes.
    }

    @Override
    public int getHealAmount(ItemStack stack) {
        FoodProfile prof = new FoodProfile(stack);
        return prof.state*2;
    }

    @Override
    public float getSaturationModifier(ItemStack stack) {
        return 0.0f;
    }

    @Override
    @SideOnly(Side.CLIENT)
    // Sets the unlocalized name, to be referenced in the language files.
    public String getUnlocalizedName(ItemStack stack) {
        FoodProfile prof = new FoodProfile(stack);
        String taste = prof.humanReadable("taste");
        String sweet = prof.humanReadable("sweetness");
        String heavy = prof.humanReadable("heaviness");
        String state = prof.humanReadable("state");
        return String.format("%s %s %s %s", taste, sweet, heavy, state).toUpperCase();
    }

    @Override
    @SideOnly(Side.CLIENT)
    public String getItemStackDisplayName(ItemStack stack) {
        return getUnlocalizedName(stack);
    }

    @Override
    @SideOnly(Side.CLIENT)
    // Creates a string description of a food using its taste data.
    // Data is stored in each ItemStack's NBT tags.
    // If an ItemStack does not have NBT tags, they will be added.
    public void addInformation(
            ItemStack stack,
            EntityPlayer player,
            List<String> tooltip,
            boolean advanced)  // not sure what this does? hold shift for adv. details?
    {
        FoodProfile prof = new FoodProfile(stack);
        String taste = prof.humanReadable("taste");
        String sweet = prof.humanReadable("sweetness");
        String heavy = prof.humanReadable("heaviness");
        String state = prof.humanReadable("state");
        tooltip.add(String.format("This food is a %s.", state));
        tooltip.add(String.format("It tastes %s.", taste));
        tooltip.add(String.format("It is %s and %s on the tongue.", sweet, heavy));
    }
}

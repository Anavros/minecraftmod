package boozemod;

import java.util.Random;
import net.minecraft.item.ItemFood;
import boozemod.BoozeFood;

public class ItemMeal extends BoozeFood {
    public ItemMeal(int taste) {
        // you can't put anything before super?
        super(10, 5.0f, taste);
        this.setUnlocalizedName("Meal");
        this.setRegistryName("meal");
    }

    public static int getRandomTaste() {
        Random r = new Random();
        int taste;
        taste = r.nextInt(3);
        return taste;
    }
}

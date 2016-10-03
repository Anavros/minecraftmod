package boozemod;

import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
//import net.minecraft.util.nbt.NBTTagCompound;

public class BoozeFood extends ItemFood {
    /* taste codes
    * 0 - nutty
    * 1 - spicy
    * 2 - sweet
    * otherwise - strange
    * TODO: make into enum
    */
    public int taste;
    //public NBTTagCompound nbt;

    // the question is how to change taste on the fly
    // and also to have it set for each instance of BoozeFood, not just classes
    public BoozeFood(int healAmount, float saturation, int taste) {
        super(healAmount, saturation, false);
        this.setTaste(taste);
    }

    public void setTaste(int taste) {
        //this.nbt.setInteger("taste", taste);
        this.taste = taste;
    }

    public int getTaste() {
        //return this.nbt.getInteger("taste");
        return this.taste;
    }
}

package boozemod;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.HashMap;
import java.util.Map;

public class FoodProfile {
    // Set to default values.
    public int taste = 3;
    public int sweetness = 3;
    public int heaviness = 3;
    public int state = 3;
    public boolean chopped = false;
    public boolean juiced = false;
    public boolean packed = false;
    public String original = "Food";
    public String modifier = "";
    
    private Map<Integer, String> tasteStrings;
    private Map<Integer, String> sweetnessStrings;
    private Map<Integer, String> heavinessStrings;
    private Map<Integer, String> stateStrings;
    
    public void read(ItemStack stack) {
        if(stack != null && stack.hasTagCompound()) {
            NBTTagCompound nbt = stack.getTagCompound();
            if(nbt.hasKey("taste")) taste = nbt.getInteger("taste");
            if(nbt.hasKey("sweet")) sweetness = nbt.getInteger("sweet");
            if(nbt.hasKey("heavy")) heaviness = nbt.getInteger("heavy");
            if(nbt.hasKey("state")) state = nbt.getInteger("state");
            if(nbt.hasKey("chopped")) chopped = nbt.getBoolean("chopped");
            if(nbt.hasKey("juiced")) juiced = nbt.getBoolean("juiced");
            if(nbt.hasKey("packed")) packed = nbt.getBoolean("packed");
            if(nbt.hasKey("original")) original = nbt.getString("original");
            if(nbt.hasKey("modifier")) modifier = nbt.getString("modifier");
        }
        // else: vars are initialized to defaults already
    }
    
    public void apply(ItemStack stack) {
        if(stack == null) return;
        if(!stack.hasTagCompound()) { stack.setTagCompound(new NBTTagCompound()); }
        NBTTagCompound nbt = stack.getTagCompound();
        nbt.setInteger("taste", taste);
        nbt.setInteger("sweet", sweetness);
        nbt.setInteger("heavy", heaviness);
        nbt.setInteger("state", state);
        nbt.setBoolean("chopped", chopped);
        nbt.setBoolean("juiced", juiced);
        nbt.setBoolean("packed", packed);
        nbt.setString("original", original);
        nbt.setString("modifier", modifier);
    }
    
    public FoodProfile() {
        putStrings();
    }
    
    public FoodProfile(ItemStack stack) {
        read(stack);
        putStrings();
    }

    public FoodProfile(int newTaste, int newSweet, int newHeavy, int newState) {
        taste = newTaste;
        sweetness = newSweet;
        heaviness = newHeavy;
        state = newState;
        putStrings();
    }

    private void putStrings() {
        tasteStrings = new HashMap<>();
        tasteStrings.put(0, "nutty");
        tasteStrings.put(1, "spicy");
        tasteStrings.put(2, "meaty");
        tasteStrings.put(3, "strange");
        
        sweetnessStrings = new HashMap<>();
        sweetnessStrings.put(0, "savory");
        sweetnessStrings.put(1, "mild");
        sweetnessStrings.put(2, "sweet");
        sweetnessStrings.put(3, "bitter");

        heavinessStrings = new HashMap<>();
        heavinessStrings.put(0, "light");
        heavinessStrings.put(1, "medium");
        heavinessStrings.put(2, "heavy");
        heavinessStrings.put(3, "ethereal");
        
        stateStrings = new HashMap<>();
        stateStrings.put(0, "solid");
        stateStrings.put(1, "pieces");
        stateStrings.put(2, "mash");
        stateStrings.put(3, "liquid");
    }
    
    public String humanReadable(String key) {
        String str;
        switch(key) {
            case "taste":
                str = tasteStrings.get(taste);
                break;
            case "sweetness":
                str = sweetnessStrings.get(sweetness);
                break;
            case "heaviness":
                str = heavinessStrings.get(heaviness);
                break;
            case "state":
                str = stateStrings.get(state);
                break;
            default:
                str = "ERROR";
        }
        return str;
    }
}
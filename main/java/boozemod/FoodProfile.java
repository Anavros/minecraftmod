package boozemod;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.HashMap;
import java.util.Map;

// TODO: Add more modifiers to foods to dictate which preparations can be done to them.
// E.g.: To be juiced, a solid must be juicy.
public class FoodProfile {
    // Set to default values.
    // If an ItemStack lacks values for any of these parameters, these defaults will be used.
    public int taste = 3;
    public int sweetness = 3;
    public int heaviness = 3;
    public int juiciness = 3;
    public int state = 3;
    public String original = "Food";
    public String modifier = "";
    
    // Human-readable strings for each integer taste value.
    // Initialized in putStrings(), which is called from the class constructors.
    private Map<Integer, String> tasteStrings;
    private Map<Integer, String> sweetnessStrings;
    private Map<Integer, String> heavinessStrings;
    private Map<Integer, String> juicinessStrings;
    private Map<Integer, String> stateStrings;
    
    // Take an ItemStack and mutate this object's internal state to match the stack's NBT data.
    // If the ItemStack is missing parameters, defaults are used instead.
    public void read(ItemStack stack) {
        if(stack != null && stack.hasTagCompound()) {
            NBTTagCompound nbt = stack.getTagCompound();
            if(nbt.hasKey("taste")) taste = nbt.getInteger("taste");
            if(nbt.hasKey("sweet")) sweetness = nbt.getInteger("sweet");
            if(nbt.hasKey("heavy")) heaviness = nbt.getInteger("heavy");
            if(nbt.hasKey("juicy")) juiciness = nbt.getInteger("juicy");
            if(nbt.hasKey("state")) state = nbt.getInteger("state");
            if(nbt.hasKey("original")) original = nbt.getString("original");
            if(nbt.hasKey("modifier")) modifier = nbt.getString("modifier");
        }
        // else: vars are initialized to defaults already
    }
    
    // Update an ItemStack's NBT data to match this profile's internal state.
    public void apply(ItemStack stack) {
        if(stack == null) return;
        if(!stack.hasTagCompound()) { stack.setTagCompound(new NBTTagCompound()); }
        NBTTagCompound nbt = stack.getTagCompound();
        nbt.setInteger("taste", taste);
        nbt.setInteger("sweet", sweetness);
        nbt.setInteger("heavy", heaviness);
        nbt.setInteger("juicy", juiciness);
        nbt.setInteger("state", state);
        nbt.setString("original", original);
        nbt.setString("modifier", modifier);
    }
    
    // Create a new profile for an extant stack.
    public FoodProfile(ItemStack stack) {
        read(stack);
        putStrings();
    }

    // Create a new profile by manually setting parameters.
    // Used to apply new parameters to ItemStack NBT.
    public FoodProfile(int newTaste, int newSweet, int newHeavy, int newJuicy, int newState) {
        taste = newTaste;
        sweetness = newSweet;
        heaviness = newHeavy;
        juiciness = newJuicy;
        state = newState;
        putStrings();
    }

    // These strings are used for human-readable food descriptions.
    // Used in humanReadable().
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

        juicinessStrings = new HashMap<>();
        juicinessStrings.put(0, "dry");
        juicinessStrings.put(1, "tender");
        juicinessStrings.put(2, "moist");
        juicinessStrings.put(3, "hyperliquid");
        
        stateStrings = new HashMap<>();
        stateStrings.put(0, "solid");
        stateStrings.put(1, "pieces");
        stateStrings.put(2, "mash");
        stateStrings.put(3, "liquid");
    }
    
    // Get a string describing this object's internal value for a given key.
    // e.g. humanReadable("taste") + <internal variable taste == 0> -> "nutty"
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
            case "juiciness":
                str = juicinessStrings.get(juiciness);
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
package boozemod;

import com.ibm.icu.impl.IllegalIcuArgumentException;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class FoodProfile {
    private Map<String, Integer> attributes;
    private Map<String, Map<Integer, String>> descriptions;
    public String original = "Food";
    public String modifier = "";

    // Create a new profile for an extant stack.
    public FoodProfile(ItemStack stack) {
        putAttributes();
        putStrings();
        read(stack); // NOTE: Must come after putAttributes! Terrible NPE bug!
    }
    
    // Take an ItemStack and mutate this object's internal state to match the stack's NBT data.
    // If the ItemStack is missing parameters, defaults are used instead.
    public void read(ItemStack stack) {
        if(stack != null && stack.hasTagCompound()) {
            NBTTagCompound nbt = stack.getTagCompound();
            // Using an iterator allows in-place modification.
            Iterator iter = this.attributes.keySet().iterator();
            while(iter.hasNext()) {
                String key = (String) iter.next();
                if(nbt.hasKey(key)) { this.set(key, nbt.getInteger(key)); }
            }
        } // else: vars are initialized to defaults already
    }
    
    // Update an ItemStack's NBT data to match this profile's internal state.
    public void apply(ItemStack stack) {
        if(stack == null) return;
        if(!stack.hasTagCompound()) { stack.setTagCompound(new NBTTagCompound()); }
        NBTTagCompound nbt = stack.getTagCompound();
        for(Map.Entry<String, Integer> entry : this.attributes.entrySet()) {
            nbt.setInteger(entry.getKey(), entry.getValue());
        }
    }
    
    public int get(String key) throws IllegalArgumentException {
        if(this.attributes.containsKey(key)) {
            return this.attributes.get(key);
        } else {
            throw new IllegalArgumentException();
        }
    }
    
    // Only works if the key is already known, otherwise throws NullPointerException.
    public void set(String key, int value) throws IllegalArgumentException {
        if(this.attributes.containsKey(key)) {
            this.attributes.put(key, value);
        } else {
            throw new IllegalArgumentException();
        }
    }
    
    public String getDesc(String key) {
        return this.descriptions.get(key).get(this.get(key));
    }

    // Set to default values.
    // If an ItemStack lacks values for any of these parameters, these defaults will be used.
    private void putAttributes() {
        this.attributes = new HashMap<>();
        this.attributes.put("taste", 3);
        this.attributes.put("sweetness", 3);
        this.attributes.put("heaviness", 3);
        this.attributes.put("juiciness", 3);
        this.attributes.put("doneness", 0);
        this.attributes.put("state", 0);
    }

    // These strings are used for human-readable food descriptions.
    // Used in humanReadable().
    private void putStrings() {
        this.descriptions = new HashMap<>();
        Map<Integer, String> tasteStrings = new HashMap<>();
        tasteStrings.put(0, "nutty");
        tasteStrings.put(1, "spicy");
        tasteStrings.put(2, "meaty");
        tasteStrings.put(3, "strange");
        
        Map<Integer, String> sweetnessStrings = new HashMap<>();
        sweetnessStrings.put(0, "savory");
        sweetnessStrings.put(1, "mild");
        sweetnessStrings.put(2, "sweet");
        sweetnessStrings.put(3, "bitter");

        Map<Integer, String> heavinessStrings = new HashMap<>();
        heavinessStrings.put(0, "light");
        heavinessStrings.put(1, "medium");
        heavinessStrings.put(2, "heavy");
        heavinessStrings.put(3, "ethereal");

        Map<Integer, String> juicinessStrings = new HashMap<>();
        juicinessStrings.put(0, "dry");
        juicinessStrings.put(1, "tender");
        juicinessStrings.put(2, "moist");
        juicinessStrings.put(3, "hyperliquid");
        
        Map<Integer, String> donenessStrings = new HashMap<>();
        donenessStrings.put(0, "raw");
        donenessStrings.put(1, "rare");
        donenessStrings.put(2, "medium");
        donenessStrings.put(3, "well-done");
        
        Map<Integer, String> stateStrings = new HashMap<>();
        stateStrings.put(0, "solid");
        stateStrings.put(1, "pieces");
        stateStrings.put(2, "mash");
        stateStrings.put(3, "liquid");
        
        this.descriptions.put("taste", tasteStrings);
        this.descriptions.put("sweetness", sweetnessStrings);
        this.descriptions.put("heaviness", heavinessStrings);
        this.descriptions.put("juiciness", juicinessStrings);
        this.descriptions.put("doneness", donenessStrings);
        this.descriptions.put("state", stateStrings);
    }
}
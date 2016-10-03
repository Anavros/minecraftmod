package boozemod;

import boozemod.EventItemTooltip;
import net.minecraftforge.common.MinecraftForge;

public class ModEvents {
    public static void registerEvents() {
        MinecraftForge.EVENT_BUS.register(new EventItemTooltip());
    }
}

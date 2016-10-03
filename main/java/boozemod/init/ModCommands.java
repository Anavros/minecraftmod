package boozemod.init;

import boozemod.commands.CommandSetTaste;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

public class ModCommands {
    public static void registerCommands(FMLServerStartingEvent event) {
        event.registerServerCommand(new CommandSetTaste());
    }
}

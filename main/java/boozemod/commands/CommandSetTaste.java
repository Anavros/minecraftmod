package boozemod.commands;

import java.util.List;
import java.util.ArrayList;

import boozemod.FoodProfile;
import net.minecraft.command.ICommand;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.item.ItemStack;

import boozemod.DynamicFood;

public class CommandSetTaste implements ICommand {
    private final List<String> aliases;

    public CommandSetTaste() {
        aliases = new ArrayList<>();
        aliases.add("taste");
        aliases.add("tst");
    }

    public List<String> getCommandAliases() {
        return aliases;
    }

    @Override
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

    @Override
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override
    public List<String> getTabCompletionOptions(
            MinecraftServer server,
            ICommandSender sender,
            String[] args,
            BlockPos pos)
    {
        return null;
    }

    @Override
    public int compareTo(ICommand o) {
        return 0; // not sure what this does
    }

    @Override
    public String getCommandName() {
        return "taste";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/taste <key> <value>";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args)
        throws CommandException
    {
        // Only execute if on a server?
        if(sender.getEntityWorld().isRemote) return;
        if(args.length != 2) {
            sender.addChatMessage(new TextComponentString(getCommandUsage(sender)));
            return;
        }
        int value;
        String key = args[0];
        try {
            value = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            sender.addChatMessage(new TextComponentString(getCommandUsage(sender)));
            return;
        }

        EntityPlayer player = ((EntityPlayer)sender.getCommandSenderEntity());
        if(player == null) return;
        ItemStack stack = player.inventory.getCurrentItem();
        if(stack == null || !(stack.getItem() instanceof DynamicFood)) return;
        
        // Finally:
        FoodProfile prof = new FoodProfile(stack);
        try {
            prof.set(key, value);
        } catch (NullPointerException e) {
            sender.addChatMessage(new TextComponentString(getCommandUsage(sender)));
            return;
        }
        prof.apply(stack);
    }
}

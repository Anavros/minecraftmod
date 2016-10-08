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
        aliases = new ArrayList<String>();
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
        return "taste <taste> <sweetness> <heaviness> <state>";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args)
        throws CommandException
    {
        int taste;
        int sweet;
        int heavy;
        int juicy;
        int state;
        String argError = "Requires five arguments: taste, sweet, heavy, juicy, and state, all <0-2>.";
        String intError = "Arguments must all be integers.";
        String success = "Successfully set new food attributes.";
        String failure = "Must be used on a modded food.";

        // Only execute if on a server?
        if(sender.getEntityWorld().isRemote) return;
        if(args.length != 5) {
            sender.addChatMessage(new TextComponentString(argError));
            return;
        }
        try {
            taste = Integer.parseInt(args[0]);
            sweet = Integer.parseInt(args[1]);
            heavy = Integer.parseInt(args[2]);
            juicy = Integer.parseInt(args[3]);
            state = Integer.parseInt(args[4]);
        } catch (NumberFormatException e) {
            sender.addChatMessage(new TextComponentString(intError));
            return;
        }

        EntityPlayer player = ((EntityPlayer)sender.getCommandSenderEntity());
        if(player == null) return;
        ItemStack stack = player.inventory.getCurrentItem();
        if(stack == null) return;
        if(stack.getItem() instanceof DynamicFood) {
            FoodProfile prof = new FoodProfile(taste, sweet, heavy, juicy, state);
            prof.apply(stack);
            sender.addChatMessage(new TextComponentString(success));
        } else {
            sender.addChatMessage(new TextComponentString(failure));
        }
    }
}

package boozemod.commands;

import java.util.List;
import java.util.ArrayList;
import net.minecraft.command.ICommand;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import boozemod.BoozeFood;

public class CommandSetTaste implements ICommand {
    // not sure if needed?
    private final List aliases;

    public CommandSetTaste() {
        aliases = new ArrayList();
        aliases.add("boozemodSetTaste");
    }

    public List getCommandAliases() {
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
        return "boozemodSetTaste";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "boozemodSetTaste [0..2]";
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args)
        throws CommandException
    {
        if(sender.getEntityWorld().isRemote) return;
        sender.addChatMessage(new TextComponentString("Do something!"));
        if(args.length != 1) {
            sender.addChatMessage(new TextComponentString("Wrong number of args."));
            return;
        }
        int tasteCode;
        try {
            tasteCode = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            sender.addChatMessage(new TextComponentString("Argument must be integer!"));
            return;
        }
        // so now we have an integer tastecode

        // next goal is to get the item the player is holding
        EntityPlayer player = ((EntityPlayer)sender.getCommandSenderEntity());
        if(player == null) return;
        ItemStack stack = player.inventory.getCurrentItem();
        if(stack == null) return;
        Item item = stack.getItem();
        if(item == null || !(item instanceof BoozeFood)) return;

        // finally
        BoozeFood food = ((BoozeFood)item);
        food.taste = tasteCode;
        sender.addChatMessage(new TextComponentString("Set new taste code."));
    }
}

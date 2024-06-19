package org.occulteam.occultech.common.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.world.entity.player.Player;

public class AddManaCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("addmana")
                .then(Commands.argument("amount", IntegerArgumentType.integer(1, 1000)).executes(command -> {
                    return execute(command, IntegerArgumentType.getInteger(command, "amount"));
                })));
    }

    public static int execute(CommandContext<CommandSourceStack> command, int amount) {
        if (command.getSource().getEntity() instanceof Player) {
            Player player = (Player) command.getSource().getEntity();
            if (player != null) { // Add null check for player variable
                // PlayerCapHelper.addMana(player, amount);
            }
        }
        return Command.SINGLE_SUCCESS;
    }
}

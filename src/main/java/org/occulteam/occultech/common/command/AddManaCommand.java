package org.occulteam.occultech.common.command;

import org.occulteam.occultech.common.capability.CapabilityRegistry;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.DoubleArgumentType;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.OutgoingChatMessage;
import net.minecraft.network.chat.PlayerChatMessage;
import net.minecraft.world.entity.player.Player;

public class AddManaCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("addmana")
                .then(Commands.argument("amount", DoubleArgumentType.doubleArg(1, 1000)).executes(command -> {
                    return execute(command, DoubleArgumentType.getDouble(command, "amount"));
                })));
    }

    public static int execute(CommandContext<CommandSourceStack> command, int amount) {
        if (command.getSource().getEntity() instanceof Player) {
            Player player = (Player) command.getSource().getEntity();
            if (player != null) { // Add null check for player variable
                int curMaxMana = player.getCapability(CapabilityRegistry.MANA_CAPABILITY).resolve().get().getMaxMana();
                player.getCapability(CapabilityRegistry.MANA_CAPABILITY).resolve().get().setMaxMana(curMaxMana + amount);
            }
        }
        return Command.SINGLE_SUCCESS;
    }
}

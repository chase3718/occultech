package org.occulteam.occultech.common.command;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.OutgoingChatMessage;
import net.minecraft.network.chat.PlayerChatMessage;
import net.minecraft.world.entity.player.Player;

public class ShowManaCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("showmana").executes(command -> {
            return execute(command);
        }));
    }

    public static int execute(CommandContext<CommandSourceStack> command) {
        if (command.getSource().getEntity() instanceof Player) {
            Player player = (Player) command.getSource().getEntity();
            String text = "Mana: ";

            if (player != null) {
                // String curMana = PlayerCapHelper.getCurrentMana(player) + "/" +
                // PlayerCapHelper.getMaxMana(player);
                // text = text.concat(curMana);
            }

            PlayerChatMessage message = PlayerChatMessage.unsigned(player.getUUID(), text);
            player.createCommandSourceStack().sendChatMessage(new OutgoingChatMessage.Player(message), false,
                    ChatType.bind(ChatType.CHAT, player));

        }
        return Command.SINGLE_SUCCESS;
    }
}

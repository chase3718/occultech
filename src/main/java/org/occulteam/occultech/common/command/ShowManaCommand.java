package org.occulteam.occultech.common.command;

import java.util.logging.Logger;

import org.occulteam.occultech.common.capability.mana.CapRegistry;
import org.occulteam.occultech.common.capability.mana.IMana;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;

import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
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

            if (player != null) {
                Logger.getLogger("ShowManaCommand").info("Player is not null");
                IMana mana = CapRegistry.getMana(player).orElse(null);
                int curMana = mana.getMana();
                int maxMana = mana.getMaxMana();
                Component manaMsg = Component.literal("Mana: " + curMana + "/" + maxMana);
                player.displayClientMessage(manaMsg, false);
            }

        }
        return Command.SINGLE_SUCCESS;
    }
}

package org.occulteam.occultech.common.item;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ModItem extends Item {
    public List<Component> tooltip = new ArrayList<>();

    public ModItem(Properties pProperties) {
        super(pProperties);
    }

    public ModItem() {
        super(new Item.Properties());
    }

    public ModItem withToolTip(String tip) {
        tooltip.add(Component.translatable(tip));
        return this;
    }

    @OnlyIn(Dist.CLIENT)
    public void appendHoverText(ItemStack stack, Level world, List<Component> tTooltip, TooltipFlag flag) {
        if (tooltip != null && !tooltip.isEmpty()) {
            tTooltip.addAll(tooltip);
        }
    }
}

package org.occulteam.occultech.client;

import org.occulteam.occultech.common.capability.mana.IMana;

public class ClientData {
    private static int mana;
    private static int maxMana;
    private static int manaRegen;

    public static int getMana() {
        return mana;
    }

    public static void setMana(int mana) {
        ClientData.mana = mana;
    }

    public static int getMaxMana() {
        return maxMana;
    }

    public static void setMaxMana(int maxMana) {
        ClientData.maxMana = maxMana;
    }

    public static int getManaRegen() {
        return manaRegen;
    }

    public static void setManaRegen(int manaRegen) {
        ClientData.manaRegen = manaRegen;
    }

    public static void setData(IMana mana) {
        setMana(mana.getMana());
        setMaxMana(mana.getMaxMana());
        setManaRegen(mana.getManaRegen());
    }
}

package org.occulteam.occultech.client;

public class ClientManaData {
    private static int PlayerMana;

    public static void setMana(int mana) {
        ClientManaData.PlayerMana = mana;
    }

    public static int getMana() {
        return ClientManaData.PlayerMana;
    }
}

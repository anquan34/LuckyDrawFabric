package com.example.luckydraw;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Items;
import org.lwjgl.glfw.GLFW;

public class LuckyDrawClient implements ClientModInitializer {
    private static KeyMapping openScreenKey;

    @Override
    public void onInitializeClient() {
        // Press G to open the showcase screen.
        openScreenKey = KeyBindingHelper.registerKeyBinding(new KeyMapping(
                "key.lucky_draw.open",
                GLFW.GLFW_KEY_G,
                KeyMapping.Category.MISC
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (openScreenKey.consumeClick()) {
                if (client.screen == null) {
                    client.setScreen(new LuckyDrawScreen());
                }
            }
        });
    }
}

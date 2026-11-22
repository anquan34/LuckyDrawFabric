package com.example.luckydraw;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class LuckyDrawScreen extends Screen {
    private record Reward(String name, Item item, String rarity) {}

    private static final List<Reward> REWARDS = List.of(
            new Reward("Emerald Showcase", Items.EMERALD, "UNCOMMON"),
            new Reward("Diamond Showcase", Items.DIAMOND, "RARE"),
            new Reward("Golden Apple Showcase", Items.GOLDEN_APPLE, "RARE"),
            new Reward("Amethyst Showcase", Items.AMETHYST_SHARD, "COMMON"),
            new Reward("Nether Star Showcase", Items.NETHER_STAR, "VERY RARE"),
            new Reward("Prismarine Showcase", Items.PRISMARINE_CRYSTALS, "COMMON"),
            new Reward("Echo Shard Showcase", Items.ECHO_SHARD, "VERY RARE"),
            new Reward("Heart of the Sea Showcase", Items.HEART_OF_THE_SEA, "RARE")
    );

    private Reward selected;
    private String status = "Press the button to reveal a random showcase item";

    public LuckyDrawScreen() {
        super(Component.literal("Lucky Draw"));
    }

    @Override
    protected void init() {
        int center = this.width / 2;
        int buttonY = this.height - 48;
        this.addRenderableWidget(Button.builder(Component.literal("REVEAL RANDOM ITEM"), button -> reveal())
                .bounds(center - 90, buttonY, 180, 22).build());
        this.addRenderableWidget(Button.builder(Component.literal("Close"), button -> onClose())
                .bounds(center - 40, buttonY + 25, 80, 20).build());
    }

    private void reveal() {
        selected = REWARDS.get(ThreadLocalRandom.current().nextInt(REWARDS.size()));
        status = "Revealed: " + selected.name() + "  |  " + selected.rarity();
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        super.render(graphics, mouseX, mouseY, partialTick);

        int center = this.width / 2;
        graphics.fill(18, 18, this.width - 18, this.height - 78, 0xD91A1A24);
        graphics.drawCenteredString(this.font, Component.literal("LUCKY DRAW"), center, 30, 0xFFFFFF);
        graphics.drawCenteredString(this.font,
                Component.literal("Cosmetic showcase only — no bets, payment, or item loss"),
                center, 48, 0xB8C8D8);

        int startX = center - 112;
        int startY = 78;
        for (int i = 0; i < REWARDS.size(); i++) {
            Reward reward = REWARDS.get(i);
            int col = i % 4;
            int row = i / 4;
            int x = startX + col * 58;
            int y = startY + row * 58;
            graphics.fill(x - 5, y - 5, x + 43, y + 45, 0xFF30303D);
            graphics.renderItem(new ItemStack(reward.item()), x + 9, y + 2);
            graphics.drawCenteredString(this.font, Component.literal(reward.rarity()), x + 19, y + 28, 0xD8D8E8);
        }

        if (selected != null) {
            graphics.fill(center - 120, this.height - 105, center + 120, this.height - 82, 0xFF252536);
            graphics.renderItem(new ItemStack(selected.item()), center - 108, this.height - 102);
            graphics.drawString(this.font, Component.literal(selected.name()), center - 84,
                    this.height - 98, 0xFFFFFF);
        } else {
            graphics.drawCenteredString(this.font, Component.literal(status), center,
                    this.height - 94, 0xE8E8F0);
        }
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}

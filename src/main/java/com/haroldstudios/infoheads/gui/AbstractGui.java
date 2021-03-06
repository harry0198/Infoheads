package com.haroldstudios.infoheads.gui;

import com.cryptomorin.xseries.XMaterial;
import com.haroldstudios.infoheads.InfoHeadConfiguration;
import com.haroldstudios.infoheads.InfoHeads;
import com.haroldstudios.infoheads.datastore.DataStore;
import com.haroldstudios.infoheads.elements.Element;
import com.haroldstudios.infoheads.inventory.HeadStacks;
import com.haroldstudios.infoheads.utils.MessageUtil;
import lombok.Getter;
import me.mattstudios.mfgui.gui.components.ItemBuilder;
import me.mattstudios.mfgui.gui.guis.BaseGui;
import me.mattstudios.mfgui.gui.guis.Gui;
import me.mattstudios.mfgui.gui.guis.GuiItem;
import org.bukkit.entity.Player;

import java.util.Objects;

public abstract class AbstractGui {

    @Getter private final InfoHeads plugin;
    @Getter private final BaseGui gui;
    @Getter private final Player player;
    @Getter private final InfoHeadConfiguration configuration;

    public AbstractGui(final Player player, InfoHeads plugin, int rows, String title, InfoHeadConfiguration configuration) {
        this.plugin = plugin;
        this.configuration = configuration;
        this.gui = new Gui(plugin, rows, title);
        this.player = player;
    }

    public void open() {
        getGui().open(getPlayer());
    }

    protected GuiItem appendMessageItem() {
        return new GuiItem(new ItemBuilder(Objects.requireNonNull(XMaterial.BOOK.parseMaterial()))
                .glow(true)
                .setName(MessageUtil.APPEND_MESSAGE_TITLE)
                .setLore(MessageUtil.APPEND_MESSAGE_LORE)
                .build(),
                event -> {
                    event.setCancelled(true);
                    gui.close(player);
                    plugin.getInputFactory(configuration, Element.InfoHeadType.MESSAGE).buildConversation(player).begin();
                });
    }

    protected GuiItem appendConsoleCommandItem() {
        return new GuiItem(new ItemBuilder(Objects.requireNonNull(XMaterial.COMMAND_BLOCK.parseMaterial()))
                .glow(true)
                .setName(MessageUtil.APPEND_CONSOLE_COMMAND_TITLE)
                .setLore(MessageUtil.APPEND_COMMAND_LORE)
                .build(), event -> {
            event.setCancelled(true);
            gui.close(player);
            plugin.getInputFactory(configuration, Element.InfoHeadType.CONSOLE_COMMAND).buildConversation(player).begin();
        });
    }

    protected GuiItem appendPlayerCommandItem() {
        return new GuiItem(new ItemBuilder(Objects.requireNonNull(XMaterial.COMMAND_BLOCK.parseMaterial()))
                .glow(true)
                .setName(MessageUtil.APPEND_PLAYER_COMMAND_TITLE)
                .setLore(MessageUtil.APPEND_COMMAND_LORE)
                .build(), event -> {
            event.setCancelled(true);
            gui.close(player);
            plugin.getInputFactory(configuration, Element.InfoHeadType.PLAYER_COMMAND).buildConversation(player).begin();
        });
    }

    protected GuiItem setLocationItem() {
        return new GuiItem(new ItemBuilder((Objects.requireNonNull(XMaterial.GRASS_BLOCK.parseMaterial())))
                .glow(true)
                .setName(MessageUtil.SET_LOCATION_TITLE)
                .setLore(MessageUtil.SET_LOCATION_LORE)
                .build(), event -> {
            event.setCancelled(true);
            gui.close(player);
            MessageUtil.sendMessage(player, MessageUtil.PLACE_INFOHEAD);
            DataStore.placerMode.put(player, configuration);

            HeadStacks.giveHeads(plugin, player);
        });
    }

    protected GuiItem cancelItem() {
        return new GuiItem(new ItemBuilder(Objects.requireNonNull(XMaterial.BARRIER.parseMaterial()))
                .glow(true)
                .setName(MessageUtil.CLOSE_WIZARD_TITLE)
                .setLore(MessageUtil.CLOSE_WIZARD_LORE)
                .build(), event -> {
            event.setCancelled(true);
            getGui().close(player);
        });
    }

    protected GuiItem setPermission() {
        return new GuiItem(new ItemBuilder(Objects.requireNonNull(XMaterial.GLASS_BOTTLE.parseMaterial()))
                .glow(true)
                .setName(MessageUtil.SET_PERMISSION_TITLE)
                .setLore(MessageUtil.SET_PERMISSION_LORE)
                .build(), event -> {
            event.setCancelled(true);
            getGui().close(player);
            plugin.getInputFactory(configuration, Element.InfoHeadType.PERMISSION).buildConversation(player).begin();
        });
    }

    protected GuiItem appendDelay() {
        return new GuiItem(new ItemBuilder(Objects.requireNonNull(XMaterial.CLOCK.parseMaterial()))
                .glow(true)
                .setName(MessageUtil.APPEND_DELAY_TITLE)
                .setLore(MessageUtil.APPEND_DELAY_LORE)
                .build(), event -> {
            event.setCancelled(true);
            getGui().close(player);
            plugin.getInputFactory(configuration, Element.InfoHeadType.DELAY).buildConversation(player).begin();
        });
    }

    protected GuiItem placeholdersItem() {
        return new GuiItem(new ItemBuilder(Objects.requireNonNull(XMaterial.WRITABLE_BOOK.parseMaterial()))
                .glow(true)
                .setName(MessageUtil.PLACEHOLDER_TITLE)
                .setLore(MessageUtil.PLACEHOLDER_LORE)
                .build(), event -> event.setCancelled(true));
    }

    protected GuiItem editItem() {
        return new GuiItem(new ItemBuilder(Objects.requireNonNull(XMaterial.MAP.parseMaterial()))
                .glow(true)
                .setName(MessageUtil.EDIT_GUI_TITLE)
                .setLore(MessageUtil.EDIT_GUI_LORE)
                .build(), event -> {
            event.setCancelled(true);
            new EditGui(configuration, getPlayer()).open();
        });
    }

    protected GuiItem cooldownItem() {
        return new GuiItem(new ItemBuilder(Objects.requireNonNull(XMaterial.COMPASS.parseMaterial()))
                .glow(true)
                .setName(MessageUtil.COOLDOWN_ITEM_TITLE)
                .setLore(MessageUtil.COOLDOWN_ITEM_LORE)
                .build(), event -> {
            event.setCancelled(true);
            new CooldownGui(player, plugin, configuration).open();
        });
    }

    protected GuiItem particleItem() {
        return new GuiItem(new ItemBuilder(Objects.requireNonNull(XMaterial.REDSTONE.parseMaterial()))
                .glow(true)
                .setName(MessageUtil.PARTICLE_ITEM_TITLE)
                .setLore(MessageUtil.PARTICLE_ITEM_LORE)
                .build(), event -> {
            event.setCancelled(true);
            new ParticleSelectorGui(player, plugin, configuration).open();
        });
    }

    protected GuiItem playerPermissionItem() {
        return new GuiItem(new ItemBuilder(XMaterial.FEATHER.parseMaterial())
        .glow(true)
        .setName(MessageUtil.PLAYER_PERMISSION_TITLE)
        .setLore(MessageUtil.PLAYER_PERMISSION_LORE)
        .build(), event -> {
            event.setCancelled(true);
            gui.close(player);
            plugin.getInputFactory(configuration, Element.InfoHeadType.PLAYER_PERMISSION).buildConversation(player).begin();
        });
    }

    protected int getSlotFromRowCol(final int row, final int col) {
        return (col + (row - 1) * 9) - 1;
    }

}

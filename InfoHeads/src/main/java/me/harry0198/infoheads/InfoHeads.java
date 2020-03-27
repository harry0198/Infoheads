package me.harry0198.infoheads;

import java.util.*;
import java.util.stream.Stream;

import com.google.inject.Inject;
import com.google.inject.Injector;
import me.harry0198.infoheads.commands.CommandManager;
import me.harry0198.infoheads.commands.Commands;
import me.harry0198.infoheads.commands.general.conversations.editspecific.LineSelectPrompt;
import me.harry0198.infoheads.commands.general.conversations.wizard.CommandPrompt;
import me.harry0198.infoheads.commands.player.EditCommand;
import me.harry0198.infoheads.guice.BinderModule;
import me.harry0198.infoheads.inventorys.HeadStacks;
import me.harry0198.infoheads.inventorys.Inventory;
import me.harry0198.infoheads.listeners.HDBListener;
import me.harry0198.infoheads.utils.HdbApi;
import me.harry0198.infoheads.utils.LoadedLocations;
import me.harry0198.infoheads.utils.PapiMethod;
import me.harry0198.infoheads.utils.Settings;
import me.harry0198.infoheads.utils.Utils;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.conversations.ConversationAbandonedEvent;
import org.bukkit.conversations.ConversationAbandonedListener;
import org.bukkit.conversations.ConversationFactory;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.harry0198.infoheads.commands.general.conversations.wizard.InfoHeadsConversationPrefix;
import me.harry0198.infoheads.listeners.EntityListeners;

public class InfoHeads extends JavaPlugin {

    /* Handlers */
    private final Settings settings = new Settings();


    @Override
    public void onEnable() {

        saveDefaultConfig();

        // Metrics
        @SuppressWarnings("unused")
        Metrics metrics = new Metrics(this);
        // Checking for PAPI

        Stream.of(Registerables.GUICE, Registerables.INFOHEADS, Registerables.LISTENERS, Registerables.COMMANDS).forEach(this::register);

    }

    public void register(Registerables registerable) {
        switch (registerable) {


            case COMMANDS:
                Objects.requireNonNull(getCommand("infoheads"))
                        .setExecutor(commandManager);
                Arrays.stream(Commands.values()).map(Commands::getClazz).map(injector::getInstance).forEach(commandManager.getCommands()::add);
                break;
        }
    }

    public static InfoHeads getInstance() {
        return getPlugin(InfoHeads.class);
    }

    public enum Registerables {
        COMMANDS, INFOHEADS, LISTENERS, GUICE
    }

}

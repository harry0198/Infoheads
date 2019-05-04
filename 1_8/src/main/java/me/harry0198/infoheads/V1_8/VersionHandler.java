package me.harry0198.infoheads.V1_8;

import me.harry0198.infoheads.VersionInterface;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class VersionHandler implements VersionInterface {
    public boolean triggerOnce(PlayerInteractEvent e){
        return true;
    }
    public void items(Player player){
        Items items = new Items();

        //TODO Change to streams / lambda
        addItem(player, items.questionSkull()); // THIS MUST BE FIRST TO REGISTER VERSION
        addItem(player, items.exclamationSkull());
        addItem(player, items.arrowUpSkull());
        addItem(player, items.arrowDownSkull());
        addItem(player, items.arrowLeftSkull());
        addItem(player, items.arrowRightSkull());
        addItem(player, items.faceBookSkull());
        addItem(player, items.chestSkull());

    }

    private void addItem(Player player, ItemStack itemstack) {
        player.getInventory().addItem(itemstack);
    }
}
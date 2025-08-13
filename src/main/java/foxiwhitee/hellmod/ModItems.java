package foxiwhitee.hellmod;


import appeng.api.AEApi;
import cpw.mods.fml.common.registry.GameRegistry;
import foxiwhitee.hellmod.config.ContentConfig;
import foxiwhitee.hellmod.items.*;
import foxiwhitee.hellmod.items.fluid.ItemEmptyFluidCell;
import foxiwhitee.hellmod.items.fluid.ItemFluidDrop;
import foxiwhitee.hellmod.items.fluid.ItemFluidStorageCells;
import foxiwhitee.hellmod.items.fluid.ItemFluidStorageComponent;
import foxiwhitee.hellmod.items.part.ItemParts;
import foxiwhitee.hellmod.items.storage.ItemCustomEmptyCells;
import foxiwhitee.hellmod.items.storage.ItemCustomStorageCells;
import foxiwhitee.hellmod.items.storage.ItemCustomStorageComponent;
import foxiwhitee.hellmod.utils.helpers.RegisterUtils;
import net.minecraft.item.Item;

import java.util.HashMap;
import java.util.Map;

public class ModItems {

    public static final Item ITEM_PARTS = new ItemParts(AEApi.instance().partHelper());

    public static final Item WIRELESS_STAFF = new ItemWirelessStaff("wireless_staff");

    public static final Item FLUID_DROP = new ItemFluidDrop();

    public static final Item EMPTY_FLUID_CELL = new ItemEmptyFluidCell("emptyFluidCell");
    public static final Item CUSTOM_EMPTY_STORAGE_CELLS = new ItemCustomEmptyCells("customEmptyStorageCell");

    public static final Item FLUID_STORAGE_COMPONENT = new ItemFluidStorageComponent("fluidComponent");
    public static final Item CUSTOM_STORAGE_COMPONENT = new ItemCustomStorageComponent("storageComponent");

    public static final Item FLUID_CELLS = new ItemFluidStorageCells();
    public static final Item CUSTOM_STORAGE_CELLS = new ItemCustomStorageCells();

    public static void registerItems() {
        RegisterUtils.registerItem(ITEM_PARTS, "hell-part");
        if (ContentConfig.enableWireless) {
            RegisterUtils.registerItem(WIRELESS_STAFF);
        }
        if (ContentConfig.enableMEFluid) {
            RegisterUtils.registerItems(FLUID_DROP, FLUID_CELLS, EMPTY_FLUID_CELL, EMPTY_FLUID_CELL);
        }
        if (ContentConfig.enableStorageCells) {
            RegisterUtils.registerItems(CUSTOM_EMPTY_STORAGE_CELLS, CUSTOM_STORAGE_COMPONENT, CUSTOM_STORAGE_CELLS);
        }
    }

}

package net.blay09.mods.cookingforblockheads.container.comparator;

import net.minecraft.item.ItemStack;

import java.util.Comparator;

public class ComparatorName implements Comparator<ItemStack> {

    @Override
    public int compare(ItemStack o1, ItemStack o2) {
        String s1 = o1.getDisplayName();
        String s2 = o2.getDisplayName();
        return s1.compareToIgnoreCase(s2);
    }

}

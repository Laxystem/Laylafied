@file:JvmName("Utilities")
@file:JvmMultifileClass

package io.github.laylameower.laylafied.utils

import io.github.laylameower.laylafied.LaylafiedMod
import io.github.laylameower.laylafied.magic.ComboEnchantment
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtElement
import net.minecraft.nbt.NbtList
import net.minecraft.registry.Registries

fun rarest(first: Enchantment.Rarity, second: Enchantment.Rarity): Enchantment.Rarity =
    if (first.ordinal > second.ordinal) first else second

fun rarest(first: Enchantment, second: Enchantment): Enchantment.Rarity = rarest(first.rarity, second.rarity)

fun addEnchantmentTo(target: NbtList, enchantment: Enchantment?, level: Int) {
    target.add(EnchantmentHelper.createNbt(EnchantmentHelper.getEnchantmentId(enchantment), level.toByte().toInt()))
}

@JvmOverloads
fun addComboEnchantmentsTo(itemStack: ItemStack, textLevel: Int = 1) {
    val itemEnchants = itemStack.getOrCreateNbt().getList("Enchantments", NbtElement.COMPOUND_TYPE.toInt())
    val itemEnchantments = EnchantmentHelper.fromNbt(itemEnchants)

    Registries.ENCHANTMENT.entries.forEach { (_, enchantment) ->
        if (enchantment !is ComboEnchantment) {
            LaylafiedMod.LOGGER.debug("\tSkipped non-combo enchantment [{}]", enchantment.getName(textLevel).string)
            return
        }

        if (enchantment.isAcceptableItem(itemStack) && EnchantmentHelper.isCompatible(
                itemEnchantments.keys,
                enchantment
            )
        ) {
            LaylafiedMod.LOGGER.debug("\tAdded enchantment [{}]", enchantment.getName(textLevel).string)

            addEnchantmentTo(
                itemEnchants, enchantment, average(
                    EnchantmentHelper.getLevel(enchantment.first, itemStack),
                    EnchantmentHelper.getLevel(enchantment.second, itemStack)
                )
            )

        } else LaylafiedMod.LOGGER.debug("\tSkipped combo enchantment [{}]", enchantment.getName(textLevel).string)
    }
}

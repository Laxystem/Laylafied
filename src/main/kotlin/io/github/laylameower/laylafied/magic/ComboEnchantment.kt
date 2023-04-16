package io.github.laylameower.laylafied.magic

import io.github.laylameower.laylafied.utils.*
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtElement
import org.jetbrains.annotations.Contract

abstract class ComboEnchantment(
    type: EnchantmentTarget?,
    slotTypes: Array<EquipmentSlot?>?,
    val first: Enchantment,
    val second: Enchantment
) : Enchantment(
    rarest(
        first, second
    ), type, slotTypes
) {
    private val maxLevel: Int = (first.maxLevel + second.maxLevel) / 2

    override fun isAcceptableItem(stack: ItemStack): Boolean {
        val enchants =
            EnchantmentHelper.fromNbt(stack.getOrCreateNbt().getList("Enchantments", NbtElement.COMPOUND_TYPE.toInt()))
        return super.isAcceptableItem(stack) && enchants.containsKey(first) && enchants.containsKey(second)
    }

    /**
     * @see io.github.laylameower.laylafied.utils.addComboEnchantmentsTo
     */
    @Contract(pure = true)
    override fun getMaxLevel(): Int {
        return maxLevel
    }

    @Contract(pure = true)
    override fun getMinLevel(): Int {
        return 1
    }

    @Contract(pure = true)
    override fun isTreasure(): Boolean {
        return true
    }

    @Contract(pure = true)
    override fun isAvailableForEnchantedBookOffer(): Boolean {
        return false
    }

    @Contract(pure = true)
    override fun isAvailableForRandomSelection(): Boolean {
        return false
    }
}

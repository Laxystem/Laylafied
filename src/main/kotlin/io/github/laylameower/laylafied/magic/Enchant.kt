@file:Suppress("unused")


package io.github.laylameower.laylafied.magic

import net.minecraft.enchantment.Enchantment
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityGroup
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.damage.DamageSource
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.math.Range

/**
 * An improved version of the [vanilla Enchantment][Enchantment] class.
 */
interface Enchant {
    fun toEnchantment(): Enchantment
    val rarity: Enchantment.Rarity
    val experienceLevelRange: Range<Int>
    fun getExtraProtectionAmountFrom(source: DamageSource?, level: Int): Int
    fun getExtraDamageAmountFrom(group: EntityGroup?, level: Int): Float
    fun isCompatibleWith(other: Enchantment): Boolean
    val target: EnchantTarget
    val translationKey: String
    fun getName(level: Int): Text
    fun onTargetDamaged(user: LivingEntity?, target: Entity?, item: ItemStack?, level: Int) {}
    fun onUserDamaged(user: LivingEntity?, attacker: Entity?, stack: ItemStack?, level: Int) {}
    fun isTreasure(level: Int): Boolean
    fun isCurse(level: Int): Boolean
    fun isGrindstoneRemovable(level: Int): Boolean = isCurse(level)

    fun isAvailableInLibrarian(level: Int): Boolean
    fun isRandomizable(level: Int): Boolean
    fun isAvailableInLootTables(level: Int): Boolean = isRandomizable(level)

    fun isAvailableInEnchantingTable(level: Int): Boolean = isRandomizable(level)
}

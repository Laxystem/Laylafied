package io.github.laylameower.laylafied.magic

import io.github.laylameower.laylafied.utils.MAIN_HAND
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.enchantment.MendingEnchantment
import net.minecraft.enchantment.UnbreakingEnchantment
import net.minecraft.entity.Entity
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import net.minecraft.util.Hand

/**
 * This weapon is imbued with a soul of a barbarian, unleashing its might when vulnerable the most.
 */
class BarbaricEnchantment : Enchantment(Rarity.RARE, EnchantmentTarget.WEAPON, MAIN_HAND) {
    override fun isAcceptableItem(stack: ItemStack): Boolean = super.isAcceptableItem(stack) && stack.isDamageable

    override fun getMaxLevel(): Int = 7

    override fun isTreasure(): Boolean = true

    override fun isAvailableForEnchantedBookOffer(): Boolean = false

    override fun onTargetDamaged(user: LivingEntity, target: Entity, level: Int) {
        val item = user.getStackInHand(Hand.MAIN_HAND)

        if (item.damage == 0) return

        val damage = item.maxDamage / (item.maxDamage - item.damage).toFloat()
        val totalDamage = damage * level

        target.damage(user.damageSources.magic(), totalDamage)
    }

    override fun canAccept(other: Enchantment): Boolean =
        !(other is MendingEnchantment || other is UnbreakingEnchantment) && super.canAccept(other)
}

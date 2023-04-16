@file:JvmName("Utilities")
@file:JvmMultifileClass

package io.github.laylameower.laylafied.utils

import io.github.laylameower.laylafied.LaylafiedMod
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentHelper.*
import net.minecraft.entity.EquipmentSlot
import net.minecraft.item.Item
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.Registry.register
import net.minecraft.util.Identifier
import org.jetbrains.annotations.Contract
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings

val HANDS = arrayOf(EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND)
val MAIN_HAND = arrayOf(EquipmentSlot.MAINHAND)

@Contract(value = " -> new", pure = true)
fun defaultMixinImplementation(): IllegalStateException = IllegalStateException("Mixin injection failed.")

fun createItem(settingsChanger: QuiltItemSettings.() -> QuiltItemSettings) = Item(settingsChanger(QuiltItemSettings()))

@Contract("_,_,_ -> param3")
fun <T> register(registry: Registry<in T>?, identifier: String?, instance: T): T {
    register(registry, Identifier(LaylafiedMod.MOD_ID, identifier), instance)
    return instance
}

@Contract("_,_ -> param2")
fun <T : Enchantment?> register(identifier: String?, instance: T): T =
    register(Registries.ENCHANTMENT, identifier, instance)

fun <T : Item?> register(identifier: String?, instance: T): T = register(Registries.ITEM, identifier, instance)

fun average(a: Int, b: Int) = (a + b) / 2

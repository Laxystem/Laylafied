package io.github.laylameower.laylafied

import io.github.laylameower.laylafied.magic.BarbaricEnchantment
import io.github.laylameower.laylafied.utils.createItem
import io.github.laylameower.laylafied.utils.register
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.util.Rarity
import org.quiltmc.loader.api.ModContainer
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object LaylafiedMod : ModInitializer {
    const val MOD_ID = "laylafied"

    @JvmField val LOGGER: Logger = LoggerFactory.getLogger(MOD_ID)

    @JvmField val BARBARIC_ENCHANTMENT = BarbaricEnchantment()

    @JvmField val DRAGON_SCALES = createItem {
        rarity(Rarity.RARE)
        fireproof()
    }

    @JvmField val FROZEN_CLOTH = createItem {
        fireproof()
    }

    @JvmField val SOUL_TEAR = createItem {
        fireproof()
    }

    override fun onInitialize(mod: ModContainer) {
        register("barbaric", BARBARIC_ENCHANTMENT)
        register("dragon_scales", DRAGON_SCALES)
        register("frozen_cloth", FROZEN_CLOTH)
        register("soul_tear", SOUL_TEAR)
        LOGGER.debug("EnchantmentTarget interfaces: {}", EnchantmentTarget::class.java.interfaces.toString())
    }
}

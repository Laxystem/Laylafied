package io.github.laylameower.laylafied.mixin;

import io.github.laylameower.laylafied.utils.injected.interfaces.MixinEnchantTarget;
import net.minecraft.enchantment.EnchantmentTarget;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EnchantmentTarget.class)
public abstract class EnchantmentTargetMixin implements MixinEnchantTarget { }

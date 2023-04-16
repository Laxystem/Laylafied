package io.github.laylameower.laylafied.utils.injected.interfaces;

import io.github.laylameower.laylafied.magic.EnchantTarget;
import net.minecraft.item.Item;
import org.jetbrains.annotations.NotNull;

import static io.github.laylameower.laylafied.utils.Utilities.defaultMixinImplementation;

/**
 * Only use it for mixins.
 */
public interface MixinEnchantTarget extends EnchantTarget {
	default boolean isAcceptableItem(@NotNull Item item) {
		throw defaultMixinImplementation();
	}
}

package io.github.laylameower.laylafied.utils.injected.interfaces;

import io.github.laylameower.laylafied.magic.Enchant;
import io.github.laylameower.laylafied.magic.EnchantTarget;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.math.Range;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static io.github.laylameower.laylafied.utils.Utilities.defaultMixinImplementation;

/**
 * Only use it for mixins.
 */
public interface MixinEnchant extends Enchant {
	@NotNull
	@Override
	default Enchantment toEnchantment() {
		throw defaultMixinImplementation();
	}

	@Override
	default int getExtraProtectionAmountFrom(@Nullable DamageSource source, int level) {
		throw defaultMixinImplementation();
	}

	@Override
	default float getExtraDamageAmountFrom(EntityGroup group, int level) {
		throw defaultMixinImplementation();
	}

	@Override
	default boolean isCompatibleWith(@NotNull Enchantment other) {
		throw defaultMixinImplementation();
	}

	@Override
	default void onTargetDamaged(LivingEntity user, Entity target, ItemStack item, int level) {
		throw defaultMixinImplementation();
	}

	@Override
	default void onUserDamaged(LivingEntity user, Entity attacker, ItemStack stack, int level) {
		throw defaultMixinImplementation();
	}

	@NotNull
	@Override
	default EnchantTarget getTarget() {
		throw defaultMixinImplementation();
	}

	@NotNull
	@Override
	default String getTranslationKey() {
		throw defaultMixinImplementation();
	}

	@NotNull
	@Override
	default Text getName(int level) {
		throw defaultMixinImplementation();
	}

	@NotNull
	@Override
	default Enchantment.Rarity getRarity() {
		throw defaultMixinImplementation();
	}

	@NotNull
	@Override
	default Range<Integer> getExperienceLevelRange() {
		throw defaultMixinImplementation();
	}

	@Override
	default boolean isTreasure(int level) {
		throw defaultMixinImplementation();
	}

	@Override
	default boolean isCurse(int level) {
		throw defaultMixinImplementation();
	}

	@Override
	default boolean isAvailableInLibrarian(int level) {
		throw defaultMixinImplementation();
	}

	@Override
	default boolean isRandomizable(int level) {
		throw defaultMixinImplementation();
	}
}

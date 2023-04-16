package io.github.laylameower.laylafied.mixin;

import io.github.laylameower.laylafied.magic.EnchantTarget;
import io.github.laylameower.laylafied.utils.injected.interfaces.MixinEnchant;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityGroup;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.Range;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public abstract class EnchantmentMixin implements MixinEnchant {
	@Shadow
	public abstract int getMinLevel();

	@Shadow
	public abstract int getMaxLevel();

	@Shadow
	public abstract int getProtectionAmount(int level, DamageSource source);

	@Shadow
	public abstract float getAttackDamage(int level, EntityGroup group);

	@Shadow
	@Final
	public EnchantmentTarget type;

	@Shadow
	public abstract boolean isTreasure();

	@Shadow
	public abstract boolean isCursed();

	@Shadow
	public abstract boolean isAvailableForEnchantedBookOffer();

	@Shadow
	public abstract boolean isAvailableForRandomSelection();

	@Shadow
	public abstract void onTargetDamaged(LivingEntity user, Entity target, int level);

	@Shadow
	public abstract void onUserDamaged(LivingEntity user, Entity attacker, int level);

	@Shadow
	public abstract boolean canCombine(Enchantment other);

	@NotNull
	@Override
	@Contract(pure = true)
	public final Enchantment toEnchantment() {
		return (Enchantment) (Object) this;
	}

	@Contract(" -> new")
	@Override
	public final @NotNull Range<Integer> getExperienceLevelRange() {
		return new Range<>(getMinLevel(), getMaxLevel());
	}

	@Override
	public final int getExtraProtectionAmountFrom(@Nullable DamageSource source, int level) {
		return getProtectionAmount(level, source);
	}

	@Override
	public final float getExtraDamageAmountFrom(EntityGroup group, int level) {
		return getAttackDamage(level, group);
	}

	@Inject(method = "isAcceptableItem", at = @At("RETURN"), cancellable = true)
	private void injectIsAcceptableItem(@NotNull ItemStack stack, @NotNull CallbackInfoReturnable<Boolean> cir) {
		cir.setReturnValue(getTarget().isAcceptableItem(stack.getItem()));
	}

	@NotNull
	@Override
	public EnchantTarget getTarget() {
		return type;
	}

	@Override
	public boolean isTreasure(int level) {
		return isTreasure();
	}

	@Override
	public boolean isCurse(int level) {
		return isCursed();
	}

	@Override
	public boolean isAvailableInLibrarian(int level) {
		return isAvailableForEnchantedBookOffer();
	}

	@Override
	public boolean isRandomizable(int level) {
		return isAvailableForRandomSelection();
	}

	@Override
	public void onTargetDamaged(LivingEntity user, Entity target, ItemStack item, int level) {
		onTargetDamaged(user, target, level);
	}

	@Override
	public void onUserDamaged(LivingEntity user, Entity attacker, ItemStack stack, int level) {
		onUserDamaged(user, attacker, level);
	}

	@Override
	public final boolean isCompatibleWith(@NotNull Enchantment other) {
		return canCombine(other);
	}
}

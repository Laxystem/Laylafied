package io.github.laylameower.laylafied.mixin;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static io.github.laylameower.laylafied.LaylafiedMod.LOGGER;
import static io.github.laylameower.laylafied.utils.Utilities.addComboEnchantmentsTo;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
	@Shadow
	public abstract Text getName();

	public ItemStack getThis() {
		return (ItemStack) (Object) this;
	}

	@Inject(method = "addEnchantment", at = @At("RETURN"))
	private void injectAddEnchantment(@NotNull Enchantment enchantment, int level, CallbackInfo info) {
		LOGGER.debug("Injecting into enchantment [{}] on item [{}]", enchantment.getName(level).getString(), this.getName().getString());

		addComboEnchantmentsTo(getThis(), level);
	}
}

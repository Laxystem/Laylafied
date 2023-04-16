package io.github.laylameower.laylafied.mixin;

import lombok.val;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.ForgingScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static io.github.laylameower.laylafied.LaylafiedMod.LOGGER;
import static io.github.laylameower.laylafied.utils.Utilities.addComboEnchantmentsTo;

@Mixin(AnvilScreenHandler.class)
public abstract class AnvilScreenHandlerMixin extends ForgingScreenHandler {

	@Inject(method = "updateResult", at = @At("RETURN"))
	public void injectUpdateResult(CallbackInfo info) {
		val itemStack = output.getStack(0);

		if (itemStack.isEmpty() || !itemStack.hasEnchantments()) return;

		LOGGER.debug("Injecting into anvil enchanting on item [{}]", itemStack.getName().getString());

		addComboEnchantmentsTo(itemStack);
	}

	// region private constructor = null

	@SuppressWarnings("DataFlowIssue")
	private AnvilScreenHandlerMixin() {
		super(null, 0, null, null);
	}

	// endregion
}

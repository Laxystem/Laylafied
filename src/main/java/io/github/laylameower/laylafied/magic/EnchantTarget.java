package io.github.laylameower.laylafied.magic;

import net.minecraft.item.Item;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * It's Java for fun. Just kidding, for simpler compatibility.
 */
public interface EnchantTarget {
	boolean isAcceptableItem(Item item);

	interface Combination {
		boolean isAcceptable(Stream<EnchantTarget> stream, Predicate<EnchantTarget> predicate);

		@Contract(pure = true)
		static @NotNull EnchantTarget of(EnchantTarget[] targets, Combination predicate) {
			return item -> predicate.isAcceptable(Arrays.stream(targets), (it -> it.isAcceptableItem(item)));
		}
	}

	@Contract(pure = true)
	static @NotNull EnchantTarget or(EnchantTarget... targets) {
		return Combination.of(targets, Stream::anyMatch);
	}

	@Contract(pure = true)
	static @NotNull EnchantTarget and(EnchantTarget... targets) {
		return Combination.of(targets, Stream::allMatch);
	}

	@Contract(pure = true)
	static @NotNull EnchantTarget none(EnchantTarget... targets) {
		return Combination.of(targets, Stream::noneMatch);
	}
}

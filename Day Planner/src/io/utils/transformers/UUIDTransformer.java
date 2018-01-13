package io.utils.transformers;

import java.util.UUID;

public class UUIDTransformer implements ITransformer<UUID> {
	public UUID transform(String value) {
		return UUID.fromString(value);
	}
}
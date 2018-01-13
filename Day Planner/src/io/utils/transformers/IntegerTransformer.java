package io.utils.transformers;

public class IntegerTransformer implements ITransformer<Integer> {
	public Integer transform(String value) {
		return Integer.parseInt(value);
	}
}
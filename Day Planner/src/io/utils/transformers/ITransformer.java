package io.utils.transformers;

public interface ITransformer<T> {
	T transform(String value);
}
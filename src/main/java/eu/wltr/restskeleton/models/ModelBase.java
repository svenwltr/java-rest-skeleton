package eu.wltr.restskeleton.models;

public abstract class ModelBase<T> {

	public final T record;

	public ModelBase(T record)
	{
		this.record = record;
	}
}

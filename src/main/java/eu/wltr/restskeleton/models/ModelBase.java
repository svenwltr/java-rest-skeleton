package eu.wltr.restskeleton.models;

import eu.wltr.restskeleton.utils.SerialClone;

public abstract class ModelBase<T> {

	public final T record;

	public ModelBase(T record)
	{
		this.record = record;
	}
	
	public T cloneRecord()
	{
		return SerialClone.clone(record);
	}
}

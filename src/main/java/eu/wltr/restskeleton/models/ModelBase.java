package eu.wltr.restskeleton.models;


import eu.wltr.restskeleton.utils.JavaBeanCopier;



public class ModelBase<T>
{

	public final T record;


	public ModelBase(T record) {
		this.record = record;
	}

	public T cloneRecord()
	{
		return JavaBeanCopier.copy(record);
		// return SerialClone.clone(record);
	}
}

package eu.wltr.restskeleton.models;

import java.util.List;

public class ModelList<T> {
	
	public final List<T> records;

	public ModelList(List<T> records)
	{
		this.records = records;
	}
}

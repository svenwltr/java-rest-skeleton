package eu.wltr.restskeleton.models;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import eu.wltr.restskeleton.server.App;

public class BarModel extends ModelBase<BarRecord> {
	
	public static ModelList<BarRecord> findAll() {
		return new ModelList<BarRecord>(App.getMongoOperations().findAll(BarRecord.class));
	}


	public static BarModel findByName(String name) {
		
		Query q = new Query(Criteria.where("name").is(name));
		BarRecord record = App.getMongoOperations().findOne(q, BarRecord.class);
		
		return new BarModel(record);
	}
	
	
	public BarModel()
	{
		super(new BarRecord());
	}

	private BarModel(BarRecord record)
	{
		super(record);
	}
	
}

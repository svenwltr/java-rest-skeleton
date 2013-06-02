package eu.wltr.restskeleton.models;


import java.util.ArrayList;
import java.util.List;



public class BarModel extends ModelBase<BarRecord>
{

	public static List<BarModel> findAll()
	{
		List<BarModel> list = new ArrayList<BarModel>();

		// for (BarRecord r : App.getMongoOperations().findAll(BarRecord.class))
		// list.add(new BarModel(r));

		return list;
	}

	public static BarModel findByName(String name)
	{

		// /Query q = new Query(Criteria.where("name").is(name));
		// BarRecord record = App.getMongoOperations().findOne(q,
		// BarRecord.class);

		// return new BarModel(record);

		return null;
	}

	public BarModel() {
		super(new BarRecord());
	}

	private BarModel(BarRecord record) {
		super(record);
	}

}

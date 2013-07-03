package eu.wltr.restskeleton.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

@Repository
public class TimeRepository extends AbstractRepository<TimeDocument> {

	@Autowired
	public TimeRepository(MongoOperations mongoOperations) {
		super(mongoOperations, TimeDocument.class, "time");

	}

}

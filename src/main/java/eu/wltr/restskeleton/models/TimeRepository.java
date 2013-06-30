package eu.wltr.restskeleton.models;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class TimeRepository {

	@Autowired
	public MongoOperations mongoOperations;

	public List<TimeDocument> findAll() {
		return mongoOperations.findAll(TimeDocument.class);

	}

	public TimeDocument findById(String id) {
		Query q = new Query(Criteria.where("id").is(id));
		return mongoOperations.findOne(q, TimeDocument.class);

	}

	public TimeDocument findByName(String name) {
		Query q = new Query(Criteria.where("name").is(name));
		return mongoOperations.findOne(q, TimeDocument.class);

	}

	public void save(TimeDocument m) {
		mongoOperations.save(m);

	}

	public void remove(TimeDocument m) {
		mongoOperations.remove(m);

	}
}

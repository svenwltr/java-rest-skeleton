package eu.wltr.restskeleton.models;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class BarRepository {

	@Autowired
	public MongoOperations mongoOperations;

	public List<BarDocument> findAll() {
		return mongoOperations.findAll(BarDocument.class);
	}

	public BarDocument findByName(String name) {
		Query q = new Query(Criteria.where("name").is(name));
		return mongoOperations.findOne(q, BarDocument.class);
	}

	public void save(BarDocument m) {
		mongoOperations.save(m);
	}
}

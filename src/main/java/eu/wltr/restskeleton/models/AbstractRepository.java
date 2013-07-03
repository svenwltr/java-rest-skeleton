package eu.wltr.restskeleton.models;

import static org.springframework.data.mongodb.core.query.Criteria.where;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.util.Assert;

public abstract class AbstractRepository<T>
		implements
			CrudRepository<T, String> {

	public final MongoOperations mongoOperations;
	private final Class<T> javaType;
	private final String collectionName;

	public AbstractRepository(MongoOperations mongoOperations,
			Class<T> javaType, String collectionName) {
		this.mongoOperations = mongoOperations;
		this.javaType = javaType;
		this.collectionName = collectionName;

	}

	protected Class<T> getJavaType() {
		return javaType;

	}

	protected String getCollectionName() {
		return collectionName;

	}

	protected String getIdAttribute() {
		return "id";

	}

	private Criteria getIdCriteria(Object id) {
		return where(getIdAttribute()).is(id);
	
	}

	private Query getIdQuery(Object id) {
		return new Query(getIdCriteria(id));

	}

	private List<T> findAll(Query query) {
		if (query == null)
			return Collections.emptyList();

		return mongoOperations.find(query, getJavaType(), getCollectionName());

	}

	@Override
	public long count() {
		return mongoOperations.getCollection(getCollectionName()).count();

	}

	@Override
	public void delete(Iterable<? extends T> entities) {
		for (T entity : entities)
			delete(entity);

	}

	@Override
	public void delete(String id) {
		Assert.notNull(id, "The given id must not be null!");
		mongoOperations.remove(getIdQuery(id), getCollectionName());

	}

	@Override
	public void delete(T entity) {
		Assert.notNull(entity, "The given entity must not be null!");
		mongoOperations.remove(entity, getCollectionName());

	}

	@Override
	public void deleteAll() {
		mongoOperations.remove(new Query(), getCollectionName());

	}

	@Override
	public boolean exists(String id) {
		Assert.notNull(id, "The given id must not be null!");
		return (findOne(id) != null);

	}

	@Override
	public List<T> findAll() {
		return mongoOperations.findAll(getJavaType(), getCollectionName());

	}

	@Override
	public Iterable<T> findAll(Iterable<String> ids) {
		Set<String> parameters = new HashSet<String>();
		for (String id : ids)
			parameters.add(id);

		return findAll(new Query(new Criteria(getIdAttribute()).in(parameters)));

	}

	@Override
	public T findOne(String id) {
		Assert.notNull(id, "The given id must not be null!");
		return mongoOperations.findById(id, getJavaType(), getCollectionName());

	}

	@Override
	public <S extends T> Iterable<S> save(Iterable<S> entities) {
		for (S entity : entities)
			save(entity);

		return entities;

	}

	@Override
	public <S extends T> S save(S entity) {
		Assert.notNull(entity, "Entity must not be null!");
		mongoOperations.save(entity, getCollectionName());
		return entity;

	}

}

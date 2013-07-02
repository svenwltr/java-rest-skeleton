package eu.wltr.restskeleton.models;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.repository.query.MongoEntityInformation;
import org.springframework.data.mongodb.repository.support.SimpleMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public class TimeRepository extends SimpleMongoRepository<TimeDocument, String> {

	@Autowired
	public TimeRepository(MongoOperations mongoOperations) {
		super(new TimeMetadata(), mongoOperations);

	}

	public static class TimeMetadata
			implements
				MongoEntityInformation<TimeDocument, String> {
	
		@Override
		public boolean isNew(TimeDocument entity) {
			return (entity.id == null || entity.id.isEmpty());
	
		}
	
		@Override
		public String getId(TimeDocument entity) {
			return entity.id;
	
		}
	
		@Override
		public Class<String> getIdType() {
			return String.class;
	
		}
	
		@Override
		public Class<TimeDocument> getJavaType() {
			return TimeDocument.class;
	
		}
	
		@Override
		public String getCollectionName() {
			return "time";
	
		}
	
		@Override
		public String getIdAttribute() {
			return "id";
	
		}
	
	}

}

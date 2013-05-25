package eu.wltr.restskeleton.rest.mapper;

import java.io.IOException;
import java.lang.reflect.Type;

import org.apache.commons.codec.binary.Base64;
import org.bson.types.ObjectId;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.SerializerBase;

public class ObjectIdSerializer extends SerializerBase<ObjectId> {
	
	public ObjectIdSerializer() {
		super(ObjectId.class);
	}

	@Override
	public void serialize(ObjectId oid, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonGenerationException {
		String fancyId = new String(Base64.encodeBase64(oid.toByteArray()));
		fancyId = fancyId.replace('/', '-');
		fancyId = fancyId.replace('+', '_');
		
		jgen.writeString(fancyId);
	}

	@Override
	public JsonNode getSchema(SerializerProvider provider, Type typeHint)
			throws JsonMappingException {
		throw new UnsupportedOperationException();
	}

}
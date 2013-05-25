package eu.wltr.restskeleton.mapper;

import java.io.IOException;
import java.lang.reflect.Type;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.SerializerBase;

public class FooFieldSerializer extends SerializerBase<FooField> {

	public FooFieldSerializer() {
		super(FooField.class);
	}

	@Override
	public void serialize(FooField value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonGenerationException {
		jgen.writeString(String.format("%s:%d:%s", value.a, value.b, value.c));
	}

	@Override
	public JsonNode getSchema(SerializerProvider provider, Type typeHint)
			throws JsonMappingException {
		throw new UnsupportedOperationException();
	}

}

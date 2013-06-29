package eu.wltr.restskeleton.fields;

import java.io.IOException;
import java.lang.reflect.Type;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.SerializerBase;

public class DurationSerializer extends SerializerBase<Duration> {
	public DurationSerializer() {
		super(Duration.class);

	}

	@Override
	public void serialize(Duration value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonGenerationException {
		jgen.writeString(String.format("s%s", value.seconds));

	}

	@Override
	public JsonNode getSchema(SerializerProvider provider, Type typeHint)
			throws JsonMappingException {
		throw new UnsupportedOperationException();

	}
}

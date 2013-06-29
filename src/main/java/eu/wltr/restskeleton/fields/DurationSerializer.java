package eu.wltr.restskeleton.fields;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class DurationSerializer extends JsonSerializer<Duration> {

	@Override
	public void serialize(Duration value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonGenerationException {
		jgen.writeString(String.format("s%s", value.seconds));

	}
}

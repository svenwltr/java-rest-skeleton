package eu.wltr.restskeleton.converters.jackson;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.module.SimpleModule;
import org.springframework.stereotype.Service;

import eu.wltr.restskeleton.converters.JacksonConverter;
import eu.wltr.restskeleton.fields.Duration;

@Service
public class DurationSerializer extends JsonSerializer<Duration>
		implements
			JacksonConverter {

	@Override
	public void register(SimpleModule module) {
		module.addSerializer(Duration.class, new DurationSerializer());

	}

	@Override
	public void serialize(Duration value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonGenerationException {
		jgen.writeString(String.format("s%s", value.seconds));

	}
}

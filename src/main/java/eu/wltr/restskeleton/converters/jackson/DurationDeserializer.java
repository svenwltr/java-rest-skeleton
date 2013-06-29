package eu.wltr.restskeleton.converters.jackson;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.module.SimpleModule;
import org.springframework.stereotype.Service;

import eu.wltr.restskeleton.converters.JacksonConverter;
import eu.wltr.restskeleton.fields.Duration;

@Service
public class DurationDeserializer extends JsonDeserializer<Duration>
		implements
			JacksonConverter {

	@Override
	public void register(SimpleModule module) {
		module.addDeserializer(Duration.class, new DurationDeserializer());

	}

	@Override
	public Duration deserialize(JsonParser jsonParser,
			DeserializationContext deserializationContext) throws IOException,
			JsonProcessingException {
		Matcher m = Pattern.compile("^s([0-9]+)$")
				.matcher(jsonParser.getText());

		if (m.matches())
			return new Duration(Integer.parseInt(m.group(1)));
		else
			throw new JsonGenerationException("Invalid Duration format.");

	}

}

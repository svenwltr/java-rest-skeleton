package eu.wltr.restskeleton.fields;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;

public class DurationDeserializer extends JsonDeserializer<Duration> {

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

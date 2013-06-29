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

		long remain = value.seconds;

		long seconds = remain % 60;
		remain /= 60;

		long minutes = remain % 60;
		remain /= 60;

		long hours = remain % 24;
		remain /= 24;

		long days = remain % 30;
		remain /= 30;

		long months = remain % 12;
		remain /= 12;

		String string = "";

		if (seconds != 0)
			string = seconds + "S";

		if (minutes != 0)
			string = minutes + "M" + string;

		if (hours != 0)
			string = hours + "H" + string;

		if (!string.isEmpty())
			string = "T" + string;

		if (days != 0)
			string = days + "D" + string;

		if (months != 0)
			string = months + "M" + string;

		if (remain != 0)
			string = remain + "Y" + string;

		jgen.writeString("P" + string);

	}
}

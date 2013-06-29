package eu.wltr.restskeleton.converters.jackson;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

	public final static Pattern pattern = Pattern
			.compile("^P((\\d+)Y)?((\\d+)M)?((\\d+)D)?(T((\\d+)H)?((\\d+)M)?((\\d+)S)?)?$");

	public final static int POS_YEARS = 2;
	public final static int POS_MONTHS = 4;
	public final static int POS_DAYS = 6;
	public final static int POS_HOURS = 9;
	public final static int POS_MINUTES = 11;
	public final static int POS_SECONDS = 13;

	@Override
	public void register(SimpleModule module) {
		module.addDeserializer(Duration.class, new DurationDeserializer());

	}

	private long getLong(Matcher m, int pos) {
		try {
			return Long.parseLong(m.group(pos));
		} catch (NumberFormatException e) {
			return 0L;
		}
	}

	@Override
	public Duration deserialize(JsonParser jsonParser,
			DeserializationContext deserializationContext) throws IOException,
			JsonProcessingException {
		String text = jsonParser.getText();
		Matcher m = pattern.matcher(text);

		if (m.matches()) {
			long years = getLong(m, POS_YEARS);
			long months = getLong(m, POS_MONTHS);
			long days = getLong(m, POS_DAYS);
			long hours = getLong(m, POS_HOURS);
			long minutes = getLong(m, POS_MINUTES);
			long seconds = getLong(m, POS_SECONDS);

			long result = 0;

			result += years;
			result *= 12;
			result += months;
			result *= 30;
			result += days;
			result *= 24;
			result += hours;
			result *= 60;
			result += minutes;
			result *= 60;
			result += seconds;

			return new Duration(result);

		} else {
			return new Duration(Long.parseLong(text));

		}

	}
}

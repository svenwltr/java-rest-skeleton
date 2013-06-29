package eu.wltr.restskeleton.converters.mongodb;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import eu.wltr.restskeleton.fields.Duration;

@Service
public class DurationWriteConverter implements Converter<Duration, Integer> {

	@Override
	public Integer convert(Duration source) {
		return new Integer((int) source.seconds);

	}

}

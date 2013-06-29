package eu.wltr.restskeleton.converters.mongodb;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

import eu.wltr.restskeleton.fields.Duration;

@Service
public class DurationReadConverter implements Converter<Integer, Duration> {

	@Override
	public Duration convert(Integer source) {
		return new Duration(source);

	}

}

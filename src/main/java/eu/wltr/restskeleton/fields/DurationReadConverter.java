package eu.wltr.restskeleton.fields;

import org.springframework.core.convert.converter.Converter;

public class DurationReadConverter implements Converter<Integer, Duration> {

	@Override
	public Duration convert(Integer source) {
		return new Duration(source);

	}

}

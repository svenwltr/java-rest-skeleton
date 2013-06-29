package eu.wltr.restskeleton.fields;

import org.springframework.core.convert.converter.Converter;

public class DurationWriteConverter implements Converter<Duration, Integer> {

	@Override
	public Integer convert(Duration source) {
		return new Integer((int) source.seconds);

	}

}

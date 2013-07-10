package eu.wltr.restskeleton.converters;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

@Service
@SuppressWarnings("serial")
public class JacksonObjectMapper extends ObjectMapper {

	private static final String moduleName = "ConversionModule";

	@Autowired
	public JacksonObjectMapper(Set<JacksonConverter> converters) {
		SimpleModule module = new SimpleModule(moduleName);

		for (JacksonConverter converter : converters)
			converter.register(module);

		registerModule(module);

	}
}

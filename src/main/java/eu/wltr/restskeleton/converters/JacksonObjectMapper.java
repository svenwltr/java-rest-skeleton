package eu.wltr.restskeleton.converters;

import java.util.Set;

import org.codehaus.jackson.Version;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.module.SimpleModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JacksonObjectMapper extends ObjectMapper {

	private static final Version moduleVersion = new Version(1, 0, 0, null);
	private static final String moduleName = "ConversionModule";

	@Autowired
	public JacksonObjectMapper(Set<JacksonConverter> converters) {
		SimpleModule module = new SimpleModule(moduleName, moduleVersion);

		for (JacksonConverter converter : converters)
			converter.register(module);

		registerModule(module);
	}
}

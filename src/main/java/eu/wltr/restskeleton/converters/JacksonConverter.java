package eu.wltr.restskeleton.converters;

import org.codehaus.jackson.map.module.SimpleModule;

public interface JacksonConverter {
	public void register(SimpleModule module);

}

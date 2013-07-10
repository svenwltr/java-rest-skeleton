package eu.wltr.restskeleton.converters;

import com.fasterxml.jackson.databind.module.SimpleModule;

public interface JacksonConverter {
	public void register(SimpleModule module);

}

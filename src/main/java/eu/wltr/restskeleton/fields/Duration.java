package eu.wltr.restskeleton.fields;

import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

@JsonSerialize(using = DurationSerializer.class)
@JsonDeserialize(using = DurationDeserializer.class)
public class Duration {

	public final long seconds;

	public Duration(long seconds) {
		this.seconds = seconds;

	}

}

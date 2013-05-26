package eu.wltr.restskeleton.rest.mapper;


import java.io.IOException;
import java.lang.reflect.Type;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.ser.SerializerBase;

import eu.wltr.restskeleton.models.ModelBase;



@SuppressWarnings("rawtypes")
// the generic type of ModelBase doesn't matter
public class ModelSerializer extends SerializerBase<ModelBase>
{

	public ModelSerializer() {
		super(ModelBase.class);
	}

	@Override
	public void serialize(ModelBase value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonGenerationException
	{
		ModelBase<?> model = (ModelBase<?>) value;

		jgen.writeObject(model.cloneRecord());
	}

	@Override
	public JsonNode getSchema(SerializerProvider provider, Type typeHint)
			throws JsonMappingException
	{
		throw new UnsupportedOperationException();
	}

}

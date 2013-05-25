package eu.wltr.restskeleton.rest.resources;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import eu.wltr.restskeleton.models.BarRecord;
import eu.wltr.restskeleton.rest.mapper.FooField;
import eu.wltr.restskeleton.rest.statuscodes.StatusNotFound;
import eu.wltr.restskeleton.server.App;

@Controller
@RequestMapping("/skeleton/foo")
public class FooResource {
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody List<BarRecord> listModels()
	{
		List<BarRecord> foo = App.getMongoOperations().findAll(BarRecord.class);
		
		return foo;
	}
	
	@RequestMapping(value="{name}", method = RequestMethod.GET)
	public @ResponseBody BarRecord getModel(@PathVariable String name)
	{
		Query q = new Query(Criteria.where("name").is(name));
		BarRecord result = App.getMongoOperations().findOne(q, BarRecord.class);
		
		if(result == null)
			throw new StatusNotFound();
		
		return result;
	}

	@RequestMapping(value="{name}", method = RequestMethod.POST)
	public @ResponseBody BarRecord postModel(@PathVariable String name)
	{
		BarRecord m = new BarRecord();
		
		m.name = name;
		m.count = name.length();
		m.date = new Date();
		m.foo = new FooField();
		m.foo.a = "a";
		m.foo.b = 2;
		m.foo.c = "c";
		
		App.getMongoOperations().save(m);
		
		return m;
	}
}
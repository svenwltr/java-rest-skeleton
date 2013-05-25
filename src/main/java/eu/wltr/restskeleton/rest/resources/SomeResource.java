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

import eu.wltr.restskeleton.models.SomeModel;
import eu.wltr.restskeleton.rest.mapper.FooField;
import eu.wltr.restskeleton.rest.statuscodes.StatusNotFound;
import eu.wltr.restskeleton.server.App;

@Controller
@RequestMapping("/skeleton/test")
public class SomeResource {
	
	@RequestMapping(method=RequestMethod.GET)
	public @ResponseBody List<SomeModel> listModels()
	{
		List<SomeModel> foo = App.getMongoOperations().findAll(SomeModel.class);
		
		return foo;
	}
	
	@RequestMapping(value="{name}", method = RequestMethod.GET)
	public @ResponseBody SomeModel getModel(@PathVariable String name)
	{
		Query q = new Query(Criteria.where("name").is(name));
		SomeModel result = App.getMongoOperations().findOne(q, SomeModel.class);
		
		if(result == null)
			throw new StatusNotFound();
		
		return result;
	}

	@RequestMapping(value="{name}", method = RequestMethod.POST)
	public @ResponseBody SomeModel postModel(@PathVariable String name)
	{
		SomeModel m = new SomeModel();
		
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
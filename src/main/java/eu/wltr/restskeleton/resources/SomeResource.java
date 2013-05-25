package eu.wltr.restskeleton.resources;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import eu.wltr.restskeleton.mapper.FooField;
import eu.wltr.restskeleton.models.SomeModel;
import eu.wltr.restskeleton.server.App;

@Controller
@RequestMapping("/skeleton/test")
public class SomeResource {

	@RequestMapping(value="{name}", method = RequestMethod.GET)
	public @ResponseBody SomeModel getModel(@PathVariable String name)
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
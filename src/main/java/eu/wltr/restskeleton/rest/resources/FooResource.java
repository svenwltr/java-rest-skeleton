package eu.wltr.restskeleton.rest.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import eu.wltr.restskeleton.models.BarRecord;
import eu.wltr.restskeleton.models.BarRepository;
import eu.wltr.restskeleton.rest.mapper.FooField;

@Controller
@RequestMapping("/skeleton/foo")
public class FooResource {

	@Autowired
	public BarRepository barRepository;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	List<BarRecord> listModels() {
		return barRepository.findAll();
	}

	@RequestMapping(value = "{name}", method = RequestMethod.GET)
	public @ResponseBody
	BarRecord getModel(@PathVariable String name) {
		return barRepository.findByName(name);
	}

	@RequestMapping(value = "{name}", method = RequestMethod.POST)
	public @ResponseBody
	BarRecord postModel(@PathVariable String name) {
		BarRecord m = new BarRecord();

		m.name = name;
		m.count = name.length();
		m.date = new Date();
		m.foo = new FooField();
		m.foo.a = "a";
		m.foo.b = 2;
		m.foo.c = "c";

		barRepository.save(m);

		return m;
	}
}

package eu.wltr.restskeleton.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import eu.wltr.restskeleton.models.TimeDocument;
import eu.wltr.restskeleton.models.TimeRepository;

@Controller
@RequestMapping("/time")
public class TimeController {
	@Autowired
	public TimeRepository timeRepository;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	List<TimeDocument> listModels() {
		return timeRepository.findAll();

	}

	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody
	TimeDocument postModel(@RequestBody TimeDocument doc) {
		timeRepository.save(doc);
		return doc;

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public @ResponseBody
	TimeDocument delete(@PathVariable("id") String id) {
		TimeDocument m = timeRepository.findById(id);
		timeRepository.remove(m);
		return m;

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public @ResponseBody
	TimeDocument update(@PathVariable("id") String id,
			@RequestBody TimeDocument doc) {
		timeRepository.save(doc);
		return doc;

	}

}

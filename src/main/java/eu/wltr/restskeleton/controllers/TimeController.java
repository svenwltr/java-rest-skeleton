package eu.wltr.restskeleton.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import eu.wltr.restskeleton.fields.Duration;
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
		doc.date = new Date();
		doc.duration = new Duration(5000000L);

		timeRepository.save(doc);
		return doc;

	}

}

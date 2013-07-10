package eu.wltr.restskeleton.controllers.meta;

import java.util.Map;
import java.util.Set;

import javax.validation.metadata.BeanDescriptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import eu.wltr.restskeleton.services.validation.ValidationService;

@Controller
@RequestMapping("/meta/validation")
public class ValidationMetaController {

	@Autowired
	private ValidationService validationService;

	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody
	Map<String, Set<String>> listModels() {
		return validationService
				.getContraints("eu.wltr.restskeleton.models.TimeDocument");
	}

	@RequestMapping(value = "/bd", method = RequestMethod.GET)
	public @ResponseBody
	BeanDescriptor getBeanDescriptor() {
		return validationService
				.getBeanDescriptor("eu.wltr.restskeleton.models.TimeDocument");
	}

}

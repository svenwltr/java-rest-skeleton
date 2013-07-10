package eu.wltr.restskeleton.services.validation;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.metadata.BeanDescriptor;
import javax.validation.metadata.ConstraintDescriptor;
import javax.validation.metadata.PropertyDescriptor;

import org.springframework.stereotype.Service;

@Service
public class ValidationService {

	private static final ValidatorFactory FACTORY = Validation
			.buildDefaultValidatorFactory();
	private static final Validator VALIDATOR = FACTORY.getValidator();

	public BeanDescriptor getBeanDescriptor(String className) {
		try {
			return getBeanDescriptor(Class.forName(className));

		} catch (ClassNotFoundException e) {
			return null;

		}

	}

	public BeanDescriptor getBeanDescriptor(Class<?> cls) {
		BeanDescriptor bd = VALIDATOR.getConstraintsForClass(cls);
		return bd;

	}

	public Map<String, Set<String>> getContraints(String className) {
		try {
			return getContraints(Class.forName(className));

		} catch (ClassNotFoundException e) {
			return null;

		}
	}

	@SuppressWarnings("rawtypes")
	public Map<String, Set<String>> getContraints(Class<?> cls) {
		Map<String, Set<String>> map = new HashMap<String, Set<String>>();

		BeanDescriptor bd = VALIDATOR.getConstraintsForClass(cls);

		for (PropertyDescriptor prop : bd.getConstrainedProperties()) {
			Set<String> set = new HashSet<String>();

			for (ConstraintDescriptor descriptor : prop
					.getConstraintDescriptors()) {
				set.add(descriptor.getAnnotation().annotationType()
						.getCanonicalName());

			}

			map.put(prop.getPropertyName(), set);

		}

		return map;
	}
}

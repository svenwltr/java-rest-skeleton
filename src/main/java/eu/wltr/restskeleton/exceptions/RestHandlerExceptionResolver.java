package eu.wltr.restskeleton.exceptions;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.PriorityOrdered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

@Service
public class RestHandlerExceptionResolver implements HandlerExceptionResolver,
		PriorityOrdered {

	public static class Response {
		public String message;
		public String exception;
		public int code;
	}

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		Response r = new Response();
		r.message = ex.getMessage();
		r.exception = ex.getClass().toString();
		r.code = HttpStatus.INTERNAL_SERVER_ERROR.value();

		ModelAndView mav = new ModelAndView(new MappingJackson2JsonView());
		mav.addObject("error", r);
		response.setStatus(r.code);
		return mav;

	}

	@Override
	public int getOrder() {
		return 0;

	}
}

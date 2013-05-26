package eu.wltr.restskeleton.rest.statuscodes;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;



@ResponseStatus(value = HttpStatus.NOT_FOUND)
public final class StatusNotFound extends RuntimeException
{
	private static final long serialVersionUID = 1L;
}

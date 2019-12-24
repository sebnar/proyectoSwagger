package com.macroproyectos.cargue.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1605770292496886338L;

	public NotFoundException(String paramString) {
		super(paramString);
	}

}

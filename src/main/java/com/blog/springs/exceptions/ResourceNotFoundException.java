package com.blog.springs.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ResourceNotFoundException extends RuntimeException{
	public ResourceNotFoundException(String resourceName, String fieldName, long fieldValue) {
		super(String.format("%s not found with %s : %s", resourceName,fieldName,fieldValue));
		this.resourceName = resourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	String resourceName;
	String fieldName;
	long fieldValue;

}

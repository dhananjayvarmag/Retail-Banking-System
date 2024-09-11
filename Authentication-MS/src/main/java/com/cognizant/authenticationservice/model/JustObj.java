package com.cognizant.authenticationservice.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

public class JustObj {
	
	@Min(value=500, message="{abc.abc}") 
	public int abc;

}

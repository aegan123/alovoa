package com.nonononoki.alovoa.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PasswordChangeDto {

	private String email;
	private String password;
	private String token;
}

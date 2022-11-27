package com.datajpa.relationship.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

	@NotBlank
	@Size(max = 20)
	private String username;

	@NotBlank
	@Size(min = 6, max = 40)
	private String password;


}

package com.eliasfb.efw.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final Integer OK_CODE = 0;
	public static final Integer ERROR_CODE = -1;
	public static final Integer UNIQUE_CONSTRAINT_CODE = -2;

	private Integer errorCode;
	private String message;
}

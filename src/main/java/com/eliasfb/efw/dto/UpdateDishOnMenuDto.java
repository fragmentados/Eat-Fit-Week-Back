package com.eliasfb.efw.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class UpdateDishOnMenuDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer dishId;
	private String oldDate;
	private String newDate;
}

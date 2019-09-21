package com.eliasfb.efw.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMenuTemplateDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer userId;
	private Integer menuId;
	private String name;
}

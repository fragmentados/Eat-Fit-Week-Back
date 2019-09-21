package com.eliasfb.efw.dto.stat;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;
	private String value;
}

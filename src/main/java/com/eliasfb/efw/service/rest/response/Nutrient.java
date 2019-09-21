package com.eliasfb.efw.service.rest.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Nutrient implements Serializable {

	private static final long serialVersionUID = 1L;

	private String label;
	private Double quantity;
	private String unit;
}

package com.eliasfb.efw.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UnitAndQuantity {
	private Long unit;
	private Double quantity;
}
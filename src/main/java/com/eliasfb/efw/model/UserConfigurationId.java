package com.eliasfb.efw.model;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserConfigurationId implements Serializable {

	private static final long serialVersionUID = 1L;

	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;
}

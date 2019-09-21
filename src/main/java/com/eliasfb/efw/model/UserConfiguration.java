package com.eliasfb.efw.model;

import javax.persistence.AssociationOverride;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "userconfiguration")
@Data
@AssociationOverride(name = "id.user", joinColumns = @JoinColumn(name = "user_id"))
public class UserConfiguration {
	@EmbeddedId
	private UserConfigurationId id;
	@Column
	private String value;
}

package com.eliasfb.efw.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "meal")
@Data
@NoArgsConstructor
@ToString(exclude = { "dishes", "user" })
@EqualsAndHashCode(exclude = { "dishes", "user" })
public class Meal {

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String name;
	@Column
	private String hour;

	@OneToMany(mappedBy = "id.meal", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<DisMealRel> dishes;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private User user;

	public Meal(Integer id) {
		super();
		this.id = id;
	}

	public Meal(String name, String hour, User user) {
		super();
		this.name = name;
		this.hour = hour;
		this.user = user;
	}

}

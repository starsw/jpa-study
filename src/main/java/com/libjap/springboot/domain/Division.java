package com.libjap.springboot.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="DIVISION")
@Getter
@Setter
public class Division {
	@Id
	@Column(name="ID")
	private Integer id;

	@Column(name="PARTNAME")
	private String partname;

}

package com.libjap.springboot.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="MEMBER")
@Getter
@Setter
public class Member {
	@Id
	@Column(name="ID")
	private String id;

	@Column(name="NAME")
	private String username;

	//맵핑 정보가 없는 필드
	private Integer age;
}

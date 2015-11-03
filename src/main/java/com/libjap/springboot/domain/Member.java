package com.libjap.springboot.domain;

import lombok.Getter;
import lombok.Setter;

import javax.lang.model.element.Name;
import javax.persistence.*;

@Entity
@Table(name = "MEMBER")
@Getter
@Setter
public class Member {
    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String username;

    @Column(name = "AGE")
    private Integer age;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TEAM_ID")
    private Team team;
}

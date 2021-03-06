package com.sample.dropwizard.entity;

import javax.persistence.*;

/**
 * Created by ankush.a on 23/03/17.
 */
@Entity
@Table(name = "person")
@TableGenerator(name = "idGen",table = "ID_GEN")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "idGen")
    private Long id;

    @Column
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

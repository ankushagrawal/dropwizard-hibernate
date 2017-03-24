package com.sample.dropwizard.entity;

import javax.persistence.*;

/**
 * Created by ankush.a on 24/03/17.
 */
@Entity
@Table(name="place")
@TableGenerator(name = "idGen",table = "ID_GEN")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "idGen")
    private Long id;

    public void setName(String name) {
        this.name = name;
    }

    private String name;
}

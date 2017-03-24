package com.sample.dropwizard.entity;

import javax.persistence.*;

/**
 * Created by ankush.a on 24/03/17.
 */
@Entity
@Table(name = "place_child")
@TableGenerator(name = "idGen",table = "ID_GEN")
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "idGen")
    private Long id;

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setParentPlace(Parent parentPlace) {
        this.parent = parentPlace;
    }

    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    private Parent parent;
}

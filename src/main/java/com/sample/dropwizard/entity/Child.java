package com.sample.dropwizard.entity;

import javax.persistence.*;

/**
 * Created by ankush.a on 24/03/17.
 */
@Entity
@Table(name = "child")
@TableGenerator(name = "idGen",table = "ID_GEN")
public class Child {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "idGen")
    private Long id;

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    @ManyToOne( cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_id")
    private Parent parent;
}

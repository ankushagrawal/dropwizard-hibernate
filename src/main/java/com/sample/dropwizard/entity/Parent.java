package com.sample.dropwizard.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ankush.a on 24/03/17.
 */
@Entity
@Table(name="place")
@TableGenerator(name = "idGen",table = "ID_GEN")
public class Parent {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "idGen")
    private Long id;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    private String name;


    public void setChildren(List<Child> children) {
        this.children = children;
    }

    public List<Child> getChildren() {
        return children;
    }

    @OneToMany(mappedBy = "parent",cascade = CascadeType.ALL)
    private List<Child> children = new ArrayList<Child>();

}

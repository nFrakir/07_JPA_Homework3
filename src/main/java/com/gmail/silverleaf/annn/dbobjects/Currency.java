package com.gmail.silverleaf.annn.dbobjects;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "currencies")
public class Currency {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String abbreviation;

    public Currency(String abbreviation) {
        super();
        this.abbreviation = abbreviation;
    }

    public Currency() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    @Override
    public String toString() {
        return "Currency{" +
                "id=" + id +
                ", abbreviation='" + abbreviation + '\'' +
                '}';
    }
}

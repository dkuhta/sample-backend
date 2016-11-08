package com.sample.persons;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author serge
 */
@Entity
public class PersonEntity implements Serializable {

    private static final long serialVersionUID = -1958421373759856954L;

    private Long id;
    private String name;

    @Id
    @GeneratedValue(generator = "PersonEntity")
    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}

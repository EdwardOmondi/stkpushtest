package me.edward.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "configuration")
public class Configuration extends AbstractEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private String value;

    public Configuration() {
    }

    @PrePersist
    @PreUpdate
    public void update() {
        setCreatedDate(new Date());
        setActive(true);
        setDeleted(false);
        if (super.getRef() == null) {
            setRef(UUID.randomUUID().toString());
        }
    }

    @Override
    public String toString() {
        return "Configuration{" +
                ", name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}

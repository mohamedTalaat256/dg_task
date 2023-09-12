package com.example.demo.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "t_entities")
@Getter
@Setter
@Builder
@Audited
public class TEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "commercial_name", length = 255)
    private String commercialName;

    @Column(name = "business", length = 255)
    private String business;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Phone> phones = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<>();

    @OneToMany(mappedBy = "entity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Person> directors = new ArrayList<>();

    public TEntity(Long id, String name, String commercialName, String business, List<Phone> phones, List<Address> addresses, List<Person> directors) {
        this.id = id;
        this.name = name;
        this.commercialName = commercialName;
        this.business = business;
        this.phones = phones;
        this.addresses = addresses;
        this.setDirectors(directors);
    }


    public TEntity() {
    }

    public void setDirectors(List<Person> directors) {
        directors.forEach(this::addDirector);
    }

    public void addDirector(Person person) {
        directors.add(person);
        person.setEntity(this);
    }

    public void removeDirector(Person person) {
        directors.remove(person);
        person.setEntity(null);
    }

}

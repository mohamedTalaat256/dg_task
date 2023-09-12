package com.example.demo.entity;


import com.example.demo.embeded.Email;
import com.example.demo.embeded.PassportNumber;
import com.example.demo.enumeration.Gender;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.envers.Audited;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "persons")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Audited
public class Person {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;



    @Column(name = "title", length = 30)
    private String title;




    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;



    @Column(name = "ssn", length = 25, nullable = false)
    private String ssn;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride( name = "passportNumber" ,  column = @Column(name = "passport_number", length = 255)),
            @AttributeOverride( name = "passportCountry", column = @Column(name = "passport_country")),
    })
    private PassportNumber passportNumber;

    @Embedded
    private Email email;


    @OneToMany(cascade = CascadeType.ALL)
    private List<Phone> phones = new ArrayList<Phone>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Address> addresses = new ArrayList<Address>();




    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "entity_id")
    @JsonBackReference
    private TEntity entity;


    public Person(Gender gender,
                  String title,
                  String firstName,
                  String lastName,
                  String ssn,
                  PassportNumber passportNumber,
                  Email email,
                  List<Phone> phones,
                  List<Address> addresses
    ){
        this.gender = gender;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.passportNumber = passportNumber;
        this.email = email;
        this.phones = phones;
        this.addresses = addresses;
    }

    public Person(Gender gender,
                  String title,
                  String firstName,
                  String lastName,
                  String ssn,
                  PassportNumber passportNumber,
                  Email email,
                  List<Phone> phones,
                  List<Address> addresses,
                  TEntity entity
    ){
        this.gender = gender;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ssn = ssn;
        this.passportNumber = passportNumber;
        this.email = email;
        this.phones = phones;
        this.addresses = addresses;
        this.entity = entity;
    }


}

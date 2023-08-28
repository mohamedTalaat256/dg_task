package com.example.dg_task.entity;


import com.example.dg_task.enumeration.CommunicationType;
import com.example.dg_task.enumeration.CountryCode;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "addresses")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Audited
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "address_type")
    @Enumerated(EnumType.STRING)
    private CommunicationType addressType;


    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "town", length = 255)
    private String town;

    @Column(name = "city", length = 255)
    private String city;

    @Column(name = "zip", length = 10)
    private String zip;

    @Column(name = "country_code")
    @Enumerated(EnumType.STRING)
    private CountryCode  countryCode;


    @Column(name = "state", length = 255)
    private String state;

    @Column(name = "comments", length = 4000)
    private String  comments;
}

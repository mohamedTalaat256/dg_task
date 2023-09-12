package com.example.demo.entity;


import com.example.demo.enumeration.CommunicationType;
import com.example.demo.enumeration.ContactType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "phones")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Audited
public class Phone {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "tph_contact_type")
    @Enumerated(EnumType.STRING)
    private ContactType tphContactType;


    @Column(name = "tph_communication_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private CommunicationType tphCommunicationType;

    @Column(name = "tph_country_prefix", length = 4)
    private String tphCountryPrefix;


    @Column(name = "tph_number", length = 50, nullable = false)
    private String tphNumber;

    @Column(name = "tph_extension", length = 10)
    private String  tphExtension;

    @Column(name = "comments", length = 4000)
    private String  comments;
}

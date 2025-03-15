package com.person.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tbl_person")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonInfo extends Person {

    @Column(name = "contact")
    private long contact;

    @Column(name = "address")
    private String address;

    @Column(name = "city")
    private String city;

}

package com.java.wonderrelationship.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cityId;
    @Column(nullable = false,unique = true)
    private String cityName;

    //this is the owner class of the Country
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "country_id_foreignKey")
    @JsonBackReference
    private Country country;

}

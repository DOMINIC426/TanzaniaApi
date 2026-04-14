package com.java.wonderrelationship.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long countryId;
    private String countryName;
    @Column(nullable = false,unique = true)
    private String countryCode;

    //relationship with city
    @OneToMany(mappedBy = "country",cascade = CascadeType.ALL,orphanRemoval = true)
    @JsonManagedReference
    private List<City> cities = new ArrayList<>();

    //just for adding this is helper
    public  void addCity(City city){
        cities.add(city);
        city.setCountry(this);
    }

}

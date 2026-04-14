package com.java.wonderrelationship.controller;

import com.java.wonderrelationship.entity.City;
import com.java.wonderrelationship.entity.Country;
import com.java.wonderrelationship.service.CountryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CountryController {
    private CountryService countryService;
    public CountryController(CountryService countryService){
        this.countryService=countryService;
    }
    @GetMapping("/countries")
    public ResponseEntity<List<Country>> getAll(){
        return ResponseEntity.ok(countryService.getAllCountries());
    }

    @PostMapping("/countries/add")
    public ResponseEntity<Country> save(@RequestBody  Country country){
        return ResponseEntity.status(HttpStatus.CREATED).body(countryService.saveCountry(country));
    }


    //adding the city
    @PostMapping("/cities/{id}/add")
    public ResponseEntity<City> addCity(@PathVariable Long id,@RequestBody City city){
         return ResponseEntity.status(HttpStatus.CREATED).body(countryService.addCity(id,city));
    }

    //get cities
    @GetMapping("/cities")
    public ResponseEntity<List<City>> getCities(){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(countryService.getAllCities());
    }

    @PatchMapping("/contries/edit/{id}")
    public ResponseEntity<Country> editCountry(@PathVariable Long id,@RequestBody Country country){
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(countryService.editCountry(id,country));
    }

    //delete logics
    @DeleteMapping("/countries/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        countryService.deleteCountry(id);
        return ResponseEntity.status(HttpStatus.GONE).body("Data Deleted successfully");
    }


}

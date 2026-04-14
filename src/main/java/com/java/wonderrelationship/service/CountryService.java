package com.java.wonderrelationship.service;

import com.java.wonderrelationship.entity.City;
import com.java.wonderrelationship.entity.Country;
import com.java.wonderrelationship.repository.CityRepository;
import com.java.wonderrelationship.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class CountryService {
   private CountryRepository countryRepository;
   private CityRepository cityRepository;
   public CountryService(CountryRepository countryRepository,CityRepository cityRepository){
       this.countryRepository=countryRepository;
       this.cityRepository=cityRepository;
   }

    //get all countries
    public List<Country> getAllCountries(){
        return  countryRepository.findAll();
    }

    //save Country
    public Country saveCountry(Country country){
         return countryRepository.save(country);
    }

    //adding the city
    public City addCity(Long id ,City city){
        Country country =countryRepository.findById(id).orElseThrow(()->new RuntimeException("The Country Do not Exist"));
         city.setCountry(country);
         return cityRepository.save(city);
    }


    public  List<City>  getAllCities() {
       return cityRepository.findAll();
    }

    public Country editCountry(Long id, Country country) {
        // 1. Find the existing record by the URL ID
        Country exist = countryRepository.findById(id).orElseThrow(() -> new RuntimeException("This Id Does not Exist"));

        //only update if the value is not null this prevents null save in database
        if(country.getCountryName()!=null){
            exist.setCountryName(country.getCountryName());
        }
        if(country.getCountryCode()!=null){
            exist.setCountryCode(country.getCountryCode());
        }

        return countryRepository.save(country);

    }


    //edit country

}

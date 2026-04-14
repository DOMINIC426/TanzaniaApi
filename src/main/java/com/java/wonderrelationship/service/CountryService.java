package com.java.wonderrelationship.service;

import com.java.wonderrelationship.entity.City;
import com.java.wonderrelationship.entity.Country;
import com.java.wonderrelationship.exception.ResourceNotFoundException;
import com.java.wonderrelationship.repository.CityRepository;
import com.java.wonderrelationship.repository.CountryRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CountryService {
   private final CountryRepository countryRepository;
   private final CityRepository cityRepository;
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
        Country country =countryRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("The id Do not Exist"));
         city.setCountry(country);
         return cityRepository.save(city);
    }


    public  List<City>  getAllCities() {
       return cityRepository.findAll();
    }

    //edit the country
    public Country editCountry(Long id, Country country) {
        // 1. Find the existing record by the URL ID
        Country exist = countryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("This Country With Id "+id +" "+"Do not found"));

        //only update if the value is not null this prevents null save in database
        if(country.getCountryName()!=null){
            exist.setCountryName(country.getCountryName());
        }
        if(country.getCountryCode()!=null){
            exist.setCountryCode(country.getCountryCode());
        }

        return countryRepository.save(exist);

    }

    //delete the country
    public void deleteCountry(Long id){
       if(!countryRepository.existsById(id)){
           throw new ResourceNotFoundException("This Id do not Exist");
       }
        countryRepository.deleteById(id);
    }

}

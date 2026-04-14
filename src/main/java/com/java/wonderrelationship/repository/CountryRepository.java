package com.java.wonderrelationship.repository;

import com.java.wonderrelationship.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country,Long> {
}

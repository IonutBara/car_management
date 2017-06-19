package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Country;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * CountryRepository
 */
public interface CountryRepository extends JpaRepository<Country, Long> {
}

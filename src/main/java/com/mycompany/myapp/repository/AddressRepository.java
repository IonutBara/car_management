package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}

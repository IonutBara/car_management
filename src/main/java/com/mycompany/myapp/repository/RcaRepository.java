package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.auto.AsigurareRCA;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * RcaRepository interface
 */
public interface RcaRepository extends JpaRepository<AsigurareRCA, Long> {
}

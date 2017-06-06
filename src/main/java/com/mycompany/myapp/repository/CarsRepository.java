package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.auto.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarsRepository extends JpaRepository<Car, Long> {

    @Query("select car from Car car INNER JOIN car.user user WHERE user.id=?1")
    List<Car> listCarsByUser(Long id);
}

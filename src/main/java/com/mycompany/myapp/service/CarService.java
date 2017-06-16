package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.Authority;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.domain.auto.Car;
import com.mycompany.myapp.exceptions.UserUnauthorizedException;
import com.mycompany.myapp.repository.CarsRepository;
import com.mycompany.myapp.security.AuthoritiesConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class CarService {

    private final static Logger LOGGER = LoggerFactory.getLogger(CarService.class);

    private CarsRepository carsRepository;

    @Inject
    public CarService(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    /**
     * method which check if user Authority is ADMIN
     * and also if user is car owner
     *
     * @param current
     * @param carToEdit
     */
    public void checkIfIsAuthorized(User current, Car carToEdit) throws UserUnauthorizedException {
        Set<Authority> authorities = current.getAuthorities();
        for (Authority auth : authorities) {
            if (auth.getName().equalsIgnoreCase(AuthoritiesConstants.ADMIN)) {
                LOGGER.debug("Admin {} is authorized to edit car {}", current.getEmail(), carToEdit.getName());
                return;
            }
        }
        User carToEditOwner = carToEdit.getUser();
        if (carToEditOwner.equals(current)) {
            LOGGER.debug("User {} is authorized to edit car {}", current.getEmail(), carToEdit.getName());
        } else {
            throw new UserUnauthorizedException("The user is not authorized to modify car.");
        }
    }

    public void createCar(String name, String description,
                          String marca, String model,
                          String versiune, String anFabricatie,
                          String capacitateCilindrica, String combustibil,
                          String cutieViteza, String transmisie,
                          String caroserie, String culoare,
                          String putere, String nrInmatriculare,
                          double nrKm, User user) {
        Car car = new Car();
        car.setName(name);
        car.setDescription(description);
        car.setMarca(marca);
        car.setModel(model);
        car.setVersiune(versiune);
        car.setAnFabricatie(anFabricatie);
        car.setCapacitateCilindrica(capacitateCilindrica);
        car.setCombustibil(combustibil);
        car.setCutieViteza(cutieViteza);
        car.setTransmisie(transmisie);
        car.setCaroserie(caroserie);
        car.setCuloare(culoare);
        car.setPutere(putere);
        car.setNrInmatriculare(nrInmatriculare);
        car.setNrKm(nrKm >= 0 ? nrKm : 0);
        car.setUser(user);
        carsRepository.save(car);
        LOGGER.info("Car saved successfully :{}", car.toString());
    }

    public void updateCar(Long id, String name, String description,
                          String marca, String model,
                          String versiune, String anFabricatie,
                          String capacitateCilindrica, String combustibil,
                          String cutieViteza, String transmisie,
                          String caroserie, String culoare,
                          String putere, String nrInmatriculare,
                          double nrKm) {
        Optional.of(carsRepository
            .findOne(id))
            .ifPresent(car -> {
                car.setName(name);
                car.setDescription(description);
                car.setMarca(marca);
                car.setModel(model);
                car.setVersiune(versiune);
                car.setAnFabricatie(anFabricatie);
                car.setCapacitateCilindrica(capacitateCilindrica);
                car.setCombustibil(combustibil);
                car.setCutieViteza(cutieViteza);
                car.setTransmisie(transmisie);
                car.setCaroserie(caroserie);
                car.setCuloare(culoare);
                car.setPutere(putere);
                car.setNrInmatriculare(nrInmatriculare);
                car.setNrKm(nrKm);
                LOGGER.debug("Changed Information for Car: {}", car);
            });
    }
}

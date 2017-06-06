package com.mycompany.myapp.web.rest.controllers;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.domain.auto.Car;
import com.mycompany.myapp.exceptions.CarNotFoundException;
import com.mycompany.myapp.exceptions.UserUnauthorizedException;
import com.mycompany.myapp.repository.CarsRepository;
import com.mycompany.myapp.repository.UserRepository;
import com.mycompany.myapp.security.AuthoritiesConstants;
import com.mycompany.myapp.security.SecurityUtils;
import com.mycompany.myapp.service.CarService;
import com.mycompany.myapp.service.dto.CarDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * REST controller for managing the cars process.
 */
@RestController
@RequestMapping("/api")
public class CarResource {

    private final Logger LOGGER = LoggerFactory.getLogger(CarResource.class);

    private CarsRepository carsRepository;
    private UserRepository userRepository;
    private CarService carService;

    @Inject
    public CarResource(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Inject
    public void setCarsRepository(CarsRepository carsRepository) {
        this.carsRepository = carsRepository;
    }

    @Inject
    public void setCarService(CarService carService) {
        this.carService = carService;
    }

    /**
     * GET  /cars : list all existent Cars.
     *
     * @return the ResponseEntity with status 200 (OK) and all cars in body,
     * or status 500 (Internal Server Error) if the list couldn't be returned
     */
    @GetMapping("/cars")
    @Timed
    @Secured({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    public ResponseEntity<List<Car>> listAll() {
        boolean isAdmin = SecurityUtils.isCurrentUserInRole(AuthoritiesConstants.ADMIN);
        List<Car> carList;
        if (isAdmin) {
            carList = carsRepository.findAll();
        } else {
            User userLogged = userRepository.getUserByLogin(SecurityUtils.getCurrentUserLogin());
           carList = carsRepository.listCarsByUser(userLogged.getId());
        }
        return new ResponseEntity<>(carList, HttpStatus.OK);
    }

    @PostMapping("/cars")
    @Timed
    @Secured({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    public ResponseEntity<?> addCar(@RequestBody CarDTO carDTO) {
        long userID = 3;
        User userLogged = userRepository.getUserByLogin(SecurityUtils.getCurrentUserLogin());
        LOGGER.debug("User userLogged {} ", userLogged);
        if (userID >= 0) {
            userLogged = userRepository.findOne(userID);
        }
        carService.createCar(carDTO.getName(), carDTO.getDescription(), carDTO.getMarca(),
            carDTO.getModel(), carDTO.getVersiune(), carDTO.getAnFabricatie(), carDTO.getCapacitateCilindrica(),
            carDTO.getCombustibil(), carDTO.getCutieViteza(), carDTO.getTransmisie(), carDTO.getCaroserie(),
            carDTO.getCuloare(), carDTO.getPutere(), carDTO.getNrInmatriculare(), carDTO.getNrKm(), userLogged);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/cars/{id}")
    @Timed
    @Secured({AuthoritiesConstants.ADMIN})
    public ResponseEntity<?> deleteCar(@PathVariable Long id) throws CarNotFoundException, UserUnauthorizedException {
        Car toDelete = carsRepository.findOne(id);
        if (toDelete == null) {
            throw new CarNotFoundException("This Car doesn't exist in portal.");
        }
        User userLogged = userRepository.getUserByLogin(SecurityUtils.getCurrentUserLogin());
        carService.checkIfIsAuthorized(userLogged, toDelete);
        carsRepository.delete(toDelete);
        LOGGER.debug("Deleted Car: {}", toDelete);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/cars/{id}")
    @Timed
    @Secured({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    public ResponseEntity<?> getCar(@PathVariable Long id) throws CarNotFoundException, UserUnauthorizedException {
        Car car = carsRepository.findOne(id);
        if (car == null) {
            throw new CarNotFoundException("This Car doesn't exist in portal.");
        }
        User userLogged = userRepository.getUserByLogin(SecurityUtils.getCurrentUserLogin());
        carService.checkIfIsAuthorized(userLogged, car);
        LOGGER.debug("Returned Car: {}", car);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @PutMapping("/cars")
    @Timed
    @Secured({AuthoritiesConstants.ADMIN, AuthoritiesConstants.USER})
    public ResponseEntity<?> updateCar(@RequestBody CarDTO carDTO) throws
        CarNotFoundException, UserUnauthorizedException {
        Car car = carsRepository.findOne(carDTO.getId());
        if (car == null) {
            throw new CarNotFoundException("This Car doesn't exist in portal.");
        }
        User userLogged = userRepository.getUserByLogin(SecurityUtils.getCurrentUserLogin());
        carService.checkIfIsAuthorized(userLogged, car);
        carService.updateCar(carDTO.getId(), carDTO.getName(), carDTO.getDescription(), carDTO.getMarca(),
            carDTO.getModel(), carDTO.getVersiune(), carDTO.getAnFabricatie(), carDTO.getCapacitateCilindrica(),
            carDTO.getCombustibil(), carDTO.getCutieViteza(), carDTO.getTransmisie(), carDTO.getCaroserie(),
            carDTO.getCuloare(), carDTO.getPutere(), carDTO.getNrInmatriculare(), carDTO.getNrKm());
        LOGGER.debug("Returned Car: {}", car);
        return new ResponseEntity<>(car, HttpStatus.OK);
    }
}


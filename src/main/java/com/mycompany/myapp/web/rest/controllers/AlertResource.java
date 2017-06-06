package com.mycompany.myapp.web.rest.controllers;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.User;
import com.mycompany.myapp.domain.auto.*;
import com.mycompany.myapp.repository.CarsRepository;
import com.mycompany.myapp.repository.UserRepository;
import com.mycompany.myapp.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * REST controller for managing the alert process.
 */
@RestController
@RequestMapping("/api")
public class AlertResource {

    private final Logger LOGGER = LoggerFactory.getLogger(AlertResource.class);

    private CarsRepository carsRepository;
    private UserRepository userRepository;

    @Inject
    public AlertResource(CarsRepository carsRepository, UserRepository userRepository) {
        this.carsRepository = carsRepository;
        this.userRepository = userRepository;
    }

    /**
     * GET  /alerts : list all existent alerts.
     *
     * @return the ResponseEntity with status 200 (OK) and all alerts in body,
     * or status 500 (Internal Server Error) if the list couldn't be returned
     */
    @GetMapping("/alerts")
    @Timed
    public ResponseEntity<?> getAllerts() {
        User userLogged = userRepository.getUserByLogin(SecurityUtils.getCurrentUserLogin());
        LOGGER.debug("userLogged={}", userLogged);
        List<Alert> alerts = new ArrayList<>();
        List<Car> carList = carsRepository.listCarsByUser(userLogged.getId());
        LocalDate now = LocalDate.now();
        if (carList.isEmpty()) {
            LOGGER.debug("This user don't have any car yet");
        }
        for (Car car : carList) {
            LOGGER.debug("car car = {}", car);
            Set<AsigurareRCA> rca = car.getRca();
            if (rca != null && !rca.isEmpty()) {
                rca.forEach(asigurareRCA -> {
                    LocalDate expirationDate = asigurareRCA.getNot_after();
                    if (expirationDate.getYear() == now.getYear() &&
                        expirationDate.getMonth() == now.getMonth() &&
                        expirationDate.getDayOfMonth() + 5 > now.getDayOfMonth()) {
                        Alert alertRca = new Alert(asigurareRCA.getName(), asigurareRCA.getDescription(), expirationDate, "RCA");
                        alerts.add(alertRca);
                    }
                });
            }
            Set<ITP> itps = car.getItp();
            if (itps != null && !itps.isEmpty()) {
                itps.forEach(itp -> {
                    LocalDate expirationDate = itp.getNot_after();
                    if (expirationDate.getYear() == now.getYear() &&
                        expirationDate.getMonth() == now.getMonth() &&
                        expirationDate.getDayOfMonth() + 5 > now.getDayOfMonth()) {
                        Alert alertRca = new Alert(itp.getName(), itp.getDescription(), expirationDate, "ITP");
                        alerts.add(alertRca);
                    }
                });
            }
            Set<Casco> cascoSet = car.getCasco();
            if (cascoSet != null && !cascoSet.isEmpty()) {
                cascoSet.forEach(casco -> {
                    LocalDate expirationDate = casco.getNot_after();
                    if (expirationDate.getYear() == now.getYear() &&
                        expirationDate.getMonth() == now.getMonth() &&
                        expirationDate.getDayOfMonth() + 5 > now.getDayOfMonth()) {
                        Alert alertRca = new Alert(casco.getName(), casco.getDescription(), expirationDate, "Casco");
                        alerts.add(alertRca);
                    }
                });
            }
        }
        return new ResponseEntity<>(alerts, HttpStatus.OK);
    }
}

package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.auto.Car;
import com.mycompany.myapp.domain.auto.RadiereAuto;
import com.mycompany.myapp.repository.RadiereRepository;
import com.mycompany.myapp.service.util.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

/**
 * RadiereService
 */
@Service
@Transactional
public class RadiereService {

    private final Logger LOGGER = LoggerFactory.getLogger(RadiereService.class);

    private RadiereRepository radiereRepository;

    @Autowired
    public RadiereService(RadiereRepository radiereRepository) {
        this.radiereRepository = radiereRepository;
    }

    public RadiereAuto radiazaVehicul(String name, String description, LocalDate data,
                                      String motivul, Car car) {
        RadiereAuto radiereAuto = new RadiereAuto();
        radiereAuto.setName(name);
        radiereAuto.setDescription(description);
        radiereAuto.setNr_inregistrare(RandomUtil.generateNrInregistrare());
        radiereAuto.setCar(car);
        LocalDate date = LocalDate.now();
        radiereAuto.setData(data == null ? date : data);
        radiereAuto.setMotivul(motivul);
        radiereRepository.save(radiereAuto);
        LOGGER.debug("Radiere created successfully: {}", radiereAuto);
        return radiereAuto;
    }

    public void updateRadiere(Long id, String name, String description,
                              LocalDate data, String motivul, Car car) {
        Optional.of(radiereRepository
            .findOne(id))
            .ifPresent(consumer -> {
                consumer.setName(name);
                consumer.setDescription(description);
                consumer.setData(data);
                consumer.setMotivul(motivul);
                consumer.setCar(car);
                LOGGER.debug("Changed Information for Radiere Auto: {}", consumer);
            });
    }
}

package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.auto.AsigurareRCA;
import com.mycompany.myapp.domain.auto.Car;
import com.mycompany.myapp.repository.RcaRepository;
import com.mycompany.myapp.service.util.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

/**
 * Asigurare RCA service class
 */
@Service
@Transactional
public class RcaService {

    private final Logger LOGGER = LoggerFactory.getLogger(RcaService.class);

    private RcaRepository rcaRepository;

    @Autowired
    public RcaService(RcaRepository rcaRepository) {
        this.rcaRepository = rcaRepository;
    }

    public AsigurareRCA createRca(String name, String description, LocalDate not_after,
                                  LocalDate not_before, Car car) {
        AsigurareRCA rca = new AsigurareRCA();
        rca.setName(name);
        rca.setDescription(description);
        rca.setNr_inregistrare(RandomUtil.generateNrInregistrare());
        rca.setCar(car);
        LocalDate date = LocalDate.now();
        rca.setNot_after(not_after == null ? date : not_after);
        rca.setNot_before(not_before == null ? LocalDate.of(date.getYear() + 1, date.getMonth(), date.getDayOfMonth()) : not_before);
        rcaRepository.save(rca);
        LOGGER.debug("Rca created successfully: {}", rca);
        return rca;
    }

    public void updateRca(Long id, String name, String description,
                          LocalDate not_after, LocalDate not_before, Car car) {
        Optional.of(rcaRepository
            .findOne(id))
            .ifPresent(rca -> {
                rca.setName(name);
                rca.setDescription(description);
                rca.setCar(car);
                rca.setNot_after(not_after);
                rca.setNot_before(not_before);
                //rca.setNr_inregistrare(RandomUtil.generateNrInregistrare());
                LOGGER.debug("Changed Information for Asigurare RCA: {}", rca);
            });
    }
}

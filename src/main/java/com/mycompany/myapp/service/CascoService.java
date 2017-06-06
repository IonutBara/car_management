package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.auto.Car;
import com.mycompany.myapp.domain.auto.Casco;
import com.mycompany.myapp.repository.CascoRepository;
import com.mycompany.myapp.service.util.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.Optional;

/**
 * CascoService
 */
@Service
@Transactional
public class CascoService {

    private final Logger LOGGER = LoggerFactory.getLogger(CascoService.class);

    private CascoRepository cascoRepository;

    @Inject
    public CascoService(CascoRepository cascoRepository) {
        this.cascoRepository = cascoRepository;
    }

    public Casco createCasco(String name, String description, String observatii,
                             LocalDate not_after, LocalDate not_before, long suma,
                             String moneda, String clauze, String precizari, Car car) {
        Casco casco = new Casco();
        casco.setName(name);
        casco.setDescription(description);
        casco.setObservatii(observatii);
        casco.setNr_inregistrare(RandomUtil.generateNrInregistrare());
        casco.setSumaAsigurata(suma);
        casco.setMoneda(moneda);
        casco.setClauzeSpeciale(clauze);
        casco.setAltePrecizari(precizari);
        casco.setCar(car);
        LocalDate date = LocalDate.now();
        casco.setNot_after(not_after == null ? date : not_after);
        casco.setNot_before(not_before == null ? LocalDate.of(date.getYear() + 1, date.getMonth(), date.getDayOfMonth()) : not_before);
        cascoRepository.save(casco);
        LOGGER.debug("Asigurare Casco created successfully: {}", casco);
        return casco;
    }

    public void updateCasco(Long id, String name, String description, String observatii,
                            LocalDate not_after, LocalDate not_before, long suma,
                            String moneda, String clauze, String precizari, Car car) {
        Optional.of(cascoRepository
            .findOne(id))
            .ifPresent(casco -> {
                casco.setName(name);
                casco.setDescription(description);
                casco.setObservatii(observatii);
                casco.setNot_after(not_after);
                casco.setNot_before(not_before);
                casco.setSumaAsigurata(suma);
                casco.setMoneda(moneda);
                casco.setClauzeSpeciale(clauze);
                casco.setAltePrecizari(precizari);
                casco.setCar(car);
                //casco.setNr_inregistrare(RandomUtil.generateNrInregistrare());
                LOGGER.debug("Changed Information for Asigurare auto Casco : {}", casco);
            });
    }
}

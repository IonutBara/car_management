package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.auto.Car;
import com.mycompany.myapp.domain.auto.ITP;
import com.mycompany.myapp.repository.ItpRepository;
import com.mycompany.myapp.service.util.RandomUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

/**
 * ItpService class
 */
@Service
@Transactional
public class ItpService {
    private final Logger LOGGER = LoggerFactory.getLogger(ItpService.class);

    private ItpRepository itpRepository;

    @Autowired
    public ItpService(ItpRepository itpRepository) {
        this.itpRepository = itpRepository;
    }

    public ITP createItp(String name, String description, String observatii,
                         LocalDate not_after, LocalDate not_before, Car car) {
        ITP itp = new ITP();
        itp.setName(name);
        itp.setDescription(description);
        itp.setObservatii(observatii);
        itp.setNr_inregistrare(RandomUtil.generateNrInregistrare());
        itp.setCar(car);
        LocalDate date = LocalDate.now();
        itp.setNot_after(not_after == null ? date : not_after);
        itp.setNot_before(not_before == null ? LocalDate.of(date.getYear() + 1, date.getMonth(), date.getDayOfMonth()) : not_before);
        itpRepository.save(itp);
        LOGGER.debug("Itp created successfully: {}", itp);
        return itp;
    }

    public void updateItp(Long id, String name, String description, String observatii,
                          LocalDate not_after, LocalDate not_before, Car car) {
        Optional.of(itpRepository
            .findOne(id))
            .ifPresent(itpConsumer -> {
                itpConsumer.setName(name);
                itpConsumer.setDescription(description);
                itpConsumer.setObservatii(observatii);
                itpConsumer.setCar(car);
                itpConsumer.setNot_after(not_after);
                itpConsumer.setNot_before(not_before);
                //itpConsumer.setNr_inregistrare(RandomUtil.generateNrInregistrare());
                LOGGER.debug("Changed Information for Itp auto : {}", itpConsumer);
            });
    }
}

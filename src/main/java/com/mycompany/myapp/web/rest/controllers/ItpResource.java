package com.mycompany.myapp.web.rest.controllers;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.auto.ITP;
import com.mycompany.myapp.exceptions.ItpNotFoundException;
import com.mycompany.myapp.repository.ItpRepository;
import com.mycompany.myapp.security.AuthoritiesConstants;
import com.mycompany.myapp.service.ItpService;
import com.mycompany.myapp.service.dto.ItpDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * ItpResource
 */
@RestController
@RequestMapping("/api")
public class ItpResource {

    private final Logger LOGGER = LoggerFactory.getLogger(ItpResource.class);

    private ItpRepository itpRepository;
    private ItpService itpService;

    @Inject
    public ItpResource(ItpRepository itpRepository) {
        this.itpRepository = itpRepository;
    }

    @Inject
    public void setItpService(ItpService itpService) {
        this.itpService = itpService;
    }

    @PostMapping("/itp")
    @Timed
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<?> createItpAuto(@RequestBody ItpDTO itpDTO) {
        ITP rca = itpService.createItp(itpDTO.getName(), itpDTO.getDescription(),
            itpDTO.getObservatii(), itpDTO.getNot_after(),
            itpDTO.getNot_before(), itpDTO.getCar());
        return new ResponseEntity<>(rca, HttpStatus.OK);
    }

    @GetMapping("/itp")
    @Timed
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<?> listAll() {
        List<ITP> list = itpRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/itp/{id}")
    @Timed
    @Secured({AuthoritiesConstants.ADMIN})
    public ResponseEntity<?> getItpInformations(@PathVariable Long id) throws ItpNotFoundException {
        ITP itp = itpRepository.findOne(id);
        if (itp == null) {
            throw new ItpNotFoundException("This Itp auto doesn't exist in portal.");
        }
        LOGGER.debug("Returned ITP Auto : {}", itp);
        return new ResponseEntity<>(itp, HttpStatus.OK);
    }

    @PutMapping("/itp")
    @Timed
    @Secured({AuthoritiesConstants.ADMIN})
    public ResponseEntity<?> updateItpAuto(@RequestBody ItpDTO rcaDTO) {
        itpService.updateItp(rcaDTO.getId(), rcaDTO.getName(), rcaDTO.getDescription(),
            rcaDTO.getObservatii(), rcaDTO.getNot_after(), rcaDTO.getNot_before(),
            rcaDTO.getCar());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}



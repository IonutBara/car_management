package com.mycompany.myapp.web.rest.controllers;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.auto.AsigurareRCA;
import com.mycompany.myapp.exceptions.RcaNotFoundException;
import com.mycompany.myapp.repository.CarsRepository;
import com.mycompany.myapp.repository.RcaRepository;
import com.mycompany.myapp.security.AuthoritiesConstants;
import com.mycompany.myapp.service.RcaService;
import com.mycompany.myapp.service.dto.RcaDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * REST controller for managing the RCA Apis
 */
@RestController
@RequestMapping("/api")
public class RcaResource {

    private final Logger LOGGER = LoggerFactory.getLogger(CarResource.class);

    private RcaRepository rcaRepository;
    private RcaService rcaService;

    @Inject
    public RcaResource(RcaRepository rcaRepository) {
        this.rcaRepository = rcaRepository;
    }

    @Inject
    public void setRcaService(RcaService rcaService) {
        this.rcaService = rcaService;
    }

    @PostMapping("/rca")
    @Timed
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<?> createRca(@RequestBody RcaDTO rcaDTO) {
        AsigurareRCA rca = rcaService.createRca(rcaDTO.getName(), rcaDTO.getDescription(),
            rcaDTO.getNot_after(), rcaDTO.getNot_before(), rcaDTO.getCar());
        return new ResponseEntity<>(rca, HttpStatus.OK);
    }

    @GetMapping("/rca")
    @Timed
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<?> listAll() {
        List<AsigurareRCA> rcaList = rcaRepository.findAll();
        return new ResponseEntity<>(rcaList, HttpStatus.OK);
    }

    @GetMapping("/rca/{id}")
    @Timed
    @Secured({AuthoritiesConstants.ADMIN})
    public ResponseEntity<?> getRcaInformations(@PathVariable Long id) throws RcaNotFoundException {
        AsigurareRCA rca = rcaRepository.findOne(id);
        if (rca == null) {
            throw new RcaNotFoundException("This Asigurare RCA doesn't exist in portal.");
        }
        LOGGER.debug("Returned Asigurare Rca : {}", rca);
        return new ResponseEntity<>(rca, HttpStatus.OK);
    }

    @PutMapping("/rca")
    @Timed
    @Secured({AuthoritiesConstants.ADMIN})
    public ResponseEntity<?> updateRca(@RequestBody RcaDTO rcaDTO) {
        rcaService.updateRca(rcaDTO.getId(), rcaDTO.getName(), rcaDTO.getDescription(),
            rcaDTO.getNot_after(), rcaDTO.getNot_before(), rcaDTO.getCar());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}



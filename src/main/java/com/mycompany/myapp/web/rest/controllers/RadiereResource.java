package com.mycompany.myapp.web.rest.controllers;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.auto.RadiereAuto;
import com.mycompany.myapp.exceptions.RadiereNotFoundException;
import com.mycompany.myapp.repository.RadiereRepository;
import com.mycompany.myapp.security.AuthoritiesConstants;
import com.mycompany.myapp.service.RadiereService;
import com.mycompany.myapp.service.dto.RadiereDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * managing Radiere
 */
@RestController
@RequestMapping("/api")
public class RadiereResource {

    private final Logger LOGGER = LoggerFactory.getLogger(RadiereResource.class);

    private RadiereRepository radiereRepository;
    private RadiereService radiereService;

    @Inject
    public RadiereResource(RadiereRepository radiereRepository) {
        this.radiereRepository = radiereRepository;
    }

    @Inject
    public void setRadiereService(RadiereService radiereService) {
        this.radiereService = radiereService;
    }

    @PostMapping("/radiere")
    @Timed
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<?> createRadiere(@RequestBody RadiereDTO dto) {
        RadiereAuto rad = radiereService.radiazaVehicul(dto.getName(), dto.getDescription(),
            dto.getData(), dto.getMotivul(), dto.getCar());
        return new ResponseEntity<>(rad, HttpStatus.OK);
    }

    @GetMapping("/radiere")
    @Timed
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<?> listAll() {
        List<RadiereAuto> all = radiereRepository.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/radiere/{id}")
    @Timed
    @Secured({AuthoritiesConstants.ADMIN})
    public ResponseEntity<?> getRadiereInformations(@PathVariable Long id) throws RadiereNotFoundException {
        RadiereAuto one = radiereRepository.findOne(id);
        if (one == null) {
            throw new RadiereNotFoundException("This Radiere doesn't exist in portal.");
        }
        LOGGER.debug("Returned Radiere auto informations : {}", one);
        return new ResponseEntity<>(one, HttpStatus.OK);
    }

    @PutMapping("/radiere")
    @Timed
    @Secured({AuthoritiesConstants.ADMIN})
    public ResponseEntity<?> updateRadiere(@RequestBody RadiereDTO dto) {
        radiereService.updateRadiere(dto.getId(), dto.getName(), dto.getDescription(),
            dto.getData(), dto.getMotivul(), dto.getCar());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}


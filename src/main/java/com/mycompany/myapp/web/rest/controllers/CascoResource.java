package com.mycompany.myapp.web.rest.controllers;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.auto.Casco;
import com.mycompany.myapp.exceptions.CascoNotFoundException;
import com.mycompany.myapp.repository.CascoRepository;
import com.mycompany.myapp.security.AuthoritiesConstants;
import com.mycompany.myapp.service.CascoService;
import com.mycompany.myapp.service.dto.CascoDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

/**
 * CascoResource
 */
@RestController
@RequestMapping("/api")
public class CascoResource {

    private final Logger LOGGER = LoggerFactory.getLogger(CascoResource.class);

    private CascoRepository cascoRepository;
    private CascoService cascoService;

    @Inject
    public CascoResource(CascoRepository cascoRepository) {
        this.cascoRepository = cascoRepository;
    }

    @Inject
    public void setCascoService(CascoService cascoService) {
        this.cascoService = cascoService;
    }

    @PostMapping("/casco")
    @Timed
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<?> createCasco(@RequestBody CascoDTO dto) {
        Casco casco = cascoService.createCasco(dto.getName(), dto.getDescription(),
            dto.getObservatii(), dto.getNot_after(),
            dto.getNot_before(), dto.getSumaAsigurata(),
            dto.getMoneda(), dto.getClauzeSpeciale(),
            dto.getAltePrecizari(), dto.getCar());
        return new ResponseEntity<>(casco, HttpStatus.OK);
    }

    @GetMapping("/casco")
    @Timed
    @Secured(AuthoritiesConstants.ADMIN)
    public ResponseEntity<?> listAll() {
        List<Casco> list = cascoRepository.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/casco/{id}")
    @Timed
    @Secured({AuthoritiesConstants.ADMIN})
    public ResponseEntity<?> getCascoInformations(@PathVariable Long id) throws CascoNotFoundException {
        Casco casco = cascoRepository.findOne(id);
        if (casco == null) {
            throw new CascoNotFoundException("This Casco doesn't exist in portal.");
        }
        CascoDTO response = cascoService.mapResponseCasco(casco);
        LOGGER.debug("Returned Asigurare CASCO Auto : {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/casco")
    @Timed
    @Secured({AuthoritiesConstants.ADMIN})
    public ResponseEntity<?> updateAsigurareCasco(@RequestBody CascoDTO dto) {
        LOGGER.debug("Suma_asigurata()={}", dto.getSumaAsigurata());
        LOGGER.debug("Clauze_speciale()={}", dto.getClauzeSpeciale());
        LOGGER.debug("Alte_precizari()={}", dto.getAltePrecizari());
        cascoService.updateCasco(dto.getId(), dto.getName(), dto.getDescription(),
            dto.getObservatii(), dto.getNot_after(), dto.getNot_before(),
            dto.getSumaAsigurata(), dto.getMoneda(), dto.getClauzeSpeciale(),
            dto.getAltePrecizari(), dto.getCar());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

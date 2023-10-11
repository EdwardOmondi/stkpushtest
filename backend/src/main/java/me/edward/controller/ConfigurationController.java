package me.edward.controller;

import me.edward.dto.ConfigurationDTO;
import me.edward.model.Configuration;
import me.edward.service.ConfigurationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ConfigurationController {
    final ConfigurationService configurationService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ConfigurationController(ConfigurationService configurationService) {
        this.configurationService = configurationService;
    }

    @PostMapping(path = "/config")
    ResponseEntity<Configuration> addConfiguration(@RequestBody ConfigurationDTO configurationDTO) {
        logger.info("addConfiguration:\nconfigurationDTO => " + configurationDTO);
        Configuration response = configurationService.saveConfig(configurationDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(path = "/config")
    ResponseEntity<Configuration> updateConfiguration(@RequestBody ConfigurationDTO configurationDTO) {
        logger.info("updateConfiguration:\nconfigurationDTO => " + configurationDTO);
        Configuration response = configurationService.updateConfig(configurationDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}

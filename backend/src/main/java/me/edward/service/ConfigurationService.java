package me.edward.service;

import me.edward.dto.ConfigurationDTO;
import me.edward.model.Configuration;
import me.edward.repository.ConfigurationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ConfigurationService {
    private final ConfigurationRepository configurationRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ConfigurationService(ConfigurationRepository configurationRepository) {
        this.configurationRepository = configurationRepository;
    }

    public Configuration saveConfig(ConfigurationDTO configurationDTO) {
        Configuration configuration = new Configuration();
        configuration.setName(configurationDTO.name());
        configuration.setValue(configurationDTO.value());
        try {
            Configuration result = configurationRepository.save(configuration);
            logger.info("saveConfig:\nresult => " + result);
            return result;
        } catch (Exception error) {
            logger.info("saveConfig:\nerror.getMessage() => " + error.getMessage());
            error.printStackTrace();
        }
        return null;
    }

    public Configuration getConfig(String configNameString) {
        Optional<Configuration> result = configurationRepository.findByName(configNameString);
        if (result.isPresent()) {
            logger.info("getConfig:\nresult => " + result);
            return result.get();
        }
        return null;
    }

    public Configuration updateConfig(ConfigurationDTO configurationDTO) {
        Configuration configuration = getConfig(configurationDTO.name());
        if (configuration == null) {
            return null;
        }
        logger.info("updateConfig:\nconfiguration => " + configuration);
        configuration.setValue(configurationDTO.value());
        configuration.setEditedDate(new Date());
        try {
            Configuration result = configurationRepository.save(configuration);
            logger.info("updateConfig:\nresult => " + result);
            return result;
        } catch (Exception error) {
            logger.info("updateConfig:\nerror.getMessage() => " + error.getMessage());
            error.printStackTrace();
        }
        return null;
    }
}

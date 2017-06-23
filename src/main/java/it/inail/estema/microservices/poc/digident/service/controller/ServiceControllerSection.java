package it.inail.estema.microservices.poc.digident.service.controller;

import it.inail.estema.microservices.poc.digident.model.Section;
import it.inail.estema.microservices.poc.digident.repositories.SectionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceControllerSection {
    
    private static final Logger log = LoggerFactory.getLogger(ServiceControllerSection.class);

    @Autowired
    private SectionRepository repository;

    @RequestMapping("/section/{type}")
    @ResponseBody
    public String getSection(@PathVariable String type) {
        Section section;
        try {
            log.debug("Recupero della sezione {} .", type );
            section = repository.findSectionByType(type.toUpperCase());
            
            log.debug("Recupero della sezione {} avvenuto con successo. Html restituito:", type , section.getHtml());
            return section.getHtml();
        
        } catch (Exception e) {
            log.error("Errore recupero sezione {} : {}", type ,e.getMessage());
            return "Sezione non trovata";
        }
    }
    
}

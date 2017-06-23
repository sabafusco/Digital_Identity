package it.inail.estema.microservices.poc.digident.service.controller;

import it.inail.estema.microservices.poc.digident.model.Section;
import it.inail.estema.microservices.poc.digident.repositories.SectionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceControllerSection {
    
    private static final Logger log = LoggerFactory.getLogger(ServiceControllerSection.class);
    private static String schedulerSezioneDaRecuperare;
    
    @Autowired
    private SectionRepository repository;

    @RequestMapping("/html")
    @ResponseBody
    public String getSection() {
        Section section;
        try {
            log.info("Recupero della sezione {} .", schedulerSezioneDaRecuperare );
            section = repository.findSectionByType(schedulerSezioneDaRecuperare.toUpperCase());
            
            log.debug("Recupero della sezione {} avvenuto con successo. Html restituito:", schedulerSezioneDaRecuperare , section.getHtml());
            return section.getHtml();
        
        } catch (Exception e) {
            log.error("Errore recupero sezione {} : {}", schedulerSezioneDaRecuperare ,e.getMessage());
            return "Sezione non trovata";
        }
    }
    
    @Value("${scheduler.sezione.da.recuperare}")
    public void setSchedulerUrlRecuperoHeader(String schedulerSezioneDaRecuperare) {
        ServiceControllerSection.schedulerSezioneDaRecuperare = schedulerSezioneDaRecuperare;
    }
    
}

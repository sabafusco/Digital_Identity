
package it.inail.estema.microservices.poc.digident.scheduler;

import it.inail.estema.microservices.poc.digident.model.Section;
import it.inail.estema.microservices.poc.digident.repositories.SectionRepository;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class SectionScheduler {
    
    private static final Logger log = LoggerFactory.getLogger(SectionScheduler.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    private static String schedulerUrlRecuperoSezione;
    private static String schedulerSezioneDaRecuperare;
    
    @Autowired
    private SectionRepository repository;
    
    @Scheduled(fixedRateString ="${scheduler.section.rate}", initialDelay=10000)
    public void recuperaSezioniDaRemoto() {

        log.debug("Recupero html {} da {}", schedulerSezioneDaRecuperare ,schedulerUrlRecuperoSezione);

        RestTemplate restTemp = new RestTemplate();
        
        if(schedulerSezioneDaRecuperare!=null && !"".equals(schedulerSezioneDaRecuperare)){
            String html = restTemp.getForObject(schedulerUrlRecuperoSezione, String.class);
            log.debug("HTML -->{}", html );
            updateToDB(schedulerSezioneDaRecuperare, html); 
        } 
    }
    
    private void updateToDB(String type, String html){
        try{
            Section section = repository.findSectionByType(type);
            
            if( section!=null ){
                section.setHtml(html);
                section.setUpdateDate(dateFormat.format(new Date()));
            }else{
                section = new Section();
                section.setHtml(html);
                section.setType(type);
                section.setUpdateDate(dateFormat.format(new Date()));
            }
            
            repository.save(section);
            log.info("Salvataggio su db riuscito per " + type);
        
        }catch(Exception e){
            log.error("Errore salvataggio su db:" + e.getLocalizedMessage());
        }
    }
    
    
    
    
    @Value("${scheduler.sezione.da.recuperare}")
    public void setSchedulerUrlRecuperoHeader(String schedulerSezioneDaRecuperare) {
        SectionScheduler.schedulerSezioneDaRecuperare = schedulerSezioneDaRecuperare;
    }
    
    @Value("${scheduler.url.recupero.sezione}")
    public void setSchedulerUrlRecuperoSezione(String schedulerUrlRecuperoSezione) {
        SectionScheduler.schedulerUrlRecuperoSezione = schedulerUrlRecuperoSezione;
    }
    
}

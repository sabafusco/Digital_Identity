package it.inail.estema.microservices.poc.digident.repositories;

import it.inail.estema.microservices.poc.digident.model.Section;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SectionRepository extends PagingAndSortingRepository<Section, Long> {

    Section findSectionByType(String type);

}

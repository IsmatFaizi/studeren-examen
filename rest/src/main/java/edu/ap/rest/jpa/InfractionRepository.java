package edu.ap.rest.jpa;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "infraction", path = "infraction")
public interface InfractionRepository extends PagingAndSortingRepository<Infraction, Integer> {
    List<Infraction> findByYear(@Param("year") int year);
}

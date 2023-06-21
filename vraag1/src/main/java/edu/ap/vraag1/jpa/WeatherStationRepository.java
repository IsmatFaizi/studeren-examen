package edu.ap.vraag1.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherStationRepository extends CrudRepository<WeatherStation, Long> {
    WeatherStation findFirstByName(String name);
}

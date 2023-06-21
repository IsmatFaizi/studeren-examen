package edu.ap.dates.jpa;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface DatesRepository extends CrudRepository<Dates, Long> {
}

package edu.ap.vraag1.jpa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends CrudRepository<Users, Long> {
    Users findFirstByUsernameAndPassword(String username, String password);
}

package org.example.springdata.config;

import org.example.springdata.pojo.Person;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

/**
 * Basic example using the CrudRepository. The CrudRepository is extended to expose the save method.
 */
public interface PersonRepository extends CrudRepository<Person, String> {
    /**
     * Find people by first ane last name
     * @param firstName first name
     * @param lastName last name
     * @return list of Person objects that match the first and last name
     */
    List<Person> findByFirstNameAndLastName(String firstName, String lastName);
}

package org.example.springdata;


import org.example.springdata.config.ApplicationConfig;
import org.example.springdata.config.PersonRepository;
import org.example.springdata.pojo.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class HelloWorldTest {
    private static final String FIRST_NAME = "test";
    private static final String LAST_NAME = "user";
    private static final String UUID = "29466edf-888b-45c7-9e97-e6e7cc356282";

    @Autowired
    private PersonRepository personRepository;

    @Test
    public void testSaveAndQuery() {
        Person person = new Person(UUID, FIRST_NAME, LAST_NAME);
        personRepository.save(person);

        List<Person> persons = personRepository.findByFirstNameAndLastName(FIRST_NAME, LAST_NAME);

        assertThat(persons.size(), equalTo(1));

        Person foundPerson = persons.stream().findFirst().get();

        assertThat(foundPerson.getFirstName(), equalTo(FIRST_NAME));
        assertThat(foundPerson.getLastName(), equalTo(LAST_NAME));
    }
}

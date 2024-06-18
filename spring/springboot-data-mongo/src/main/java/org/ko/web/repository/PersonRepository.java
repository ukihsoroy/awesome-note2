package org.ko.web.repository;

import org.ko.web.domain.Person;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.RepositoryDefinition;

//@RepositoryDefinition(domainClass = Person.class, idClass = Integer.class)
public interface PersonRepository extends MongoRepository<Person, Integer> {


    Person findByName (String name);
}

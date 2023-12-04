package net.froihofer.util.jboss.entity;

import common.PersonDTO;

public class PersonTranslator {

    public Person toEntity(PersonDTO person) {
        if (person == null) return null;
        return new Person();
    }

    /** Converts an entity instance to a DTO instance. */
    public PersonDTO toDTO(Person variable) {
        if (variable == null) return null;
        return new PersonDTO();
    }


}

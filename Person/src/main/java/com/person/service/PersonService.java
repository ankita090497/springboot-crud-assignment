package com.person.service;

import com.person.entity.PersonInfo;

public interface PersonService {

    PersonInfo savePerson(PersonInfo personInfo);

    PersonInfo fetchPerson(Long personId);

    void deletePerson(Long personId);

    PersonInfo updatePerson(PersonInfo personInfo, Long personId);
}

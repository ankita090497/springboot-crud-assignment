package com.person.service;

import com.person.entity.PersonInfo;
import com.person.exception.ResourceNotFoundException;
import com.person.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doThrow;

@SpringBootTest
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonServiceImpl personService;

    private PersonInfo personInfo;
    private PersonInfo updatedPersonInfo;

    /* @BeforeEach
     public void setUp() {
         MockitoAnnotations.openMocks(this);
     }*/
    @BeforeEach
    void setUp() {
        personInfo = new PersonInfo();
        personInfo.setPersonid(1L);
        personInfo.setFirstname("John");
        personInfo.setLastname("Doe");
        personInfo.setContact(123456);
        personInfo.setAddress("Adwerd Street");
        personInfo.setCity("New York");

        updatedPersonInfo = new PersonInfo();
        updatedPersonInfo.setPersonid(1L);
        updatedPersonInfo.setFirstname("John");
        updatedPersonInfo.setLastname("Doe");
        updatedPersonInfo.setContact(123456);
        updatedPersonInfo.setAddress("Ingel Street");
        updatedPersonInfo.setCity("Mexico");
    }

   /* @Test
    public void testSavePersonInfo() {
        PersonInfo personInfo = new PersonInfo();
        personInfo.setPersonid(1L);
        personInfo.setFirstname("John");
        personInfo.setLastname("Doe");
        personInfo.setContact(123456);
        personInfo.setAddress("Adwerd Street");
        personInfo.setCity("New York");

        when(personRepository.save(any(PersonInfo.class))).thenReturn(personInfo);

        PersonInfo savedPersonInfo = personService.savePersonInfo(personInfo);

        assertNotNull(savedPersonInfo);
        assertNotNull(savedPersonInfo.getPersonid());
    }*/

    @Test
    public void testSavePerson_Success() {
        // Arrange
        PersonInfo personInfo = new PersonInfo();
        PersonInfo savedPersonInfo = new PersonInfo();
        when(personRepository.save(any(PersonInfo.class))).thenReturn(savedPersonInfo);

        // Act
        PersonInfo result = personService.savePerson(personInfo);

        // Assert
        assertEquals(savedPersonInfo, result);
        verify(personRepository).save(personInfo);
    }

    @Test
    void testSavePerson_Failure() {
        when(personRepository.save(any(PersonInfo.class))).thenThrow(new DataAccessException("...") {});

        DataAccessException exception = assertThrows(DataAccessException.class, () -> {
            personService.savePerson(personInfo);
        });

        assertEquals("...", exception.getMessage());
    }

    @Test
    void testFetchPerson_Success() {
        when(personRepository.findById(1L)).thenReturn(Optional.of(personInfo));

        PersonInfo result = personService.fetchPerson(1L);

        assertEquals(personInfo, result);
    }

    @Test
    void testFetchPerson_Failure() {
        when(personRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            personService.fetchPerson(1L);
        });
    }

    @Test
    void testUpdatePerson_Success() {
        when(personRepository.findById(anyLong())).thenReturn(Optional.of(new PersonInfo()));
        when(personRepository.save(any(PersonInfo.class))).thenReturn(updatedPersonInfo);

        PersonInfo result = personService.updatePerson(updatedPersonInfo, 1L);

        assertNotNull(result);
        assertEquals(updatedPersonInfo.getFirstname(), result.getFirstname());
        assertEquals(updatedPersonInfo.getLastname(), result.getLastname());
        assertEquals(updatedPersonInfo.getContact(), result.getContact());
        assertEquals(updatedPersonInfo.getAddress(), result.getAddress());
        assertEquals(updatedPersonInfo.getCity(), result.getCity());
    }

    @Test
    void testUpdatePerson_Failure() {
        when(personRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            personService.updatePerson(personInfo, 1L);
        });
    }

    @Test
    void testDeletePerson_Success() {
        Long personId = 1L;
        doNothing().when(personRepository).deleteById(personId);

        personService.deletePerson(personId);

        verify(personRepository, times(1)).deleteById(personId);
    }

    @Test
    void testDeletePerson_Failure() {
        doThrow(new ResourceNotFoundException("1")).when(personRepository).deleteById(anyLong());

        assertThrows(ResourceNotFoundException.class, () -> {
            personService.deletePerson(1L);
        });
    }
}

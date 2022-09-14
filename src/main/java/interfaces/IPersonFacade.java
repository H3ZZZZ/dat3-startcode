package interfaces;

import dtos.PersonDTO;

import java.util.List;

public interface IPersonFacade {

    PersonDTO addPerson(PersonDTO personDTO);

    PersonDTO deletePerson(Integer id);

    public PersonDTO getPerson(Integer id);
    public List<PersonDTO> getAllPersons();
    public PersonDTO editPerson(PersonDTO p);
}


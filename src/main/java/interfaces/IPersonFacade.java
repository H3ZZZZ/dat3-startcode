package interfaces;

import dtos.PersonDTO;

import java.util.List;

public interface IPersonFacade {

    public PersonDTO addPerson(PersonDTO personDTO);

    public PersonDTO deletePerson(Long id);

    public PersonDTO getPerson(Long id);

    public List<PersonDTO> getAllPersons();
    public PersonDTO editPerson(PersonDTO p);
}


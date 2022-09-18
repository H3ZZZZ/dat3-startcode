package facades;

import dtos.PersonDTO;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.TypedQuery;

//import errorhandling.PersonNotFoundException;
import entities.Person;
import interfaces.IPersonFacade;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

/**
 *
 * Rename Class to a relevant name Add add relevant facade methods
 */
public class PersonFacade implements IPersonFacade {
    Date date = new Date();
    private static PersonFacade instance;
    private static EntityManagerFactory emf;
    
    //Private Constructor to ensure Singleton
    private PersonFacade() {}
    
    
    /**
     * 
     * @param _emf
     * @return an instance of this facade class.
     */
    public static PersonFacade getPersonFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new PersonFacade();
        }
        return instance;
    }


    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    @Override
    public PersonDTO addPerson(PersonDTO personDTO) {

        Person personEntity = new Person(personDTO.getFname(), personDTO.getLname(), personDTO.getPhone());
        personEntity.setLastedited(date.toString());
        personEntity.setCreated(date.toString());

        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(personEntity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(personEntity);
    }

    @Override
    public PersonDTO deletePerson(Long id) {
        EntityManager em = emf.createEntityManager();
        Person personFromDb = em.find(Person.class, id);

        if( personFromDb == null)
            throw new EntityNotFoundException("No such Person with id" + personFromDb.getId());
        try {
            em.getTransaction().begin();
            em.remove(personFromDb);
            em.getTransaction().commit();
        } finally {
            em.close();
            return null;
        }
    }


    @Override
    public PersonDTO getPerson(Long id) {
        EntityManager em = emf.createEntityManager();
        Person person = em.find(Person.class, id);
        if (person == null)
            return null;
            return new PersonDTO(person);
    }

    @Override
    public List<PersonDTO> getAllPersons() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
        List<Person> persons = query.getResultList();
        return PersonDTO.getDtos(persons);
    }

    @Override
    public PersonDTO editPerson(PersonDTO p) {
        EntityManager em = emf.createEntityManager();
        Person personFromDb = em.find(Person.class, p.getId());

        if( personFromDb == null) {
            throw new EntityNotFoundException("No such Person with id" + p.getId());
        }

        Person personEntity = new Person( p.getId(), p.getFname(), p.getLname(), p.getPhone(), p.getCreated(), p.getLastedited());
        personEntity.setLastedited(date.toString());
        try {
            em.getTransaction().begin();
            em.merge(personEntity);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return new PersonDTO(personEntity);
    }



    //TODO Remove/Change this before use


//    public List<PersonDTO> getAll(){
//        EntityManager em = emf.createEntityManager();
//        TypedQuery<Person> query = em.createQuery("SELECT p FROM Person p", Person.class);
//        List<Person> persons = query.getResultList();
//        return PersonDTO.getDtos(persons);
//    }
//
//    public PersonDTO updatePerson(PersonDTO personDTO){
//        EntityManager em = getEntityManager();
//        Person personFromDb = em.find(Person.class, personDTO.getId());
////        Person personFromDb = em.find(Person.class, id);
//        if( personFromDb == null) {
//            throw new EntityNotFoundException("No such Person with id" + personDTO.getId());
////            throw new EntityNotFoundException("No such Person with id" + id);
//        }
//        Person personEntity = new Person(personDTO.getId(), personDTO.getName(), personDTO.getAge());
////        Person personEntity = new Person(id, personDTO.getName(), personDTO.getAge());
//        try {
//            em.getTransaction().begin();
//            em.merge(personEntity);
//            em.getTransaction().commit();
//        } finally {
//            em.close();
//        }
//        return new PersonDTO(personEntity);
//    }
//
//    public static void main(String[] args) {
//        emf = EMF_Creator.createEntityManagerFactory();
//        PersonFacade fe = getPersonFacade(emf);
//        //        fe.getAll().forEach(dto->System.out.println(dto));
////        fe.updatePerson(new PersonDTO("Kristian Hartmann", 29), 2);
//        fe.updatePerson(new PersonDTO(2,"Kristian Hartmann", 29));
//    }

}

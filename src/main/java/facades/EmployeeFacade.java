package facades;

import dtos.EmployeeDTO;
import dtos.PersonDTO;
import entities.Employee;
import entities.Person;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeeFacade {
    private static EmployeeFacade instance;
    private static EntityManagerFactory emf;

    private EmployeeFacade() {

    }

    public static EmployeeFacade getEmployeeFacade(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeFacade();
        }
        return instance;
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employeeEntity = new Employee(employeeDTO.getName(), employeeDTO.getAddress(), employeeDTO.getSalary());
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(employeeEntity);
            em.getTransaction().commit();
        } finally {
            em.close();
            return new EmployeeDTO(employeeEntity);
        }
    }

    public List<EmployeeDTO> getAll(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e", Employee.class);
        List<Employee> employees = query.getResultList();
        return EmployeeDTO.getDtos(employees);
    }

    public EmployeeDTO getById(long id) throws Exception { //throws PersonNotFoundException {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e where e.id = :id", Employee.class);
        query.setParameter("id", id);
        Employee employee = query.getSingleResult();
        if (employee == null)
            throw new Exception("The Person entity with ID: "+id+" Was not found");
        return new EmployeeDTO(employee);
    }

    public EmployeeDTO getHighestSalary() throws Exception {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Employee> query = em.createQuery("Select e FROM Employee e order by e.salary desc", Employee.class);
        query.setMaxResults(1);
        Employee employee = query.getSingleResult();
        if (employee == null)
            throw new Exception("No employees found");
        return new EmployeeDTO(employee);
    }

    public List<EmployeeDTO> getAllEmployeesByName(String name){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Employee> query = em.createQuery("SELECT e FROM Employee e where e.name = :name", Employee.class);
        query.setParameter("name", name);
        List<Employee> employees = query.getResultList();
        return EmployeeDTO.getDtos(employees);
    }

    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        EmployeeFacade ef = getEmployeeFacade(emf);




    }

}

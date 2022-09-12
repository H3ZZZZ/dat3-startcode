package facades;

import entities.Employee;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

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

    public Employee createEmployee(String name, String address, float salary) {
        EntityManager em = emf.createEntityManager();

        Employee employee = new Employee(name, address, salary);
        em.getTransaction().begin();
            em.persist(employee);
        em.getTransaction().commit();
        em.close();
        return employee;
    }

    public static void main(String[] args) {
        emf = EMF_Creator.createEntityManagerFactory();
        EmployeeFacade ef = getEmployeeFacade(emf);




    }

}

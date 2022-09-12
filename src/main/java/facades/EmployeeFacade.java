package facades;

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



}

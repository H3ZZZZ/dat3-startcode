/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facades;

import javax.persistence.EntityManagerFactory;
import utils.EMF_Creator;

/**
 *
 * @author tha
 */
public class Populator {
    public static void populate(){
        EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
//        EntityManagerFactory emf_test = EMF_Creator.createEntityManagerFactoryForTest(); //  test



//        PersonFacade fe = PersonFacade.getPersonFacade(emf);
//        fe.create(new PersonDTO("Frederik", 26));
//        fe.create(new PersonDTO("Hartmann", 25));
//        fe.create(new PersonDTO("Lugi", 26));
//        fe.create(new PersonDTO("Denis", 40));
        emf.close();

    }
    
    public static void main(String[] args) {
        populate();
    }
}

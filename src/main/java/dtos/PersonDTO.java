/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import entities.Person;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author tha
 */
public class PersonDTO {

private Integer id;
private String fname;
private String lname;
private String phone;
private Date created;
private Date lastedited;


    public static List<PersonDTO> getDtos(List<Person> persons) {
        List<PersonDTO> persondtos = new ArrayList();
        persons.forEach(person -> persondtos.add(new PersonDTO(person)));
        return persondtos;
    }

    public PersonDTO() {
    }

    public PersonDTO(Person person) {
        if(person.getId() != null)

        this.id = id;
        this.fname = person.getFname();
        this.lname = person.getLname();
        this.phone = person.getPhone();
        this.created = person.getCreated();
        this.lastedited = person.getLastedited();
    }
    public PersonDTO(String fn,String ln, String phone) {
        this.fname = fn;
        this.lname = ln;
        this.phone = phone;
    }


    public long getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastedited() {
        return lastedited;
    }

    public void setLastedited(Date lastedited) {
        this.lastedited = lastedited;
    }
}


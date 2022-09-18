package entities;

import org.eclipse.persistence.jpa.jpql.parser.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table (name = "Person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 45)
    @NotNull
    @Column(name = "fname", nullable = false, length = 45)
    private String fname;

    @Size(max = 45)
    @NotNull
    @Column(name = "lname", nullable = false, length = 45)
    private String lname;

    @Size(max = 45)
    @NotNull
    @Column(name = "phone", nullable = false, length = 45)
    private String phone;

    @NotNull
    @Column(name = "created", nullable = false)
    private String created;

    @NotNull
    @Column(name = "lastedited", nullable = false)
    private String lastedited;

    public Person() {
    }

    public Person(String fName, String lName, String phone) {
        this.fname = fName;
        this.lname = lName;
        this.phone = phone;
    }

    public Person(Long id, String fname, String lname, String phone) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.phone = phone;
    }


    public Person(Long id, String fname, String lname, String phone, String created, String lastedited) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.phone = phone;
        this.created = created;
        this.lastedited = lastedited;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getLastedited() {
        return lastedited;
    }

    public void setLastedited(String lastedited) {
        this.lastedited = lastedited;
    }

}
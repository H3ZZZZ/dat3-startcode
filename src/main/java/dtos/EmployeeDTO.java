package dtos;

import entities.Employee;
import entities.Person;

import java.util.ArrayList;
import java.util.List;

public class EmployeeDTO {

    private long id;
    private String name;
    private String address;
    private float salary;

    public EmployeeDTO(String name, String address, float salary) {
        this.name = name;
        this.address = address;
        this.salary = salary;
    }

    public EmployeeDTO(Employee employee) {
        if (employee.getId() != null)
            this.id = employee.getId();
        this.name = employee.getName();
        this.address = employee.getAddress();
        this.salary = employee.getSalary();
    }

    public static List<EmployeeDTO> getDtos(List<Employee> employees) {
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        employees.forEach(employee -> employeeDTOS.add(new EmployeeDTO(employee)));
        return employeeDTOS;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public float getSalary() {
        return salary;
    }

    public void setSalary(float salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", salary=" + salary +
                '}';
    }
}
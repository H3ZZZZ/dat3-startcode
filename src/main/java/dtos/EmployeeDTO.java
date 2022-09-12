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
    }

    public static List<EmployeeDTO> getDtos(List<Employee> employees) {
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        employees.forEach(employee -> employeeDTOS.add(new EmployeeDTO(employee)));
        return employeeDTOS;
    }
}
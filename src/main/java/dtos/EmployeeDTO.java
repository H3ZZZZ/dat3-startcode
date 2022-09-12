package dtos;

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
}
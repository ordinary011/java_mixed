package copy_objects.copy_constructor;

import java.util.Date;

public class EmployeeDemo {
    public static void main(String[] args) {

    }
}

class Employee {
    private int id;
    private String name;
    private Date startDate;

    public Employee(int id, String name, Date startDate){
        this.id = id;
        this.name = name;
        this.startDate = startDate;
    }

    public Employee(Employee employee) {
        this.id = employee.id;
        this.name = employee.name;
        this.startDate = new Date(employee.startDate.getTime());
    }

}



package employee.salary;


public class PrintBean {
    private String CNIC;
    private String name;
    private String salary;

    public PrintBean(String CNIC, String name, String salary) {
        this.CNIC = CNIC;
        this.name = name;
        this.salary = salary;
    }

    
    public String getCNIC() {
        return CNIC;
    }

    public void setCNIC(String CNIC) {
        this.CNIC = CNIC;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }
    
    
}

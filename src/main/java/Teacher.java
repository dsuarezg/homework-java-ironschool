import java.util.UUID;

public class Teacher {

    private String teacherId;
    private String name;
    private Double salary;


    public Teacher(String name, Double salary) {
        setTeacherId();
        setName(name);
        setSalary(salary);
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId() {
        this.teacherId = UUID.randomUUID().toString().substring(0, 4);;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId='" + teacherId + '\'' +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}

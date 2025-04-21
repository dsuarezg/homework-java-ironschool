
import java.util.ArrayList;
import java.util.UUID;

public class Course {

    private String courseId;
    private String name;
    private double price;
    private Teacher teacher;
    private double money_earned;
    private ArrayList<Student> students;

    public Course(String name, Double price) {
        setCourseId();
        setName(name);
        setPrice(price);
        setTeacher(null);
        //setMoneyEarned(0.0);
        this.money_earned = 0.0;
        setStudents(new ArrayList<Student>());
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId() {
        this.courseId = UUID.randomUUID().toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void setCourseId(String courseId) {this.courseId = courseId;}

    public Double getMoneyEarned() {return money_earned;}

    public void setMoneyEarned(Double money_earned) {this.money_earned = money_earned;}

    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> students) {
        this.students = students;
    }

    public void enrollStudent(Student student) {
        this.students.add(student);
        student.setCourse(this);
        this.money_earned += this.price;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", teacher=" + teacher +
                ", money_earned=" + money_earned +
                ", students=" + students +
                '}';
    }
}

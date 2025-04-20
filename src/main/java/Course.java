import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Course {

    private String courseId;
    private String name;
    private Double price;
    private Teacher teacher;
    private Double moneyEarned;
    // Students object and not Student ID for better readability in JSON
    private List<Student> students;

    public Course(String name, Double price) {
        setCourseId();
        setName(name);
        setPrice(price);
        setTeacher(null);
        setMoneyEarned(0.0);
        setStudents(students);
    }

    public String getCourseId() {
        return courseId;
    }

    // Auxiliary method to set a random course ID, same as in the Teacher and Student classes
    // can be moved to a utility class
    public void setCourseId() {
        this.courseId = UUID.randomUUID().toString().substring(0, 4);
    }

    // NAME
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // PRICE
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    // TEACHER
    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void assignTeacher(Teacher teacher) {
        setTeacher(teacher);
    }

    // STUDENTS
    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = new ArrayList<>();
    }

    public void enrollStudent(Student student) {

        if (student == null) {
            System.out.println("Must provide a valid student to enroll.");
        }

        students.add(student);
        increasedEarned(getPrice());
    }

    // MONEY
    public Double getMoneyEarned() {
        return moneyEarned;
    }

    public void setMoneyEarned(Double moneyEarned) {
        this.moneyEarned = moneyEarned;
    }

    public void increasedEarned(Double amount) {
        setMoneyEarned(getMoneyEarned() + amount);
    }

    @Override
    public String toString() {
        return "Course{" +
                "\n\tcourseId='" + courseId + '\'' +
                ",\n\tname='" + name + '\'' +
                ",\n\tprice=" + price +
                ",\n\tteacher=" + teacher +
                ",\n\tmoneyEarned=" + moneyEarned +
                ",\n\tstudents=" + students +
                "\n}";
    }

}
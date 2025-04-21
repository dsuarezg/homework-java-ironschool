import java.util.UUID;

public class Student {

    private String studentId;
    private String name;
    private String address;
    private String email;
    private Course course;

    public Student(String name, String address, String email) {
        setStudentId();
        setName(name);
        setAddress(address);
        setEmail(email);
        setCourse(null);
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId() {
        this.studentId = UUID.randomUUID().toString().substring(0, 4);
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", course=" + course +
                '}';
    }
}

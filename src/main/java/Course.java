import java.util.UUID;

public class Course {

    private String courseId;
    private String name;
    private Double price;
    private Teacher teacher;

    public Course(String name, Double price) {
        setCourseId();
        setName(name);
        setPrice(price);
        setTeacher(null);
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

}

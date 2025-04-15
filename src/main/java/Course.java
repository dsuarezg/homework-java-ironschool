import java.util.UUID;

public class Course {

    private String courseId;
    private String name;
    private Double price;
    private Teacher teacher;
    private int money_earned;

    public Course(String name, Double price) {
        setCourseId();
        setName(name);
        setPrice(price);
        setTeacher(null);
        setMoneyEarned(0);
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

    public int getMoneyEarned() {return money_earned;}

    public void setMoneyEarned(int money_earned) {this.money_earned = money_earned;}
}

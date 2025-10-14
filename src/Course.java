import java.util.ArrayList;
import java.util.List;

public class Course {
    private final String name;
    private final Instructor instructor;
    private final List<Student> students = new ArrayList<>();

    public Course(String name, Instructor instructor) {
        this.name = name;
        this.instructor = instructor;
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public String getName() { return name; }

    public Instructor getInstructor() { return instructor; }

    public List<Student> getStudents() {
        return students;
    }
}

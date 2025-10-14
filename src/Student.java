import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student extends Person {
    private final Map<Course, List<Boolean>> attendance = new HashMap<>();
    private final Map<Course, Integer> grades = new HashMap<>();

    public Student(String id, String name, String email) {
        super(id, name, email);
    }

    public void attendCourse(Course course, boolean present) {
        attendance.computeIfAbsent(course, k -> new ArrayList<>()).add(present);
    }

    public void addGrade(Course course, int grade) {
        grades.put(course, grade);
    }

    public double getAttendancePercentage(Course course) {
        List<Boolean> records = attendance.get(course);
        if (records == null || records.isEmpty()) return 0.0;

        long presentCount = records.stream().filter(p -> p).count();
        return 100.0 * presentCount / records.size();
    }

    @Override
    public void printInfo() {
        System.out.println("Student: " + getName() + ", Email: " + getEmail());
    }
}

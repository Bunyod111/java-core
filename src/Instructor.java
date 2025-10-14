public class Instructor extends Person {
    public Instructor(String id, String name, String email) {
        super(id, name, email);
    }

    public void assignGrade(Student student, Course course, int grade) {
        student.addGrade(course, grade);
    }

    public void markAttendance(Student student, Course course, boolean present) {
        student.attendCourse(course, present);
    }

    @Override
    public void printInfo() {
        System.out.println("Instructor: " + getName() + ", Email: " + getEmail());
    }
}

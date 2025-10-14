public class Main {
    public static void main(String[] args) {
        Instructor instructor = new Instructor("I1", "Dr. Smith", "smith@example.com");
        Course javaCourse = new Course("Java Basics", instructor);

        Student alice = new Student("S1", "Alice", "alice@example.com");
        Student bob = new Student("S2", "Bob", "bob@example.com");

        javaCourse.addStudent(alice);
        javaCourse.addStudent(bob);

        instructor.markAttendance(alice, javaCourse, true);
        instructor.markAttendance(bob, javaCourse, false);

        instructor.markAttendance(alice, javaCourse, true);
        instructor.markAttendance(bob, javaCourse, true);

        instructor.assignGrade(alice, javaCourse, 95);
        instructor.assignGrade(bob, javaCourse, 80);

        // Info output
        System.out.println("Course: " + javaCourse.getName());
        System.out.println("Instructor:");
        instructor.printInfo();

        for (Student s : javaCourse.getStudents()) {
            s.printInfo();
            System.out.println("Attendance: " + s.getAttendancePercentage(javaCourse) + "%");
        }
    }
}

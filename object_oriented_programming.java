import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input student's information
        System.out.println("Masukan nama siswa: ");
        String studentName = scanner.nextLine();
        System.out.println("Masukan alamat siswa: ");
        String studentAddress = scanner.nextLine();
        Student student = new Student(studentName, studentAddress);

        // Input courses and grades for the student
        for (int i = 1; i <= 3; i++) {
            System.out.println("Masukkan nama mata pelajaran ke-" + i + ": ");
            String course = scanner.nextLine();
            System.out.println("Masukkan nilai untuk mata pelajaran ke-" + i + ": ");
            int grade = scanner.nextInt();
            scanner.nextLine(); // Consume newline character
            student.addCourseGrade(course, grade);
        }

        // Input teacher's information
        System.out.println("Masukan nama guru: ");
        String teacherName = scanner.nextLine();
        System.out.println("Masukan alamat guru: ");
        String teacherAddress = scanner.nextLine();
        Teacher teacher = new Teacher(teacherName, teacherAddress);

        // Input courses taught by the teacher
        for (int i = 1; i <= 5; i++) {
            System.out.println("Mata pelajaran " + i + " yang diajar oleh guru: ");
            String course = scanner.nextLine();
            teacher.addCourse(course);
        }

        // Display student and teacher information
        System.out.println("Informasi siswa:");
        System.out.println(student);
        student.printGrades();
        System.out.println("Nilai rata-rata siswa: " + student.getAverageGrade());

        System.out.println("\nInformasi guru:");
        System.out.println(teacher);
    }
}

class Person {
    private String name;
    private String address;

    public Person(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String toString() {
        return name + " (" + address + ")";
    }
}

class Student extends Person {
    private int numCourses = 0;
    private String[] courses;
    private int[] grades;
    
    public Student(String name, String address) {
        super(name, address);
        courses = new String[5]; // assuming a student can take up to 5 courses
        grades = new int[5];
    }

    public void addCourseGrade(String course, int grade) {
        courses[numCourses] = course;
        grades[numCourses] = grade;
        numCourses++;
    }

    public void printGrades() {
        System.out.print("Grades: ");
        for (int i = 0; i < numCourses; i++) {
            System.out.print(courses[i] + ": " + grades[i] + " ");
        }
        System.out.println();
    }

    public double getAverageGrade() {
        int sum = 0;
        for (int i = 0; i < numCourses; i++) {
            sum += grades[i];
        }
        return (double) sum / numCourses;
    }

    public String toString() {
        return "Student: " + super.toString();
    }
}

class Teacher extends Person {
    private int numCourses = 0;
    private String[] courses;

    public Teacher(String name, String address) {
        super(name, address);
        courses = new String[5]; // assuming a teacher can teach up to 5 courses
    }

    public boolean addCourse(String course) {
        for (int i = 0; i < numCourses; i++) {
            if (courses[i].equals(course)) {
                return false; // course already exists
            }
        }
        courses[numCourses] = course;
        numCourses++;
        return true;
    }

    public boolean removeCourse(String course) {
        for (int i = 0; i < numCourses; i++) {
            if (courses[i].equals(course)) {
                for (int j = i; j < numCourses - 1; j++) {
                    courses[j] = courses[j + 1];
                }
                courses[numCourses - 1] = null;
                numCourses--;
                return true; // course removed successfully
            }
        }
        return false; // course does not exist
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Teacher: ").append(super.toString()).append("\n");
        sb.append("Courses taught:\n");
        for (int i = 0; i < numCourses; i++) {
            sb.append(i + 1).append(". ").append(courses[i]).append("\n");
        }
        return sb.toString();
    }
}

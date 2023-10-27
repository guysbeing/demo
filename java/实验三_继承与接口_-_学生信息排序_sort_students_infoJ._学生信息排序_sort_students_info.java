import java.util.*;

class Student {
    String studentId;
    String name;
    String gender;
    double chineseGrade;
    double mathGrade;
    double englishGrade;
    double avg;
    double totalGrades;

    public Student(String studentId, String name, String gender, double chineseGrade, double mathGrade, double englishGrade) {
        this.studentId = studentId;
        this.name = name;
        this.gender = gender;
        this.chineseGrade = chineseGrade;
        this.mathGrade = mathGrade;
        this.englishGrade = englishGrade;
        this.avg = (chineseGrade + mathGrade + englishGrade) / 3;
        this.totalGrades = chineseGrade + mathGrade + englishGrade;
    }
}

public class Main {
    public static void main(String[] args) {
        List<Student> students = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();
            String[] parts = command.split(" ");

            if (parts[0].equals("INSERT")) {
                String studentId = parts[1];
                String name = parts[2];
                String gender = parts[3];
                double chineseGrade = Double.parseDouble(parts[4]);
                double mathGrade = Double.parseDouble(parts[5]);
                double englishGrade = Double.parseDouble(parts[6]);

                Student student = new Student(studentId, name, gender, chineseGrade, mathGrade, englishGrade);
                students.add(student);
                displayInfo(student);
            } else if (parts[0].equals("LIST")) {
                Collections.sort(students, new Comparator<Student>() {
                    @Override
                    public int compare(Student o1, Student o2) {
                        return Integer.parseInt(o1.studentId)-Integer.parseInt((o2.studentId)) ;
                    }
                });

                for (Student student : students) {
                    displayInfo(student);
                }
            } else if (parts[0].equals("QUIT")) {
                System.out.println("Good bye!");
                break;
            }
        }
    }

    private static void displayInfo(Student student) {
        System.out.printf("%s %s %s %.2f %.2f %.2f %.2f %.2f%n",
                student.studentId, student.name, student.gender,
                student.chineseGrade, student.mathGrade, student.englishGrade,
                student.avg, student.totalGrades);
    }
}

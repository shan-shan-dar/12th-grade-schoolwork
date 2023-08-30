package school;
import java.util.Scanner;

public class School implements IntSchool
{
    String name;
    String boardAffiliation;
    
    int maxClasses = 3;
    int maxTeachers = 5;
    int maxStudents = 20;    
        
    Class[] classes = new Class[maxClasses];
    Teacher[] teachers = new Teacher[maxTeachers];
    Student[] students = new Student[maxStudents];
    
    int classCount = 0;
    int teacherCount = 0;
    int studentCount = 0;
    
    public School(String name)
    {
        this.name = name;
    }
    
    public void initialize()
    {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Board affiliation: ");
        this.boardAffiliation = sc.nextLine();
        
        System.out.println("\nSchool capacity");
        
        System.out.print("Maximum classes: ");
        this.maxClasses = sc.nextInt();
        System.out.print("Maximum teachers: ");
        this.maxTeachers = sc.nextInt();
        System.out.print("Maximum students: ");
        this.maxStudents = sc.nextInt();
    }
    
    public boolean addClass(int n)
    {
        if (classCount + 1 > maxClasses)
        {
            return false;
        }
        classCount++;
        classes[classCount] = new Class(n);
        return true;
    }
    
    public boolean addTeacher(String name)
    {
        if (teacherCount + 1 > maxTeachers)
        {
            return false;
        }
        teacherCount++;
        teachers[teacherCount] = new Teacher(name,teacherCount);
        return true;
    }
    
    public boolean addStudent(String name)
    {
        if (studentCount + 1 > maxStudents)
        {
            return false;
        }
        studentCount++;
        students[studentCount] = new Student(name, studentCount);
        return true;
    }
}

package school;
import java.util.Scanner;

public class Class
{
    Student[] students;
    Teacher classTeacher;
    
    public Class(int n)
    {
        students = new Student[n];
    }
    
    public void enrollBatch(Student[] students)
    {
        Scanner sc = new Scanner(System.in);
        
        for (int i = 0; i < students.length; i++)
        {
            students[i] = students[i];
        }
    }
    
    public void studentAges()
    {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Present date: ");
        int day = sc.nextInt();
        
        System.out.print("Present month: ");
        int month = sc.nextInt();
        
        System.out.print("Present year: ");
        int year = sc.nextInt();
        
        for (int i = 0; i < students.length; i++)
        {
            System.out.println("\n" + students[i].name + "'s age: " + students[i].age(day, month, year));
        }
    }
    
    public void assignClassTeacher(Teacher teacher)
    {
        this.classTeacher = teacher;
    }
}

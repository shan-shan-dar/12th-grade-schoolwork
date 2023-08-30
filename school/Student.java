package school;
import java.util.Scanner;

public class Student extends Person
{
    int roll;
    float marks;
    
    public Student(String name)
    {
        super(name);
    }
    
    public Student(String name, int roll)
    {
        super(name);
        this.roll = roll;
    }
}

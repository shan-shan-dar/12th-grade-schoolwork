package school;
import java.util.Scanner;

public class Teacher extends Person
{   
    int id;
    String subject;
    Class[] classes;
    
    public Teacher(String name)
    {
        super(name);
    }
    
    public Teacher(String name, int id)
    {
        super(name);
        this.id = id;
    }
}

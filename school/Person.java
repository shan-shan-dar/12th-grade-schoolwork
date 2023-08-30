package school;
import java.util.Scanner;

abstract public class Person
{
    String name;
    
    //birthday
    int bday;
    int bmonth;
    int byear;
    
    public Person()
    {
        
    }
    
    public Person(String name)
    {
        this.name = name;
    }
    
    public void setBday()
    {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Date of birth: ");
        bday = sc.nextInt();
        
        System.out.print("Month of birth: ");
        bmonth = sc.nextInt();
        
        System.out.print("Year of birth: ");
        byear = sc.nextInt();
    }
    
    public String age(int day, int month, int year)
    {
        int d;
        int m;
        int y;
        
        if (day<bday)
        {
            day = day + daysInPreviousMonth(month);
            month = month - 1;
        }
        d = day - bday;
        
        if (month<bmonth)
        {
            month = month + 12;
            year = year - 1;
        }
        m = month - bmonth;
        
        y = year - byear;
        
        String age;
        
        if (y < 0)
        {
            age = "Present date is before birthday";
        }
        
        age = (y + " years, " + m + " months and " + d + " days");
        
        return age;
    }
    
    public int daysInPreviousMonth(int month)
    {
        switch(month)
        {
            //dec
            case 1:
                return 31;
            
            //jan
            case 2:
                return 31;
            
            //feb
            case 3:
                return 28;
                
            //mar
            case 4:
                return 31;
                
            //apr
            case 5:
                return 30;
                
            //may
            case 6:
                return 31;
                
            //jun
            case 7:
                return 30;
                
            //jul
            case 8:
                return 31;
                
            //aug
            case 9:
                return 31;
            
            //sep
            case 10:
                return 30;
                
            //oct
            case 11:
                return 31;
                
            //nov
            case 12:
                return 30;
                
            default:
                return 30;
        }
    }
}

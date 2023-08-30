
public class RandoMando
{
    public boolean checkElements(int[] arr, int elem)
    {
        for (int i = 0; i < arr.length; i++)
        {
            if (arr[i] == elem)
            {
                return false;
            }
        }              
        return true;          
    }
    
    public int[] random52 ()
    {
        int[] array = new int[52];
        
        int i = 0;
        while (i < 52)
        {
            int num = (int) (Math.random() * 1000 % 52) + 1;
            System.out.println(num);
            if (checkElements(array, num))
            {
                array[i] = num;
                i++;
            }
        }
        
        return array;
    }
}

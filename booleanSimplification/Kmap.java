
package booleanSimplification;

/*#
 * Kmap accepts a Karnaugh map and returns the number of octets, quartets and duets it can be resolved into.
 */

import java.util.Scanner;
import java.io.*;

public class Kmap
{
    /*#
     * Initiates instance variables for:
     *     Storing the k-map
     *     Storing the number of octets, quartets, duets and singles respectively
     */
    int[][] kmap;
    
    int octets = 0;
    int quartets = 0;
    int duets = 0;
    int singles = 0;

    public Kmap(int[][] kmap)
    {
        this.kmap = new int[kmap.length][kmap[0].length];
        
        for (int i = 0; i < kmap.length; i++)
        {
            for (int j = 0; j < kmap[0].length; j++)
            {
                this.kmap[i][j] = kmap[i][j];
            }
        }
    }
    
    public Kmap()
    {}
    
    /*#
     * Checks if the element at (i,j) in the map is the start of a 4x2 octet (vertical box)
     */
    public boolean octetVert(int i, int j)
    {
        if (kmap.length < 4 || kmap[0].length < 2)
        {
            return false;
        }
        
        boolean one = false;
        
        for (int k = 0; k < 4; k++)
        {
            for (int n = 0; n < 2; n++)
            {
                if (kmap[(i+k)%kmap.length][(j+n)%kmap[0].length] == 1)
                {
                    one = true;
                }
                
                if (kmap[(i+k)%kmap.length][(j+n)%kmap[0].length] == 0)
                {
                    return false;
                }
            }
        }
        
        if (!one)
        {
            return false;
        }

        for (int k = 0; k < 4; k++)
        {
            for (int n = 0; n < 2; n++)
            {
                kmap[(i+k)%kmap.length][(j+n)%kmap[0].length]++;
            }
        }

        return true;
    }

    /*#
     * Checks if the element at (i,j) in the map is the start of a 2x4 octet (horizontal box)
     */
    public boolean octetHori(int i, int j)
    {
        if (kmap.length < 2 || kmap[0].length < 4)
        {
            return false;
        }
        
        boolean one = false;
        
        for (int k = 0; k < 2; k++)
        {
            for (int n = 0; n < 4; n++)
            {
                if (kmap[(i+k)%kmap.length][(j+n)%kmap[0].length] == 1)
                {
                    one = true;
                }
                
                if (kmap[(i+k)%kmap.length][(j+n)%kmap[0].length] == 0)
                {
                    return false;
                }
            }
        }
        
        if (!one)
        {
            return false;
        }

        for (int k = 0; k < 2; k++)
        {
            for (int n = 0; n < 4; n++)
            {
                kmap[(i+k)%kmap.length][(j+n)%kmap[0].length]++;
            }
        }

        return true;
    }

    /*#
     * Checks if the element at (i,j) in the map is the start of a 2x2 quartet (box)
     */
    public boolean quartetBox(int i, int j)
    {
        if (kmap.length < 2 || kmap[0].length < 2)
        {
            return false;
        }
        
        boolean one = false;
        
        for (int k = 0; k < 2; k++)
        {
            for (int n = 0; n < 2; n++)
            {
                if (kmap[(i+k)%kmap[0].length][(j+n)%kmap[0].length] == 1)
                {
                    one = true;
                }
                
                if (kmap[(i+k)%kmap[0].length][(j+n)%kmap[0].length] == 0)
                {
                    return false;
                }
            }
        }
        
        if (!one)
        {
            return false;
        }

        for (int k = 0; k < 2; k++)
        {
            for (int n = 0; n < 2; n++)
            {
                kmap[(i+k)%kmap.length][(j+n)%kmap[0].length]++;
            }
        }

        return true;
    }
    
    /*#
     * Checks if the element at (i,j) in the map is the start of a 4x1 quartet (vertical line)
     */
    public boolean quartetVert(int i, int j)
    {
        if (kmap.length < 2 || kmap[0].length < 2)
        {
            return false;
        }
        
        boolean one = false;
        
        for (int k = 0; k < 4; k++)
        {
            if (kmap[(i+k)%kmap.length][j] == 1)
            {
                one = true;
            }
                
            if (kmap[(i+k)%kmap.length][j] == 0)
            {
                return false;
            }
        }
        
        if (!one)
        {
            return false;
        }

        for (int k = 0; k < 4; k++)
        {
            kmap[(i+k)%kmap.length][j]++;
        }

        return true;
    }
    
    /*#
     * Checks if the element at (i,j) in the map is the start of a 1x4 quartet (horizontal line)
     */
    public boolean quartetHori(int i, int j)
    {
        if (kmap[0].length < 4)
        {
            return false;
        }
        
        boolean one = false;
        
        for (int n = 0; n < 4; n++)
        {
            if (kmap[i][(j+n)%kmap[0].length] == 1)
            {
                one = true;
            }
            
            if (kmap[i][(j+n)%kmap[0].length] == 0)
            {
                return false;
            }
        }
        
        if (!one)
        {
            return false;
        }

        for (int n = 0; n < 4; n++)
        {
            kmap[i][(j+n)%kmap[0].length]++;
        }

        return true;
    }
    
    /*#
     * Checks if the element at (i,j) in the map is the start of a 2x1 duet (vertical line)
     */
    public boolean duetVert(int i, int j)
    {
        if (kmap.length < 2)
        {
            return false;
        }
        
        boolean one = false;
        
        for (int k = 0; k < 2; k++)
        {
            if (kmap[(i+k)%kmap.length][j] == 1)
            {
                one = true;
            }
            
            if (kmap[(i+k)%kmap.length][j] == 0)
            {
                return false;
            }
        }
        
        if (!one)
        {
            return false;
        }

        for (int k = 0; k < 2; k++)
        {
            kmap[(i+k)%kmap.length][j]++;
        }

        return true;
    }
    
    /*#
     * Checks if the element at (i,j) in the map is the start of a 1x2 duet (horizontal line)
     */
    public boolean duetHori(int i, int j)
    {
        if (kmap[0].length < 2)
        {
            return false;
        }
        
        boolean one = false;
        
        for (int n = 0; n < 2; n++)
        {
            if (kmap[i][(j+n)%kmap[0].length] == 1)
            {
                one = true;
            }
            
            if (kmap[i][(j+n)%kmap[0].length] == 0)
            {
                return false;
            }
        }
        
        if (!one)
        {
            return false;
        }

        for (int n = 0; n < 2; n++)
        {
            kmap[i][(j+n)%kmap[0].length]++;
        }

        return true;
    }
    
    /*#
     * Checks if the element at (i,j) in the map is a single ungrouped element
     */
    public boolean single(int i, int j)
    {
        if (kmap[i][j] == 1)
        {
            return true;
        }
        
        return false;
    }
    
    /*#
     * Runs the above functions on all elements and keeps count of octets, quartets and duets.
     * Also generates a general form of the simplified expression for the map
     */
    public String resolve()
    {
        String simplified = "";
        
        for (int i = 0; i < kmap.length; i++)
        {
            for (int j = 0; j < kmap[0].length; j++)
            {
                if (octetVert(i, j))
                {
                    octets++;
                    
                    String temp = "";
                    
                    switch(j)
                    {
                        case 0:
                            temp = "A";
                            break;
                        case 1:
                            temp = "b";
                            break;
                        case 2:
                            temp = "a";
                            break;
                        case 3:
                            temp = "B";
                            break;
                    }
                    
                    simplified+= temp + ' ';
                }
            }
        }
        
        for (int i = 0; i < kmap.length; i++)
        {
            for (int j = 0; j < kmap[0].length; j++)
            {
                if (octetHori(i, j))
                {
                    octets++;
                    
                    String temp = "";
                    
                    switch(i)
                    {
                        case 0:
                            temp = "C";
                            break;
                        case 1:
                            temp = "d";
                            break;
                        case 2:
                            temp = "c";
                            break;
                        case 3:
                            temp = "D";
                            break;
                    }
                    
                    simplified+= temp + ' ';
                }
            }
        }
        
        for (int i = 0; i < kmap.length; i++)
        {
            for (int j = 0; j < kmap[0].length; j++)
            {
                if (quartetVert(i, j))
                {
                    quartets++;
                    
                    String temp = "";
                    
                    switch(j)
                    {
                        case 0:
                            temp = "A/B";
                            break;
                        case 1:
                            temp = "A/b";
                            break;
                        case 2:
                            temp = "a/b";
                            break;
                        case 3:
                            temp = "a/B";
                            break;
                    }
                    
                    simplified+= temp + ' ';
                }
            }
        }
                
        for (int i = 0; i < kmap.length; i++)
        {
            for (int j = 0; j < kmap[0].length; j++)
            {
                
                if (quartetHori(i, j))
                {
                    quartets++;
                    
                    String temp = "";
                    
                    switch(i)
                    {
                        case 0:
                            temp = "C/D";
                            break;
                        case 1:
                            temp = "C/d";
                            break;
                        case 2:
                            temp = "c/d";
                            break;
                        case 3:
                            temp = "c/D";
                            break;
                    }
                    
                    simplified+= temp + ' ';
                }
            }
        }
                
        for (int i = 0; i < kmap.length; i++)
        {
            for (int j = 0; j < kmap[0].length; j++)
            {
                
                if (quartetBox(i, j))
                {
                    quartets++;
                    
                    String temp1 = "";
                    String temp2 = "";
                    
                    switch(i)
                    {
                        case 0:
                            temp1 = "C";
                            break;
                        case 1:
                            temp1 = "d";
                            break;
                        case 2:
                            temp1 = "c";
                            break;
                        case 3:
                            temp1 = "D";
                            break;
                    }
                    
                    switch(j)
                    {
                        case 0:
                            temp2 = "A";
                            break;
                        case 1:
                            temp2 = "b";
                            break;
                        case 2:
                            temp2 = "a";
                            break;
                        case 3:
                            temp2 = "B";
                            break;
                    }
                    
                    simplified+= temp2 + "/" + temp1 + ' ';
                }
            }
        }
        
        for (int i = 0; i < kmap.length; i++)
        {
            for (int j = 0; j < kmap[0].length; j++)
            {
                if (duetVert(i, j))
                {
                    duets++;
                    
                    String temp1 = "";
                    String temp2 = "";
                    
                    switch(i)
                    {
                        case 0:
                            temp1 = "C";
                            break;
                        case 1:
                            temp1 = "d";
                            break;
                        case 2:
                            temp1 = "c";
                            break;
                        case 3:
                            temp1 = "D";
                            break;
                    }
                    
                    switch(j)
                    {
                        case 0:
                            temp2 = "A/B";
                            break;
                        case 1:
                            temp2 = "A/b";
                            break;
                        case 2:
                            temp2 = "a/b";
                            break;
                        case 3:
                            temp2 = "a/B";
                            break;
                    }
                    
                    simplified+= temp2 + "/" + temp1 + ' ';
                }
            }
        }
                
        for (int i = 0; i < kmap.length; i++)
        {
            for (int j = 0; j < kmap[0].length; j++)
            {
                
                if (duetHori(i, j))
                {
                    duets++;
                    
                    String temp1 = "";
                    String temp2 = "";
                    
                    switch(i)
                    {
                        case 0:
                            temp1 = "C/D";
                            break;
                        case 1:
                            temp1 = "C/d";
                            break;
                        case 2:
                            temp1 = "c/d";
                            break;
                        case 3:
                            temp1 = "c/D";
                            break;
                    }
                    
                    switch(j)
                    {
                        case 0:
                            temp2 = "A";
                            break;
                        case 1:
                            temp2 = "b";
                            break;
                        case 2:
                            temp2 = "a";
                            break;
                        case 3:
                            temp2 = "B";
                            break;
                    }
                    
                    simplified+= temp2 + "/" + temp1 + ' ';
                }
            }
        }
        
        for (int i = 0; i < kmap.length; i++)
        {
            for (int j = 0; j < kmap[0].length; j++)
            {
                if (single(i, j))
                {
                    singles++;
                    
                    String temp1 = "";
                    String temp2 = "";
                    
                    switch(i)
                    {
                        case 0:
                            temp1 = "C/D";
                            break;
                        case 1:
                            temp1 = "C/d";
                            break;
                        case 2:
                            temp1 = "c/d";
                            break;
                        case 3:
                            temp1 = "c/D";
                            break;
                    }
                    
                    switch(j)
                    {
                        case 0:
                            temp2 = "A/B";
                            break;
                        case 1:
                            temp2 = "A/b";
                            break;
                        case 2:
                            temp2 = "a/b";
                            break;
                        case 3:
                            temp2 = "a/B";
                            break;
                    }
                    
                    simplified+= temp2 + "/" + temp1 + ' ';
                }
            }
        }
        
        return simplified;
    }

    /*#
     * Takes manual element by element input from the user and updates the kmap accordingly
     */
    public void input()
    {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter rows of k-map");
        int r = sc.nextInt();
        System.out.println("Enter columns of k-map");
        int c = sc.nextInt();

        System.out.println();

        kmap = new int[r][c];

        System.out.println("Enter values of k-map");

        for (int i = 0; i < r; i++)
        {
            for (int j = 0; j < c; j++)
            {
                int value = sc.nextInt();
                kmap[i][j] = value;
            }
        }

        System.out.println();
        print();
    }
    
    /*#
     * Takes file input from the user and updates the kmap accordingly
     */
    public void readFile() throws Exception
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter k-map file name");
        String filename = sc.nextLine();
        String path = "/home/sdarshan/12thJavu/k-maps/" + filename;
        System.out.println();

        try
        {
            File su = new File(path);

            Scanner fread = new Scanner(su);

            int rows = fread.nextInt();
            int cols = fread.nextInt();

            kmap = new int[rows][cols];

            for (int i = 0; i < rows; i++)
            {
                for (int j = 0; j < cols; j++)
                {
                    int value = fread.nextInt();
                    kmap[i][j] = value;
                }
            }

            print();
        }
        catch (Exception e)
        {
            System.out.println("File Error");
        }
    }

    /*
     * Prints the kmap legibly
     */
    public void print()
    {
        for (int i = 0; i < kmap.length; i++)
        {
            for (int j = 0; j < kmap[0].length; j++)
            {
                System.out.print(kmap[i][j] + "\t");
            }
            System.out.println();
        }  
    }
    
    /*#
     * Returns false if given co-ordinates are already in array
     */
    public boolean New(int[] coord, int[][] arr)
    {
        for (int i = 0; i < arr.length; i++)
        {
            if (arr[i] == coord)
            {
                return false;
            }
        }  
        return true;
    }
    
    /*
     * Handles the user interface
     */
    public static void main(String[] args) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        
        int option;

        System.out.println("0. Exit\n1. Solve K-map (Input Manually)\n2. Solve K-map (Load File)\n");
        option = sc.nextInt();
        
        while (option != 0)
        {
            Kmap kmap = new Kmap();
        
            System.out.println();
            if (option == 2)
            {
                kmap.readFile();
            }
            else 
            {
                kmap.input();
            }
            System.out.println();
            kmap.resolve();
            
            System.out.println(kmap.octets + " octets");
            System.out.println(kmap.quartets + " quartets");
            System.out.println(kmap.duets + " duets");
            System.out.println(kmap.singles + " singles");
            
            System.out.println();
            
            System.out.println("0. Exit\n1. Solve K-map (Input)\n2. Solve K-map (Load File)\n");
            option = sc.nextInt();
        }
        
        System.out.println();
        System.out.println(":)");
    }
    
}

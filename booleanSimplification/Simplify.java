
package booleanSimplification;

import java.util.Scanner;
import java.io.*;

public class Simplify
{
    /**
     * Initiates instance variables for:
     *     Storing the canonical form as a 2D matrix
     *     Storing a boolean for if the expression is POS or not (SOP)
     *     Storing the final simplified expression as a string
     */
    char[][] canonical;
    boolean pos = false;
    String simplifiedExpression;
    
    /**
     * Takes manual term by term input from the user and updates the canonical form matrix accordingly
     */
    public void input()
    {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("No.of terms : ");
        int len = sc.nextInt();
        
        canonical = new char[len][4];
        
        System.out.println();
        
        for (int i = 0; i < canonical.length; i++)
        {
            System.out.print("Term " + i + ": ");
            String temp = sc.next();
            
            if (temp.charAt(1) == '+')
            {
                pos = true;
            }
            else 
            {
                pos = false;
            }
            
            for (int j = 0; j < 7; j++)
            {
                if (j%2 == 0)
                {
                    canonical[i][j/2] = temp.charAt(j);
                }
            }
        }
    }
    
    /**
     * Takes file input from the user and updates the canonical form matrix accordingly
     */
    public void readFile() throws Exception
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter canonical expression file name");
        String filename = sc.nextLine();
        String path = "/home/sdarshan/12thJavu/POS/" + filename;
        System.out.println();

        try
        {
            File su = new File(path);

            Scanner fread = new Scanner(su);
            
            int len = fread.nextInt();
            canonical = new char[len][4];

            for (int i = 0; i < canonical.length; i++)
            {
                String temp = fread.next();
                
                if (temp.charAt(1) == '+')
                {
                    pos = true;
                }
                else 
                {
                    pos = false;
                }
            
                for (int j = 0; j < 7; j++)
                {
                    if (j%2 == 0)
                    {
                        canonical[i][j/2] = temp.charAt(j);
                    }
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("File Error");
        }
    }
    
    /**
     * Prints the contents of the canonical form matrix in a legible format
     * Also changes the placeholder '\' to '+' or '.' 
     */
    public void printCanonical()
    {
        System.out.println("Canonical expression:");
        
        for (int i = 0; i < canonical.length; i++)
        {
            for (int j = 0; j < canonical[0].length; j++)
            {
                if (j == 0)
                {
                    System.out.print("(");
                }
                System.out.print(canonical[i][j]);
                if (j != 3)
                {
                    if (pos)
                    {
                        System.out.print("+");
                    }
                    else
                    {
                        System.out.print(".");
                    }
                }
                if (j == 3)
                {
                    if (!pos && i != canonical.length - 1)
                    {
                        System.out.print(")+");
                    }
                    else
                    {
                        System.out.print(")");
                    }
                }
            }
        }
    }
    
    
    /**
     * Generates a kmap matrix from the given canonical form of expression
     */
    public int[][] makeKmap()
    {
        int[][] kmap = new int[4][4];
        
        for (int i = 0; i < 4; i++)
        {
            for (int j = 0; j < 4; j++)
            {
                kmap[i][j] = 1;
            }
        }
        
        for (int i = 0; i < canonical.length; i++)
        {
            int rowIndex = index(canonical[i][2], canonical[i][3]);
            int colIndex = index(canonical[i][0], canonical[i][1]);
            
            kmap[rowIndex][colIndex] = 0;
        } 
        
        System.out.println("Kmap:");
        
        String[] colLabel = {"A.B", "A.b", "a.b", "a.B"};
        String[] rowLabel = {"C.D", "C.d", "c.d", "c.D"};
        
        if (pos)
        {
            for(int i = 0; i < 4; i++)
            {
                colLabel[i] = colLabel[i].replace('.', '+');
                rowLabel[i] = rowLabel[i].replace('.', '+');
            }
        }
        
        System.out.print('\t');
        
        for(int i = 0; i < 4; i++)
        {
            System.out.print(colLabel[i] + "\t");
        }
        
        System.out.println();
        
        for (int i = 0; i < kmap.length; i++)
        {   
            System.out.print(rowLabel[i] + "\t");
            for (int j = 0; j < kmap[0].length; j++)
            {
                System.out.print(kmap[i][j] + "\t");
            }
            System.out.println();
        }
        
        if (pos)
        {
            for (int i = 0; i < kmap.length; i++)
            {
                for (int j = 0; j < kmap[0].length; j++)
                {
                    if (kmap[i][j] == 1)
                    {
                        kmap[i][j] = 0;
                    }
                    else
                    {
                        kmap[i][j] = 1;
                    }
                }
            }
        }
        
        return kmap;
    }
    
    /**
     * Resolves the kmap using Kmap class
     */
    public void resolveKmap(Kmap kmap)
    {
        String simplified = kmap.resolve();
        
        if(pos)
        {
            simplified = simplified.replace('/', '+');
        }
        else
        {
            simplified = simplified.replace('/', '.');
        }
        
        simplified = simplified.trim();
        
        if(pos)
        {
            simplified = simplified.replace(" ", ").(");
        }
        else
        {
            simplified = simplified.replace(" ", ")+(");
        }
        
        simplified = "(" + simplified + ")";
        
        simplifiedExpression = simplified;
    }
    
    /**
     * Returns the correspoding kmap index, given a pair of variable states
     * eg. AB -> 0 since AB is the 0th column of the kmap
     *     Cd -> 1 since Cd is the 1st row of the kmap
     *     etc.
     */
    public int index(char a, char b)
    {
        if (Character.isUpperCase(a) && Character.isUpperCase(b))
        {
            return 0;
        }
        if (Character.isUpperCase(a) && !Character.isUpperCase(b))
        {
            return 1;
        }
        if (!Character.isUpperCase(a) && !Character.isUpperCase(b))
        {
            return 2;
        }
        else
        {
            return 3;
        }
    }
    
    /**
     * Handles the user interface
     */
    public static void main(String[] args) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        
        int option;

        System.out.println("0. Exit\n1. Enter Expression Manually\n2. Load Expression Text File\n");
        option = sc.nextInt();
        
        while (option != 0)
        {
            Simplify exp = new Simplify();
            
            System.out.println();
            if (option == 2)
            {
                exp.readFile();
            }
            else 
            {
                exp.input();
            }
           
            exp.printCanonical();
            System.out.println("\n");
            
            Kmap kmap = new Kmap(exp.makeKmap());
            
            exp.resolveKmap(kmap);
            
            System.out.println();
            System.out.println("Simplified Expression:");
            System.out.println(exp.simplifiedExpression);
            System.out.println();
            
            System.out.println("0. Exit\n1. Enter Expression Manually\n2. Load Expression Text File\n");
            option = sc.nextInt();
        }
        
        System.out.println();
        System.out.println(":)");
    }
}

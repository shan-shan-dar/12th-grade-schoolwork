
/*
 * Sudoku accepts a sudoku (with no null elements) of size n (a square number) and determines if the sudoku is complete according to rules
 * If not, it determines one of the mistakes in the sudoko
 */

import java.util.*;
import java.io.*;

public class Sudoku
{
    /*
     * Initiates instance variables for:
     *     Storing the sudoku
     *     Storing the result of the check
     *     Storing the name of the file loaded, if any
     */
    int[][] sudoku;
    String mistake = "Sudoku Complete";
    String filename = "";
    
    /*
     * Prints the contents of the int[][] parameter 'board' in a legible format
     */
    public void print(int[][] board)
    {
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[0].length; j++)
            {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }  
    }

    /*
     * Prompts user for manual input for the elements of the sudoku and returns the sudoku as an int[][]
     */
    public int[][] input()
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter size of sudoku");
        int size = sc.nextInt();

        System.out.println();

        int [][] sudoku = new int[size][size];

        System.out.println("Enter elements of sudoku");

        for (int i = 0; i < size; i++)
        {
            for (int j = 0; j < size; j++)
            {
                int value = sc.nextInt();
                sudoku[i][j] = value;
            }
        }

        System.out.println();
        print(sudoku);
        return sudoku;
    }

    /*
     * Prompts user for the file containing the sudoku and returns the sudoku as an int[][]
     */
    public int [][] readFile() throws Exception
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter sudoku file name");
        filename = sc.nextLine();
        String path = "/home/sdarshan/12thJavu/sudokus/" + filename;
        System.out.println();

        int [][] sudoku = null;

        try
        {
            File su = new File(path);

            Scanner fread = new Scanner(su);

            int size = fread.nextInt();

            sudoku = new int[size][size];

            for (int i = 0; i < size; i++)
            {
                for (int j = 0; j < size; j++)
                {
                    int value = fread.nextInt();
                    sudoku[i][j] = value;
                }
            }

            print(sudoku);
        }
        catch (Exception e)
        {
            System.out.println("File Error");
        }

        return sudoku;
    }

    /*
     * Creates a file named the result of the sudoku check and writes the result to it
     */
    public void writeFile() throws Exception
    {
        try
        {
            String path = "/home/sdarshan/12thJavu/sudokus/results/" + filename + " " + mistake;
            File su = new File(path);
            PrintStream ps = new PrintStream(su);

            ps.print(mistake);
        }
        catch (Exception e)
        {
            System.out.println("File Error " + e.getMessage());
        }
    }

    /*
     * Returns true if the elements of the int[] parameter 'arr' are distict from each other
     * Returns false otherwise
     */
    public boolean uniqueElements(int[] arr)
    {
        for (int i = 0; i < arr.length; i++)
        {
            for (int j = 0; j < arr.length; j++)
            {
                if (arr[i] == arr[j] && i != j)
                {
                    return false;
                }
            }
        }              
        return true;          
    }

    /*
     * Iterates over every row in the instance variable 'sudoku' and returns false if any row violates the rules of the sudoku
     * Returns true otherwise
     */
    public boolean checkHorizontal()
    {
        for (int i = 0; i < sudoku.length; i++)
        {
            int[] elements = new int[sudoku.length];
            int check = 0; 
            for (int j = 0; j < sudoku.length; j++)
            {
                if (sudoku[i][j] > sudoku.length || sudoku[i][j] < 1)
                {
                    mistake = i + " " + j + " not valid";
                    return false;
                }

                elements[j] = sudoku[i][j];
            }

            if (!uniqueElements(elements))
            {
                mistake = "Row " + i + " Incomplete";
                return false;
            }
        }

        return true;
    }

    /*
     * Iterates over every column in the instance variable 'sudoku' and returns false if any row violates the rules of the sudoku
     * Returns true otherwise
     */
    public boolean checkVertical()
    {
        for (int j = 0; j < sudoku.length; j++)
        {
            int[] elements = new int[sudoku.length];

            for (int i = 0; i < sudoku.length; i++)
            {
                if (sudoku[i][j] > sudoku.length || sudoku[i][j] < 1)
                {
                    mistake = i + " " + j + " not valid";
                    return false;
                }

                elements[i] = sudoku[i][j];
            }

            if (!uniqueElements(elements))
            {
                mistake = "Column " + j + " Incomplete";
                return false;
            }
        }

        return true;
    }

    /*
     * Iterates over box of size root(size) x root(size) in the instance variable 'sudoku' and returns false if any box violates the rules of the sudoku
     * Returns true otherwise
     */
    public boolean checkAllBoxes()
    {
        for (int i = 0; i < sudoku.length; i += (int)Math.sqrt(sudoku.length))
        {
            for (int j = 0; j < sudoku.length; j += (int)Math.sqrt(sudoku.length))
            {
                int[] box = new int[sudoku.length];

                int k = 0;
                for (int p = i; p < i + (int)Math.sqrt(sudoku.length); p++)
                {
                    for (int y = j; y < j + (int)Math.sqrt(sudoku.length); y++)
                    {
                        if (sudoku[p][y] > sudoku.length || sudoku[p][y] < 1)
                        {
                            mistake = p + " " + y + " not valid";
                            return false;
                        }

                        box[k] = sudoku[p][y];
                        k++;
                    }
                }

                if (!uniqueElements(box))
                {
                    mistake = "Box " + i + " " + j + " Incomplete";
                    return false;
                }
            }
        }

        return true;
    }

    /*
     * Runs a user friendly demonstration of the class
     */
    public static void main(String[] args) throws Exception
    {
        Scanner sc = new Scanner(System.in);

        int option;

        System.out.println("0. Exit\n1. Check New Sudoku (Enter Sudoku)\n2. Check New Sudoku (Load File)\n");
        option = sc.nextInt();

        System.out.println();

        Sudoku s = new Sudoku();

        while(option != 0)
        {
            switch(option)
            {
                case 1:
                s.sudoku = s.input();
                break;

                case 2:
                s.sudoku = s.readFile();
                break;

                default:
                System.out.println("Wrong Input");
                break;
            }

            System.out.println();

            s.mistake = "Sudoku Complete";
            if (!(s.checkHorizontal() && s.checkVertical() && s.checkAllBoxes()))
            {
                System.out.println("Sudoku Incomplete");   
            }

            System.out.println(s.mistake);

            if (option == 2)
            {
                s.writeFile();
            }

            System.out.println();

            System.out.println("0. Exit\n1. Check New Sudoku (Enter Sudoku)\n2. Check New Sudoku (Load File)\n");
            option = sc.nextInt();

            System.out.println();
        }

        System.out.println(":)");
    }
}

    
/**
 * ●○
 * ■□
 */

import java.util.Scanner;
import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.Date;

public class Life
{
    char[][] board;
    
    public void print()
    {
        System.out.print("\t");
        
        for (int i = 0; i < board[0].length; i++)
        {
            System.out.print("[" + i + "]" + "\t");
        }
        
        System.out.println();
        System.out.println();
        
        for (int i = 0; i < board.length; i++)
        {
            System.out.print("[" + i + "]" + "\t");
            for (int j = 0; j < board[0].length; j++)
            {
                System.out.print(board[i][j] + "\t");
            }
            System.out.println();
            System.out.println();
        }  
    }
    
    public void initiate(int l, int b)
    {
        board = new char[l][b];
        
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[0].length; j++)
            {
                board[i][j] = '□';
            }
        }
    }
    
    public void flip(int y, int x)
    {
        if (board[y][x] == '□')
        {
            board[y][x] = '■';
        }
        else
        {
            board[y][x] = '□';
        }
    }
    
    public int neighbor(int y, int x)
    {
        int count = 0;
        
        for (int i = -1; i < 2; i++)
        {
            for (int j = -1; j < 2; j++)
            {
                if (!(i == 0 && j == 0) && (y + i >= 0) && (x + j >= 0) && (y + i < board.length) && (x + j < board[0].length))
                {
                    if (board[y+i][x+j] == '■')
                    {
                        count++;
                    }
                }
            }
        }
        
        return count;
    }
    
    public void update()
    {
        for (int y = 0; y < board.length; y++)
        {
            for (int x = 0; x < board[0].length; x++)
            {
                int n = neighbor(y, x);
                
                //For a space that is populated
                if (board[y][x] == '■')
                {
                    //Each cell with one or no neighbors dies, as if by solitude
                    if (n <= 1)
                    {
                        flip(y, x);
                    }
                    
                    //Each cell with four or more neighbors dies, as if by overpopulation
                    if (n >= 4)
                    {
                        flip(y, x);
                    }
                    
                    //Each cell with two or three neighbors survives
                }
                
                //For a space that is empty or unpopulated
                if (board[y][x] == '□')
                {
                    //Each cell with three neighbors becomes populated
                    if (n == 3)
                    {
                        flip(y, x);
                    }
                }
                
            }
        }   
    }
    
    public void play(int ln, int br, int rn)
    {
        Scanner sc = new Scanner(System.in);
        
        initiate(ln, br);
        
        int x;
        int y;
        
        char temp = 'n';
        
        print();
        
        while (temp != 'Y' && temp != 'y')
        {
            System.out.println("\nIndex: ");
            y = sc.nextInt();
            x = sc.nextInt();
            
            flip(y, x);
            
            System.out.println("");
            
            print();
            
            System.out.println("\nRun?");
            sc.nextLine();
            temp = sc.nextLine().charAt(0);
            System.out.println("");
        }
        
        System.out.print('\u000C');
        
        int i = 0;
        
        while (i < rn)
        {      
            print();
            update();
            
            try
            {
                Thread.sleep(800);
            }
            catch(InterruptedException ex)
            {
                
            }
            
            System.out.print('\u000C');
            i++;
        }
    }
}

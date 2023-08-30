
import java.util.Scanner;

public class Connect4
{
    char black = '●';
    char white = '○';
    char blank = ' ';
    char[][] board;
    
    public void initBoard(int row, int col)
    {
        if (row < 4)
        {
            row = 4;
        }
        
        if (col < 4)
        {
            col = 4;
        }
        
        board = new char[row][col];
        
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[0].length; j++)
            {
                board[i][j] = blank;
            }
        }
    }
    
    public void printBoard()
    {
        System.out.println();
        
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[0].length; j++)
            {
                System.out.print(board[i][j] + "\t");
            }
            System.out.println();
        }
        
        System.out.println();
        
        for (int i = 0; i < board[0].length; i++)
        {
            System.out.print(i + "\t");
        }
        
        System.out.println();
        System.out.println();
    }
    
    public char player()
    {
        int countBlack = 0;
        int countWhite = 0;
        
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[0].length; j++)
            {
                if (board[i][j] == black)
                {
                    countBlack++;
                }
                if (board[i][j] == white)
                {
                    countWhite++;
                }
            }
        }
        
        if (countBlack == countWhite)
        {
            return black;
        }
        else
        {
            return white;
        }
    }
    
    public int lowestBlank(int j)
    {
        if (j >= board[0].length)
        {
            return -1;
        }
        
        for (int i = board.length - 1; i >= 0; i--)
        {
            if (board[i][j] == blank)
            {
                return i;
            }
        }
        return -1;
    }
    
    public boolean update(int j)
    {
        if (lowestBlank(j) != -1 && j < board[0].length)
        {
            board[lowestBlank(j)][j] = player();
        }
        else
        {
            return false;
        }
        return true;
    }
    
    public char check()
    {
        //check horizontals
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board[0].length - 3; j++)
            {
                if (board[i][j] != blank && board[i][j+1] == board[i][j] && board[i][j+2] == board[i][j] && board[i][j+3] == board[i][j])
                {
                    return board[i][j];
                }
            }
        }
        
        //check verticals
        for (int i = 0; i < board.length - 3; i++)
        {
            for (int j = 0; j < board[0].length; j++)
            {
                if (board[i][j] != blank && board[i+1][j] == board[i][j] && board[i+2][j] == board[i][j] && board[i+3][j] == board[i][j])
                {
                    return board[i][j];
                }
            }
        }
        
        //check descending diagonals
        for (int i = 0; i < board.length - 3; i++)
        {
            for (int j = 0; j < board[0].length - 4; j++)
            {
                if (board[i][j] != blank && board[i+1][j+1] == board[i][j] && board[i+2][j+2] == board[i][j] && board[i+3][j+3] == board[i][j])
                {
                    return board[i][j];
                }
            }
        }
        
        //check ascending diagonals
        for (int i = board.length - 1; i > 2; i--)
        {
            for (int j = 0; j < board[0].length - 3; j++)
            {
                if (board[i][j] != blank && board[i-1][j+1] == board[i][j] && board[i-2][j+2] == board[i][j] && board[i-3][j+3] == board[i][j])
                {
                    return board[i][j];
                }
            }
        }
        
        return blank;
    }
    
    public static void main (String[] args)
    {
        Scanner sc = new Scanner(System.in);
        
        int option;

        System.out.println("0. Exit\n1. Play\n");
        option = sc.nextInt();

        System.out.println();

        Connect4 play = new Connect4();
        
        while(option != 0)
        {
            System.out.print('\f');
            
            System.out.println("Enter size of the board:");
            int row = sc.nextInt();
            int col = sc.nextInt();
            
            System.out.print('\f');
            
            play.initBoard(row, col);
           
            boolean valid = true;
            
            while (play.check() == play.blank)
            {
                play.printBoard();
                
                System.out.println();
                
                System.out.println("Player " + play.player() + "\'s turn:");
                
                System.out.println();
                
                if (valid == false)
                {
                  System.out.println("Invalid play");   
                }
                
                int p = sc.nextInt();
                
                valid = play.update(p);
                
                System.out.print('\f');
            }
            
            play.printBoard();
            System.out.println();
            
            System.out.println(play.check() + " is the winner :)");
            
            System.out.println();
            
            System.out.println("0. Exit\n1. Play again\n");
            option = sc.nextInt();
            
            System.out.println();
        }
        
        System.out.println(":)");
    }
}

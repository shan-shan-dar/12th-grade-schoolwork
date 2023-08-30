            
import java.util.Scanner;
import java.io.*;
import java.lang.*;
    
public class WordFind
{
    char[][] board;
    String[] words;
    String[] dictionary = new String[10000];
    
    //# Initialize and Input
    
    public void init(int n, String[] words)
    {
        board = new char[n][n];
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n; j++)
            {
                board[i][j] = '0';
            }
        }
        this.words = words;
    }
    
    public void input()
    {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Size of board: ");
        int n = sc.nextInt();
        
        System.out.println();
        
        System.out.println("Enter words: ");
        sc.nextLine();
        String w = sc.nextLine();
        w = w.toUpperCase();
        
        String[] words = w.split(" ");
        
        String[] wordsNew = filter(words, n);
                
        if(wordsNew.length != words.length)
        {
            System.out.println();
            System.out.println("(Some words are omitted due to length)");
        }
        
        init(n, wordsNew);
    }
    
    public void initDictionary() throws Exception
    {
        String path = "/home/sdarshan/12thJavu/Dictionary10000.txt";

        try
        {
            File d = new File(path);

            Scanner fread = new Scanner(d);

            for (int i = 0; i < 10000; i++)
            {
                dictionary[i] = fread.next().toUpperCase();
            }
        }
        catch (Exception e)
        {
            System.out.println("File Error");
        }
    }
    
    //# Place words in board
    
    public char[][] hori(char[][] board, String word)
    {
        int len = word.length();
        
        for (int t = 0; t < 100; t++)
        {
            //generate random index
            int iRand = (int)(Math.random() * board.length);
            int jRand = (int)(Math.random() * board[0].length);
            
            //check if it crosses the board
            if (jRand + len > board[0].length)
            {
                continue;
            }
            
            //check if it clashes with another word
            boolean flag = false;
            
            for (int j = 0; j < len; j++)
            {
                if (board[iRand][jRand + j] != '0')
                {
                    flag = true;
                    break;
                }
            }
            
            if (flag)
            {
                continue;
            }
            
            //add word
            for (int j = 0; j < len; j++)
            {
                board[iRand][(jRand + j)%board[0].length] = word.charAt(j);
            }
            
            return board;
        }
        
        String[] wordsNew = remove(words,word);
        if(wordsNew.length != words.length)
        {
            System.out.println();
            System.out.println("(Some words are omitted)");
        }
        words = wordsNew;
        return board;
    }
    
    public char[][] vert(char[][] board, String word)
    {
        int len = word.length();
        
        for (int t = 0; t < 100; t++)
        {
            //generate random index
            int iRand = (int)(Math.random() * board.length);
            int jRand = (int)(Math.random() * board[0].length);
            
            //check if it crosses the board
            if (iRand + len > board.length)
            {
                continue;
            }
            
            //check if it clashes with another word
            boolean flag = false;
            
            for (int i = 0; i < len; i++)
            {
                if (board[iRand + i][jRand] != '0')
                {
                    flag = true;
                    break;
                }
            }
            
            if (flag)
            {
                continue;
            }
            
            //add word
            for (int i = 0; i < len; i++)
            {
                board[iRand + i][jRand] = word.charAt(i);
            }
            
            return board;
        }
        
        String[] wordsNew = remove(words,word);
        if(wordsNew.length != words.length)
        {
            System.out.println();
            System.out.println("(Some words are omitted)");
        }
        words = wordsNew;
        return board;
    }
    
    public char[][] diagDown(char[][] board, String word)
    {
        int len = word.length();
        
        for (int t = 0; t < 100; t++)
        {
            //generate random index
            int iRand = (int)(Math.random() * board.length);
            int jRand = (int)(Math.random() * board[0].length);
            
            //check if it crosses the board
            if (iRand + len > board.length || jRand + len > board[0].length)
            {
                continue;
            }
            
            //check if it clashes with another word
            boolean flag = false;
            
            for (int i = 0; i < len; i++)
            {
                if (board[iRand + i][jRand + i] != '0')
                {
                    flag = true;
                    break;
                }
            }
            
            if (flag)
            {
                continue;
            }
            
            //add word
            for (int i = 0; i < len; i++)
            {
                board[iRand + i][jRand + i] = word.charAt(i);
            }
            
            return board;
        }
        
        String[] wordsNew = remove(words,word);
        if(wordsNew.length != words.length)
        {
            System.out.println();
            System.out.println("(Some words are omitted)");
        }
        words = wordsNew;
        return board;
    }
    
    public char[][] diagUp(char[][] board, String word)
    {
        int len = word.length();
        
        for (int t = 0; t < 100; t++)
        {
            //generate random index
            int iRand = (int)(Math.random() * board.length);
            int jRand = (int)(Math.random() * board[0].length);
            
            //check if it crosses the board
            if (iRand - len < 0 || jRand + len > board[0].length)
            {
                continue;
            }
            
            //check if it clashes with another word
            boolean flag = false;
            
            for (int i = 0; i < len; i++)
            {
                if (board[iRand - i][jRand + i] != '0')
                {
                    flag = true;
                    break;
                }
            }
            
            if (flag)
            {
                continue;
            }
            
            //add word
            for (int i = 0; i < len; i++)
            {
                board[iRand - i][jRand + i] = word.charAt(i);
            }
            
            return board;
        }
        
        String[] wordsNew = remove(words,word);
        if(wordsNew.length != words.length)
        {
            System.out.println("(Some words are omitted since they couldn't be fit in)");
            System.out.println();
        }
        words = wordsNew;
        return board;
    }
    
    public void makeBoard()
    {
        for (int i = 0; i < words.length; i++)
        {
            String word = words[i];
            
            double rev = Math.random();
            
            if (rev < 0.4)
            {
                word = reverse(word);
            }
            
            int r = (int)(Math.random() * 4);
            
            switch(r)
            {
                case 0:
                    hori(board, word);
                    break;
                    
                case 1:
                    vert(board, word);
                    break;
                    
                case 2:
                    diagDown(board, word);
                    break;
                
                case 3:
                    diagUp(board, word);
                    break;
            }
        }
        
        for (int i = 0; i < board.length; i++)
        {   
            for (int j = 0; j < board[0].length ; j++)
            {
                if (board[i][j] == '0')
                {   
                    int r = (int)((Math.random() * 26) + 65);
                    
                    board[i][j] = (char)r;
                }
            }
        }
    }
    
    //# Play logic and checking
    
    public void play()
    {
        Scanner sc = new Scanner(System.in);
        
        String[] found = new String[words.length];
        String[] bonus = new String[10];
        int f = -1;
        int b = -1;
        boolean finish = false;
        
        while (!finish)
        {
            System.out.print('\u000C');
            
            System.out.println("Words:");
                
            for (int i = 0; i < words.length; i++)
            {
                System.out.print(words[i].toLowerCase() + " ");
            }
            
            System.out.println();
            System.out.println();
            
            print(board);
            System.out.println();
            
            System.out.println("Words found so far:");
            for (int i = 0; i < found.length; i++)
            {
                if (found[i] != null)
                {
                    System.out.print(found[i].toLowerCase() + " ");
                }
            }
            for (int i = 0; i < bonus.length; i++)
            {
                if (bonus[i] != null)
                {
                    System.out.print(bonus[i].toLowerCase() + " ");
                }
            }
            System.out.println();
            System.out.println();
            
            System.out.println("Enter word: word i j h/v/u/d r/n");
            
            String p = sc.nextLine();
            p = p.toUpperCase();
            
            String[] play = p.split(" ");
            
            System.out.println();
            
            if (check(play[0], Integer.parseInt(play[1]), Integer.parseInt(play[2]), play[3].charAt(0), play[4].charAt(0), words))
            {
                f++;
                found[f] = play[0];
                
                System.out.println("Nice.");
            }
            
            else if (play[0].length() < 4)
            {
                System.out.println("Too small.");
            }
            
            else if (check(play[0], Integer.parseInt(play[1]), Integer.parseInt(play[2]), play[3].charAt(0), play[4].charAt(0), dictionary))
            {
                b++;
                bonus[b] = play[0];
                
                System.out.println("Nice.");
            }
            else 
            {
                System.out.println("Nope.");
            }
            
            finish = true;
            for (int i = 0; i < found.length; i++)
            {
                if (!search(found, words[i]))
                {
                    finish = false;
                    continue;
                }
            }
            
            sc.nextLine();
        }
        
        System.out.print('\u000C');
            
        System.out.println("Words:");
            
        for (int i = 0; i < words.length; i++)
        {
            System.out.print(words[i].toLowerCase() + " ");
        }
        
        System.out.println();
        System.out.println();
        
        print(board);
        System.out.println();
        
        System.out.println("Words found:");
        for (int i = 0; i < found.length; i++)
        {
            if (found[i] != null)
            {
                System.out.print(found[i].toLowerCase() + " ");
            }
        }
        System.out.println();
        System.out.println();
        System.out.println("Bonus words:");
        for (int i = 0; i < bonus.length; i++)
        {
            if (bonus[i] != null)
            {
                System.out.print(bonus[i].toLowerCase() + " ");
            }
        }
        System.out.println();
        System.out.println();
        
        System.out.println("You've found all the words :)");
        
    }
    
    public boolean checkHori(char[][] board,int I, int J, String word)
    {
        if (J + word.length() >= board.length)
        {
            return false;
        }
        
        for (int j = 0; j < word.length(); j++)
        {
            if (Character.toUpperCase(board[I][J+j]) != word.charAt(j))
            {
                return false;
            }
        }
        
        return true;
    }
    
    public boolean checkVert(char[][] board,int I, int J, String word)
    {
        if (I + word.length() >= board.length)
        {
            return false;
        }
        for (int i = 0; i < word.length(); i++)
        {
            if (Character.toUpperCase(board[I+i][J]) != word.charAt(i))
            {
                return false;
            }
        }
        
        return true;
    }
    
    public boolean checkDiagDown(char[][] board,int I, int J, String word)
    {
        if (I + word.length() >= board.length || J + word.length() >= board.length)
        {
            return false;
        }
        for (int k = 0; k < word.length(); k++)
        {
            if (Character.toUpperCase(board[I+k][J+k]) != word.charAt(k))
            {
                return false;
            }
        }
        
        return true;
    }
    
    public boolean checkDiagUp(char[][] board,int I, int J, String word)
    {
        if (I - word.length() < 0 || J + word.length() >= board.length)
        {
            return false;
        }
        for (int k = 0; k < word.length(); k++)
        {
            if (Character.toUpperCase(board[I-k][J+k]) != word.charAt(k))
            {
                return false;
            }
        }
        
        return true;
    }
    
    public boolean check(String word, int I, int J, char direction, char reversed, String[] words)
    {
        boolean correct = false;
        
        if (!search(words, word))
        {
            return false;
        }
        
        if (reversed == 'R')
        {
            word = reverse(word);
        }
        
        switch(direction)
        {
            case 'H':
                if (reversed == 'R')
                {
                    J = J - (word.length() - 1);
                }
                correct = checkHori(board, I, J, word);
                if (correct)
                {
                    board = updateHori(board, I, J, word);
                }
                return correct;
                
            case 'V':
                if (reversed == 'R')
                {
                    I = I - (word.length() - 1);
                }
                correct = checkVert(board, I, J, word);
                if (correct)
                {
                    board = updateVert(board, I, J, word);
                }
                return correct;
            
            case 'D':
                if (reversed == 'R')
                {
                    I = I - (word.length() - 1);
                    J = J - (word.length() - 1);
                }
                correct = checkDiagDown(board, I, J, word);
                if (correct)
                {
                    board = updateDiagDown(board, I, J, word);
                }
                return correct;
                
            case 'U':
                if (reversed == 'R')
                {
                    I = I + (word.length() - 1);
                    J = J - (word.length() - 1);
                }
                correct = checkDiagUp(board, I, J, word);
                if (correct)
                {
                    board = updateDiagUp(board, I, J, word);
                }
                return correct;
                
            default:
                return false;
        }
    }
    
    public char[][] updateHori(char[][] board,int I, int J, String word)
    {
        for (int j = 0; j < word.length(); j++)
        {
            if (Character.isUpperCase(board[I][J+j]))
            {
                board[I][J+j] = Character.toLowerCase(board[I][J+j]);
            }
            else
            {
                board[I][J+j] = Character.toUpperCase(board[I][J+j]);
            }
        }
        
        return board;
    }
    
    public char[][] updateVert(char[][] board,int I, int J, String word)
    {
        for (int i = 0; i < word.length(); i++)
        {
            if (Character.isUpperCase(board[I+i][J]))
            {
                board[I+i][J] = Character.toLowerCase(board[I+i][J]);
            }
            else
            {
                board[I+i][J] = Character.toUpperCase(board[I+i][J]);
            }
        }
        
        return board;
    }
    
    public char[][] updateDiagDown(char[][] board,int I, int J, String word)
    {
        for (int k = 0; k < word.length(); k++)
        {
            if (Character.isUpperCase(board[I+k][J+k]))
            {
                board[I+k][J+k] = Character.toLowerCase(board[I+k][J+k]);
            }
            else
            {
                board[I+k][J+k] = Character.toUpperCase(board[I+k][J+k]);
            }
        }
        
        return board;
    }
    
    public char[][] updateDiagUp(char[][] board,int I, int J, String word)
    {
        for (int k = 0; k < word.length(); k++)
        {
            if (Character.isUpperCase(board[I-k][J+k]))
            {
                board[I-k][J+k] = Character.toLowerCase(board[I-k][J+k]);
            }
            else
            {
                board[I-k][J+k] = Character.toUpperCase(board[I-k][J+k]);
            }
        }
        
        return board;
    }
    
    //# Interface
    
    public static void main(String[] args) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        
        int option;
        int option2;
    
        System.out.println("0. Exit\n1. Generate Board\n");
        option = sc.nextInt();
        
        WordFind game = new WordFind();
        game.initDictionary();
        
        while (option != 0)
        {
            System.out.println();
            
            //handles the ways in which a new board is made
            if (option == 1 || option == 3)
            {
                System.out.print('\u000C');
                    
                game.input();
                System.out.println();
            }
            else if (option == 2)
            {
                System.out.print('\u000C');
                System.out.println("Words:");
                    
                for (int i = 0; i < game.words.length; i++)
                {
                    System.out.print(game.words[i].toLowerCase() + " ");
                }
                
                System.out.println();
                System.out.println();
                
                game.init(game.board.length, game.words);
            }
            
            //to play or not to play
            if (option == 4)
            {
                game.play();
                
                System.out.println();
                
                System.out.println("0. Exit\n1. Generate Board\n");
                option = sc.nextInt();
                continue;
            }
            else
            {
                game.makeBoard();
            
                game.print(game.board);
                System.out.println();
            }
            
            System.out.println("0. Exit\n1. Play\n2. Regenerate Board\n3. Generate New Board\n");
            option = sc.nextInt();
            
            if (option == 1)
            {
                option = 4;
            }
        }
    }
    
    //# Helper functions
    
    public void print(char[][] board)
    {
        System.out.print('\t');
        for (int i = 0; i < board[0].length; i++)
        {
            System.out.print(i + "\t");
        }
        System.out.println();
        for (int i = 0; i < board.length; i++)
        {   
            System.out.print(i + "\t");
            for (int j = 0; j < board[0].length ; j++)
            {
                System.out.print("" + board[i][j] + "\t");
            }
            System.out.println();
        }
    }
    
    public String reverse(String word)
    {
        String reversedWord = "";
        
        for (int i = 0; i < word.length(); i++)
        {   
            reversedWord += word.charAt(word.length() - 1 - i);
        }
        
        return reversedWord;
    }
    
    public boolean search(String[] array, String word)
    {   
        for(int i = 0; i < array.length; i++)
        {
            if (array[i] != null)
            {
                if (array[i].compareTo(word) == 0)
                {
                    return true;
                }
            }
        }
        return false;
    }
    
    public String[] filter(String[] array, int n)
    {   
        String[] wordsNew;
        
        int count = 0;
        for (int i = 0; i < array.length; i++)
        {
            if (array[i].length() > n)
            {
                count++;
            }
        }
        
        wordsNew = new String[array.length - count];
        
        int j = 0;
        for (int i = 0; i < array.length; i++)
        {
            if (!(array[i].length() > n))
            {
                wordsNew[j] = array[i];
                j++;
            }
        }
        
        return wordsNew;
    }
    
    public String[] remove(String[] array, String word)
    {
        String[] wordsNew;
        
        int count = 0;
        for (int i = 0; i < array.length; i++)
        {
            if (array[i].compareTo(word) == 0)
            {
                count++;
            }
        }
        
        wordsNew = new String[array.length - count];
        
        int j = 0;
        for (int i = 0; i < array.length; i++)
        {
            if (!(array[i].compareTo(word) == 0))
            {
                wordsNew[j] = array[i];
                j++;
            }
        }
        
        return wordsNew;
    }
}


import java.util.Scanner;
import java.io.*;
import java.lang.*;

public class WordFind2
{
    /* 
     * Instance variables for storing the board, list of entered words and a dictionary
     */
    char[][] board;
    String[] words;
    String[] dictionary = new String[0];
    
    //# Initialize and Input
    
    /* 
     * init makes board a matrix of size n and the list of words
     */
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
    
    /* 
     * input asks the user for the size of the board and words and uses init function to initialise the words array and board matrix
     */
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
        
        init(n, words);
    }
    
    /* 
     * initDictionary reads the dictionary file and fills in the dictionary array
     */
    public void initDictionary() throws Exception
    {
        String path = "/home/sdarshan/12thJavu/Dictionary10000.txt";

        try
        {
            File d = new File(path);

            Scanner fread = new Scanner(d);

            for (int i = 0; i < 10000; i++)
            {
                String word = fread.next().toUpperCase();
                if (word.length() > 2)
                {
                    dictionary = append(word, dictionary);
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("File Error");
        }
    }
    
    //# Place words in board
    
    /* 
     * hori places a given word (or reverse of a word) in the board horizontally, checking that it fits and dosen't clash with other words
     */
    public boolean hori(String word, boolean rev)
    {
        int len = word.length();
        
        String originalWord = word;
        
        if(rev)
        {
            originalWord = reverse(word);
        }
        
        if (len > board[0].length)
        {
            words = remove(words,originalWord);
            return false;
        }
        
        for (int t = 0; t < 1000; t++)
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
            
            return true;
        }
        
        words = remove(words,originalWord);
        return false;
    }
    
    /* 
     * veret places a given word (or reverse of a word) in the board vertically, checking that it fits and dosen't clash with other words
     */
    public boolean vert(String word, boolean rev)
    {
        int len = word.length();
        
        String originalWord = word;
        
        if(rev)
        {
            originalWord = reverse(word);
        }
        
        if (len > board.length)
        {
            words = remove(words,originalWord);
            return false;
        }
        
        for (int t = 0; t < 1000; t++)
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
            
            return true;
        }
        
        words = remove(words,originalWord);
        return false;
    }
    
    /* 
     * diagDown places a given word (or reverse of a word) in the board diagonally down, checking that it fits and dosen't clash with other words
     */
    public boolean diagDown(String word, boolean rev)
    {
        int len = word.length();
        
        String originalWord = word;
        
        if(rev)
        {
            originalWord = reverse(word);
        }
        
        if (len > board[0].length || len > board.length)
        {
            words = remove(words,originalWord);
            return false;
        }
        
        for (int t = 0; t < 1000; t++)
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
            
            return true;
        }
        
        words = remove(words,originalWord);
        return false;
    }
    
    /* 
     * diagUp places a given word (or reverse of a word) in the board diagonally up, checking that it fits and dosen't clash with other words
     */
    public boolean diagUp(String word, boolean rev)
    {
        int len = word.length();
        
        String originalWord = word;
        
        if(rev)
        {
            originalWord = reverse(word);
        }
        
        if (len > board[0].length || len > board.length)
        {
            words = remove(words,originalWord);
            return false;
        }
        
        for (int t = 0; t < 1000; t++)
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
            
            return true;
        }
        
        words = remove(words,originalWord);
        return false;
    }
    
    /* 
     * makeBoard randomly uses hori, vert, diagDown and diagUp on al the words that user has inputted
     * It also fills the rest of the board with random characters
     */
    public void makeBoard()
    {
        for (int i = 0; i < words.length; i++)
        {
            String word = words[i];
            
            double rev = Math.random();
            
            boolean revOrNot = false;
            if (rev < 0.4)
            {
                word = reverse(word);
                revOrNot = true;
            }
            
            int r = (int)(Math.random() * 4);
            
            switch(r)
            {
                case 0:
                    if(!hori(word,revOrNot))
                    {
                        i--;
                    }
                    break;
                    
                case 1:
                    if(!vert(word,revOrNot))
                    {
                        i--;
                    }
                    break;
                    
                case 2:
                    if(!diagDown(word,revOrNot))
                    {
                        i--;
                    }
                    break;
                
                case 3:
                    if(!diagUp(word,revOrNot))
                    {
                        i--;
                    }
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
    
    //# Check
    
    /* 
     * checkHori checks if word (or reverse of a word) is present at position I and J in the board horizontally
     */
    public boolean checkHori(String word, int I, int J, char rev)
    {
        int len = word.length();
        
        int factor = 1;
        
        if (rev == 'R')
        {
            factor = -1;
        }
        
        if (J > board.length || J < 0 || J + (factor*(len - 1)) > board.length || J + (factor*(len - 1)) < 0)
        {
            return false;
        }
        
        for (int j = 0; j < len; j++)
        {
            if (board[I][J+(factor*j)] != word.charAt(j))
            {
                return false;
            }
        }
        
        return true;
    }
    
    /* 
     * checkVert checks if word (or reverse of a word) is present at position I and J in the board vertically
     */
    public boolean checkVert(String word, int I, int J, char rev)
    {
        int len = word.length();
        
        int factor = 1;
        
        if (rev == 'R')
        {
            factor = -1;
        }
        
        if (I > board.length || I < 0 || I + factor*(len - 1) > board.length || I + factor*(len - 1) < 0)
        {
            return false;
        }
        
        for (int i = 0; i < word.length(); i++)
        {
            if (board[I+(factor*i)][J] != word.charAt(i))
            {
                return false;
            }
        }
        
        return true;
    }
    
    /* 
     * checkDiagDown checks if word (or reverse of a word) is present at position I and J in the board diganally down
     */
    public boolean checkDiagDown(String word, int I, int J, char rev)
    {
        int len = word.length();
        
        int factor = 1;
        
        if (rev == 'R')
        {
            factor = -1;
        }
        
        if (I > board.length || I < 0 || I + factor*(len - 1) > board.length || J + factor*(len - 1) < 0)
        {
            return false;
        }
        
        for (int k = 0; k < word.length(); k++)
        {
            if (Character.toUpperCase(board[I+(factor*k)][J+(factor*k)]) != word.charAt(k))
            {
                return false;
            }
        }
        
        return true;
    }
    
    /* 
     * checkDiagUp checks if word (or reverse of a word) is present at position I and J in the board digonally up
     */
    public boolean checkDiagUp(String word, int I, int J, char rev)
    {
        int len = word.length();
        
        int factor = 1;
        
        if (rev == 'R')
        {
            factor = -1;
        }
        
        if (I > board.length || I < 0 || I - factor*(len - 1) > board.length || J + factor*(len - 1) < 0)
        {
            return false;
        }
        
        for (int k = 0; k < word.length(); k++)
        {
            if (Character.toUpperCase(board[I-(factor*k)][J+(factor*k)]) != word.charAt(k))
            {
                return false;
            }
        }
        
        return true;
    }
    
    /* 
     * check uses the functions checkHori,checkVert, checkDiagDown and checkDiagUp to check if word is a valid find from an array words
     */
    public boolean check(String word, int I, int J, char dir, char rev, String[]words)
    {
        boolean correct = false;
        
        if (!search(word, words))
        {
            return false;
        }
        
        switch(dir)
        {
            case 'H':
                if (checkHori(word, I, J, rev))
                {
                    updateHori(word, I, J, rev);
                    return true;
                }
                return false;
                
            case 'V':
                if (checkVert(word, I, J, rev))
                {
                    updateVert(word, I, J, rev);
                    return true;
                }
                return false;
            
            case 'D':
                if (checkDiagDown(word, I, J, rev))
                {
                    updateDiagDown(word, I, J, rev);
                    return true;
                }
                return false;
                
            case 'U':
                if (checkDiagUp(word, I, J, rev))
                {
                    updateDiagUp(word, I, J, rev);
                    return true;
                }
                return false;
                
            default:
                return false;
        }
    }
    
    //# Update
    
    /* 
     * updateHori changes the case of word (or reverse of word) from position I and J in the board horizontally
     */
    public void updateHori(String word, int I, int J, char rev)
    {
        int factor = 1;
        
        if (rev == 'R')
        {
            factor = -1;
        }
        
        for (int j = 0; j < word.length(); j++)
        {
            if (Character.isUpperCase(board[I][J+(factor*j)]))
            {
                board[I][J+(factor*j)] = Character.toLowerCase(board[I][J+(factor*j)]);
            }
            else
            {
                board[I][J+(factor*j)] = Character.toUpperCase(board[I][J+(factor*j)]);
            }
        }
    }
    
    /* 
     * updateVert changes the case of word (or reverse of word) from position I and J in the board vertically
     */
    public void updateVert(String word, int I, int J, char rev)
    {
        int factor = 1;
        
        if (rev == 'R')
        {
            factor = -1;
        }
        
        for (int i = 0; i < word.length(); i++)
        {
            if (Character.isUpperCase(board[I+(factor*i)][J]))
            {
                board[I+(factor*i)][J] = Character.toLowerCase(board[I+(factor*i)][J]);
            }
            else
            {
                board[I+(factor*i)][J] = Character.toUpperCase(board[I+(factor*i)][J]);
            }
        }
    }
    
    /* 
     * updateDiagDown changes the case of word (or reverse of word) from position I and J in the board diagonally down
     */
    public void updateDiagDown(String word, int I, int J, char rev)
    {
        int factor = 1;
        
        if (rev == 'R')
        {
            factor = -1;
        }
        
        for (int k = 0; k < word.length(); k++)
        {
            if (Character.isUpperCase(board[I+(factor*k)][J+(factor*k)]))
            {
                board[I+(factor*k)][J+(factor*k)] = Character.toLowerCase(board[I+(factor*k)][J+(factor*k)]);
            }
            else
            {
                board[I+(factor*k)][J+(factor*k)] = Character.toUpperCase(board[I+(factor*k)][J+(factor*k)]);
            }
        }
    }
    
    /* 
     * updateDiagUp changes the case of word (or reverse of word) from position I and J in the board diagonally up
     */
    public void updateDiagUp(String word, int I, int J, char rev)
    {
        int factor = 1;
        
        if (rev == 'R')
        {
            factor = -1;
        }
        
        for (int k = 0; k < word.length(); k++)
        {
            if (Character.isUpperCase(board[I-(factor*k)][J+(factor*k)]))
            {
                board[I-(factor*k)][J+(factor*k)] = Character.toLowerCase(board[I-(factor*k)][J+(factor*k)]);
            }
            else
            {
                board[I-(factor*k)][J+(factor*k)] = Character.toUpperCase(board[I-(factor*k)][J+(factor*k)]);
            }
        }
    }
    
    //# Play
    
    /* 
     * play handles the entire user interface and logic for playing the game using the check function
     */
    public void play()
    {
        Scanner sc = new Scanner(System.in);
        
        boolean win = false;
        
        String[] found = new String[0];
        String[] bonus = new String[0];
        
        while(!win)
        {
            System.out.print('\u000C');
            
            printWords();
            System.out.println();
            printBoard();
            printFound(found, bonus);
            System.out.println();
            
            System.out.println("Enter word: word i j h/v/u/d r/n");
            
            String s = sc.nextLine();
            s = s.toUpperCase();
            
            String[] move = s.split(" ");
            
            String word = move[0];
            int I = Integer.parseInt(move[1]);
            int J = Integer.parseInt(move[2]);
            char dir = move[3].charAt(0);
            char rev = move[4].charAt(0);
            
            System.out.println();
            if (check(word, I, J, dir, rev, words))
            {
                found = append(word, found);
                System.out.println("Nice.");
            }
            else if (check(word, I, J, dir, rev, dictionary))
            {
                bonus = append(word, bonus);
                System.out.println("Nice.");
            }
            else
            {
                System.out.println("Bhup.");
            }
            
            win = compare(found, words);
            
            sc.nextLine();
        }
        
        System.out.println("You won sheee.");
    }
    
    //# Usetr Interface
    
    public static void main(String[] args) throws Exception
    {
        Scanner sc = new Scanner(System.in);
        
        int option;
        
        System.out.println("Come find word :)");
        System.out.println();
        
        System.out.println("0. Exit\n1. Generate Board\n");
        option = sc.nextInt();
        
        WordFind2 game = new WordFind2();
        
        String[] ogWords = null;
        
        while(option != 0)
        {
            System.out.print('\u000C');
            switch(option)
            {
                case 1:
                    game.input();
                    
                    ogWords = game.words;
                    
                    game.makeBoard();
                    System.out.println();
                    game.printWords();
                    System.out.println();
                    game.printBoard();
                    System.out.println();
                    break;
                    
                case 2:
                    game.init(game.board.length, ogWords);
                    game.makeBoard();
                    game.printWords();
                    System.out.println();
                    game.printBoard();
                    System.out.println();
                    break;
                
                case 3:
                    game.play();
            }
            
            System.out.println();
            System.out.println("0. Exit\n1. Generate Board\n2. Regenerate Board\n3. Play");
            System.out.println();
            option = sc.nextInt();
        }
        
        System.out.println(":)");
    }
    
    //# Helper Functions
    
    /* 
     * remove removes an element from a given array
     */
    public String[] remove(String[] array, String element)
    {
        String[] arrayNew;
        
        int count = 0;
        for (int i = 0; i < array.length; i++)
        {
            if (array[i].compareTo(element) == 0)
            {
                count++;
            }
        }
        
        arrayNew = new String[array.length - count];
        
        int j = 0;
        for (int i = 0; i < array.length; i++)
        {
            if (!(array[i].compareTo(element) == 0))
            {
                arrayNew[j] = array[i];
                j++;
            }
        }
        
        return arrayNew;
    }
    
    /* 
     * reverse returns the reverse of a word
     */
    public String reverse(String word)
    {
        String reversedWord = "";
        
        for (int i = 0; i < word.length(); i++)
        {   
            reversedWord += word.charAt(word.length() - 1 - i);
        }
        
        return reversedWord;
    }
    
    /* 
     * printBoard prints a the word board in a legible and playable manner
     */
    public void printBoard()
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
    
    /* 
     * printWords prints a the words array in a legible manner
     */
    public void printWords()
    {
        System.out.println("Words:");
        for (int i = 0; i < words.length; i++)
        {
            System.out.print(words[i].toLowerCase() + " ");
        }
        System.out.println();
    }
    
    /* 
     * printFound prints a the words found by the user in a legible manner
     */
    public void printFound(String[] words, String[] words2)
    {   if (words.length != 0)
        {
            System.out.println();
            System.out.println("Words found:");
            for (int i = 0; i < words.length; i++)
            {
                System.out.print(words[i].toLowerCase() + " ");
            }
            System.out.println();
        }
        if (words2.length != 0)
        {
            System.out.println();
            System.out.println("Bonus words:");
            for (int i = 0; i < words2.length; i++)
            {
                System.out.print(words2[i].toLowerCase() + " ");
            }
            System.out.println();
        }
    }
    
    /* 
     * search checks if a word is present in an array
     */
    public boolean search(String word, String[] array)
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
    
    /* 
     * append appends a word to an array
     */
    public String[] append(String word, String[] array)
    {
        String[] appendedArray = new String[array.length + 1];
        
        for (int i = 0; i < array.length; i++)
        {
            appendedArray[i] = array[i];
        }
        
        appendedArray[array.length] = word;
        
        return appendedArray;
    }
    
    /* 
     * compare checks if all elements in array are present in array2 and vise-versa
     */
    public boolean compare(String[] array, String[] array2)
    {
        if(array.length != array2.length)
        {
            return false;
        }
        
        for (int i = 0; i < array.length; i++)
        {
            boolean there = false;
            
            for (int j = 0; j < array2.length; j++)
            {
                if (array[i].compareTo(array2[j]) == 0)
                {
                    there = true;
                }
            }
            
            if (!there)
            {
                return false;
            }
        }
        
        return true;
    }
}

package cardTrick;

import java.util.Scanner;

public class Trick
{
    Deck deck = new Deck();
    Card[][] cards = new Card[3][7];
    
    public Trick()
    {
        deck.shuffle();
        
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 7; j++)
            {
                cards[i][j] = deck.deck[(i*7) + j];
            }
        }
    }
    
    public void printCards()
    {
        System.out.println("[1]" + '\t' + "[2]" + '\t' + "[3]");
        System.out.println();
        for (int i = 0; i < 7; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                cards[j][i].printCard();
                System.out.print('\t');
            }
            System.out.println();
        }
    }
    
    public void collect(int col)
    {
        Card[] temp = cards[1];
        cards[1] = cards[col];
        cards[col] = temp;
    }
    
    public void redistribute()
    {
        Card[] linear = new Card[21];
        
        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 7; j++)
            {
                linear[(i*7) + j] = cards[i][j];
            }
        }
        
        for (int j = 0; j < 7; j++)
        {
            for (int i = 0; i < 3; i++)
            {
                cards[i][j] = linear[(j*3) + i];
            }
        }
    }
    
    public void userInter()
    {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Select a card from the collection");
        
        for (int i = 0; i < 3; i++)
        {
            printCards();
            System.out.println();
            
            System.out.println("What column in your card in: ");
            int col = sc.nextInt() - 1;
            
            collect(col);
            redistribute();
            
            System.out.println('\f');
        }
        
        System.out.println('\f');
        
        printCards();
        System.out.println();
        
        System.out.print("Your card is ");
        cards[1][3].printCard();
        System.out.println();
    }
    
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Hi\nYou want play trick?\n");
        char response = sc.next().charAt(0);
        System.out.println();
        
        if (response == 'n')
        {
            System.out.println(":(\n\nOki bye");
        }
        else 
        {
            char replay = 'y';
            
            while(replay == 'y')
            {
                Trick trick = new Trick();
                
                System.out.println('\f');
                
                trick.userInter();
                
                System.out.println();
                System.out.println("You want play trick agane?\n");
                replay = sc.next().charAt(0);
            }
            
            System.out.println();
            
            System.out.println(":)\n\nOki bye");
        }
    }
}

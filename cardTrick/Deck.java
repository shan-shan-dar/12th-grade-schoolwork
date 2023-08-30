package cardTrick;

public class Deck
{
    Card deck[] = new Card[52];

    public Deck()
    {
        initialize();
    }

    public void initialize()
    {
        char suit[] = {'♠', '♦', '♣', '♥'};
        String rank[] = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

        for (int i = 0; i < rank.length; i++)
        {
            for (int j = 0; j < suit.length; j++)
            {
                deck[i*4 + j] = new Card(rank[i], suit[j]);
            }
        }
    }

    public void shuffle()
    {
        for (int i = 0; i < 52; i++)
        {
            int unos = (int) (Math.random() * 1000 % 52);
            int dos = (int) (Math.random() * 1000 % 52);

            Card tres = deck[unos];
            deck[unos] = deck[dos];
            deck[dos] = tres;
        }
    }
    
    public Card[] shuffle2()
    {
        Card[] shuffled = new Card[52];
        
        for (int i = 0; i < 52; i++)
        {
            shuffled[i] = deck[i];
        }
        
        for (int i = 0; i < 52; i++)
        {
            int unos = (int) (Math.random() * 1000 % 52);
            int dos = (int) (Math.random() * 1000 % 52);

            Card tres = shuffled[unos];
            shuffled[unos] = shuffled[dos];
            shuffled[dos] = tres;
        }
        
        return shuffled;
    }

    public void printDeck()
    {
        for (int i = 0; i < deck.length; i++)
        {
            deck[i].printCard();
            System.out.print('\t');
            if (i % 4 == 3)
            {
                System.out.println();
            }
        }
    }
    
    public void printDeck(Card[] deck)
    {
        for (int i = 0; i < deck.length; i++)
        {
            deck[i].printCard();
            System.out.print('\t');
            if (i % 4 == 3)
            {
                System.out.println();
            }
        }
    }
}
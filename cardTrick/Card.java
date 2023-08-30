package cardTrick;

public class Card
{
     String rank;
     char suit;
     
     public Card(String r, char s)
     {
         rank = r;
         suit = s;
     }
     
     public void printCard()
     {
         System.out.print("" + rank + suit);
     }
}

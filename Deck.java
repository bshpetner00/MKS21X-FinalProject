import java.util.*;

public class Deck {
  public List<Card> deck = new ArrayList<Card>();
  
  public Deck() {
    for (int i = 1; i < 5; i++) {
      for (int i2 = 1; i2 < 14; i2++) {
        deck.add(new Card(i, i2));
      }
    }
  }
  
  public Card deal() {
    int i = (int) (Math.random() * 10) % (deck.size());
    return deck.remove(i);
  }
}
  /*public static void main(String[] args) {
    Deck w = new Deck();
    for (int i = 0; i < 52; i++) {
      System.out.println(w.deal());
    }
  }
}

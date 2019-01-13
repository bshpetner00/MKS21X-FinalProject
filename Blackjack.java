import java.util.*;

public class Blackjack extends Game{
  private Deck deck;
  public int total = 0;
  public List<Card> hand = new ArrayList<Card>();
  public int dTotal = 0;
  public List<Card> dHand = new ArrayList<Card>();
  
  public Blackjack(int x, int y) {
    super(x, y);
    deck = new Deck();
    hand.add(deck.deal()); total += hand.get(0).value;
    dHand.add(deck.deal()); dTotal += dHand.get(0).value;
    hand.add(deck.deal()); total += hand.get(1).value;
    dHand.add(deck.deal()); dTotal += dHand.get(1).value;
  }
  
  public void calculate() {
    if (total > 21) {winnings = 0;}
    else if (total > dTotal || dTotal > 21) {winnings = amountBet * 2;}
    else {winnings = amountBet;}
  }
  
  public void pDraw() {
    hand.add(deck.deal());
    total += hand.get(hand.size() - 1).value;
  }
  public void dDraw() {
    dHand.add(deck.deal());
    dTotal += dHand.get(dHand.size() - 1).value;
  }
}

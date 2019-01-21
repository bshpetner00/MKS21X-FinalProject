import java.util.*;

public class Blackjack extends Game{
  //instance variables are decks and hand totals
  private Deck deck;
  public int total = 0;
  public List<Card> hand = new ArrayList<Card>();
  public int dTotal = 0;
  public List<Card> dHand = new ArrayList<Card>();
  
  //for jacks, queens, and kings that have a value of 10
  private int vCalc(int i) {
    if (i < 11) {return i;}
    else {return 10;}
  }
  
  public Blackjack(int x, int y) {
    super(x, y);
    deck = new Deck();
    //each person draws two cards at the beginning of each game
    hand.add(deck.deal()); total += vCalc(hand.get(0).value);
    dHand.add(deck.deal()); dTotal += vCalc(dHand.get(0).value);
    hand.add(deck.deal()); total += vCalc(hand.get(1).value);
    dHand.add(deck.deal()); dTotal += vCalc(dHand.get(1).value);
  }
  
  //calculates who wins the game
  public void calculate() {
    if (total > 21 || ((dTotal > total ) && (dTotal <= 21))) {winnings = 0;}
    else if (((total > dTotal) && (total <= 21)) || ((dTotal > 21) && total <= 21)) {winnings = amountBet * 2;}
    else {winnings = amountBet;}
  }
  
  //player and dealer drawing methods
  public void pDraw() {
    hand.add(deck.deal());
    total += vCalc(hand.get(hand.size() - 1).value);
  }
  public void dDraw() {
    dHand.add(deck.deal());
    dTotal += vCalc(dHand.get(dHand.size() - 1).value);
  }
}

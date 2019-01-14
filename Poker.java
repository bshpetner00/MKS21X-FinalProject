import java.util.*;

public class Poker extends Game {
	private Deck deck;
	public int score = 0;
	public ArrayList<Card> hand = new ArrayList<Card>();
	public Poker(int x, int y) {
		super(x,y);
		deck = new Deck();
	}
	public void deal1() {
		for (int i = 0; i < 5; i++) {
			hand.add(deck.deal());	
		}
	}
	public void deal2(boolean one, boolean two, boolean three, boolean four, boolean five) {
		int numToDeal = 0;
		if (one) {
			hand.remove(hand.get(0));
			numToDeal++;
		}
		if (two) {
			hand.remove(hand.get(1));
			numToDeal++;
		}
		if (three) {
			hand.remove(hand.get(2));
			numToDeal++;
		}
		if (four) {
			hand.remove(hand.get(3));
			numToDeal++;
		}
		if (five) {
			hand.remove(hand.get(4));
			numToDeal++;
		}
		while (numToDeal > 0) {
			hand.add(deck.deal());
			numToDeal--;
		}
	}
	public int calculate(ArrayList<Card> cards) {
		Arraylist<Integer> values = new ArrayList<Integer>();
		for (Card c: cards) {
			values.add(c.value);
		} //creates a list of card values

		boolean flush = true;
		int suit = (cards.get(0)).symbol;
		for (Card k: cards) {
			if (k.symbol != suit) {
				flush = false;
			}
		} //checks for a flush

		boolean straight = false;
		int lowVal = values.get(0);
		for (int i = 1; i < values.size();i++) {
			if (values.get(i) < lowVal) {
				lowVal = values.get(i);
			}
		} //gets lowest value
		if(values.contains(lowVal) && values.contains(lowVal+1) && values.contains(lowVal+2) && values.contains(lowVal+3) && values.contains(lowVal+4)) {
			straight = true; //checks to see if all values are consecutive
		}

		boolean royalStraight = false;
		if (lowVal == 1 && values.contains(10) && values.contains(11) && values.contains(12) && values.contains(13)) {
			royalStraight = true;
		}

		if ()




	} 



}
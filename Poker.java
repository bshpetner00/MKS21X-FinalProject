import java.util.*;

public class Poker extends Game {
	private Deck deck;
	public ArrayList<Card> hand = new ArrayList<Card>();
	public boolean one,two,three,four,five; //if x is true then card x will be discarded and replaced on deal2 
	public Poker(int x, int y) {
		super(x,y);
		deck = new Deck();
		one = false;
		two = false;
		three = false;
		four = false;
		five = false;
	}
	public void deal1() { //5 brand spankin new cards
		for (int i = 0; i < 5; i++) {
			hand.add(deck.deal());
		}
	}
	public void deal2(boolean one, boolean two, boolean three, boolean four, boolean five) { //for each boolean that is true the corresponding card is replaced with a new one
		if (one) {
			hand.set(0, deck.deal());
		}
		if (two) {
			hand.set(1, deck.deal());
		}
		if (three) {
			hand.set(2, deck.deal());
		}
		if (four) {
			hand.set(3, deck.deal());
		}
		if (five) {
			hand.set(4, deck.deal());
		}
	}
	public void calculate(ArrayList<Card> cards) {
		ArrayList<Integer> values = new ArrayList<Integer>();
		for (int c = 0; c < cards.size(); c++) {
			values.add(cards.get(c).getValue());
		} //creates a list of card values
		Collections.sort(values);


		boolean flush = true;
		int suit = (cards.get(0)).symbol;
		for (int k = 0; k < cards.size(); k++) {
			if (cards.get(k).getSuit() != suit) {
				flush = false;
			}
		} //checks for a flush

		boolean straight = false;
		int lowVal = values.get(0);
		if(values.contains(lowVal) && values.contains(lowVal+1) && values.contains(lowVal+2) && values.contains(lowVal+3) && values.contains(lowVal+4)) {
			straight = true; //checks to see if all values are consecutive
		}

		boolean royalStraight = false;
		if (lowVal == 1 && values.contains(10) && values.contains(11) && values.contains(12) && values.contains(13)) {
			royalStraight = true; //for straight containing ace with 10/J/Q/K
		}

		if (royalStraight && flush) {
			winnings = amountBet * 800; //royal flush
		}

		if (straight && flush) {
			winnings = amountBet * 50; //straight flush
		}

		if ((values.get(0) == values.get(1) && values.get(1) == values.get(2) && values.get(2) == values.get(3)) || (values.get(1) == values.get(2) && values.get(2) == values.get(3) && values.get(3) == values.get(4))) {
			winnings = amountBet * 25; //4 of a kind
		}

		boolean tres = false;
		ArrayList<Integer> trace = values;
		int three = 0;
		int tresVal = 0;
		for (int t = 0; t < trace.size(); t++) {
			for (int j = t+1; j < trace.size(); j++) {
				if ((trace.get(t)).equals(trace.get(j))) {
					three++;
				}
				if (j == trace.size()-1 && three != 2) {
					three = 0;
				}
				else if (j == trace.size()-1 && three >= 2) {
					tres = true;
					tresVal = trace.get(2);
				}
			}
		} //3 of a kind

		boolean dos = false;
		if (tres) {
			trace.remove(tresVal);
			trace.remove(tresVal);
			trace.remove(tresVal);
			if ((trace.get(0)).equals(trace.get(1))) {
				dos = true;
			}
		} //checks for pair and triple

		if (tres && dos) {
			winnings = amountBet * 9;
		} //full house

		if (flush) {
			winnings = amountBet * 6;
		} //flush

		if (royalStraight || straight) {
			winnings = amountBet * 4;
		} //straight

		if (tres && !dos) {
			winnings = amountBet * 3;
		} //three of a kind

		if (!tres) {
			int pairCount = 0;
			for (int w = 0; w < values.size()-1; w++) {
				if ((values.get(w)).equals(values.get(w+1))) {
					pairCount++;
				}
			}
			if (pairCount >= 2) {
				winnings = amountBet * 2;
			} //two pair
			else {
				for (int l = 0; l < values.size()-1; l++) {
					if ((values.get(l)).equals(values.get(l+1)) && (values.get(l) >= 11 || values.get(l) == 1)) {
						winnings = amountBet; //pair of jacks or better
					}
				}
			}
		}
	}
}
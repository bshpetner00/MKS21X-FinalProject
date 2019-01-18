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
	public void calculate(ArrayList<Card> cards) {
		ArrayList<Integer> values = new ArrayList<Integer>();
		for (Card c: cards) {
			values.add(c.value);
		} //creates a list of card values
		Collections.sort(values);


		boolean flush = true;
		int suit = (cards.get(0)).symbol;
		for (Card k: cards) {
			if (k.symbol != suit) {
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
			royalStraight = true; //for ace then 10/J/Q/K
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
		}

		boolean dos = false;
		if (tres) {
			trace.remove(tresVal);
			trace.remove(tresVal);
			trace.remove(tresVal);
			if ((trace.get(0)).equals(trace.get(1))) {
				dos = true;
			}
		}

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

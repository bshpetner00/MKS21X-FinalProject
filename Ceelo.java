import java.util.*;

public class Ceelo extends Game {
	public ArrayList<Integer> playerDice,dealerDice; //arrays of 3 dice each
	public int playerScore,dealerScore;
	public boolean setMatch; //true if win, false if loss
	public Ceelo(int x, int y) {
		super(x,y);
		playerDice = new ArrayList<Integer>();
		dealerDice = new ArrayList<Integer>();
		(this.playerDice).add(0);
      	(this.playerDice).add(0);
     	(this.playerDice).add(0);
      	(this.dealerDice).add(0);
      	(this.dealerDice).add(0);
      	(this.dealerDice).add(0);
      	this.playerScore = 0;
      	this.dealerScore = 0;
	}
	public void roll(ArrayList<Integer> dice) {
		dice.clear();
		for (int i = 0; i < 3; i++) {
			dice.add((int) (Math.random() * 10) % 6 + 1); //number between 1 & 6
		}
		if (calcScore(dice) == 0) {
			roll(dice);
		}
	}
	public int calcScore(ArrayList<Integer> dice) {
		int score = 0;
		if (dice.contains(4) && dice.contains(5) && dice.contains(6)) { //win condition of ceelo
			score = 100;
		}
		else if (dice.get(0) == dice.get(1) && dice.get(1) == dice.get(2)) { //triples
			score = dice.get(0) * 7;
		}
		else if (dice.get(0) == dice.get(1) && dice.get(1) != dice.get(2)) { //normal hand
			score = dice.get(2);
		}
		else if (dice.get(0) == dice.get(2) && dice.get(1) != dice.get(2)) { //normal hand
			score = dice.get(1);
		}
		else if (dice.get(0) != dice.get(1) && dice.get(1) == dice.get(2)) { //normal hand
			score = dice.get(0);
		}
		else if (dice.contains(1) && dice.contains(2) && dice.contains(3)) { //normal hand
			score = -100;
		}
		return score;
	}
	public void setMatch(int pscore, int dscore) {
		if (dscore == 0) {
			if (pscore == 100) {
				this.setMatch = true;
			} 
			else if (pscore == -100) {
				this.setMatch = false;
			}
		}
		else {
			if (dscore == 100) {
				this.setMatch = false;
			}
			else if (dscore == -100) {
				this.setMatch = true;
			}
			if (pscore > dscore) {
				this.setMatch = true;
			}
			if (pscore < dscore) {
				this.setMatch = false;
			}
			if (pscore == dscore) {
				this.roll(this.playerDice);
				this.roll(this.dealerDice);
				this.playerScore = this.calcScore(this.playerDice);
				this.setMatch(this.playerScore,this.dealerScore);
				this.dealerScore = this.calcScore(this.dealerDice);
				this.setMatch(this.playerScore,this.dealerScore);
			}	
		}
		if (this.setMatch) {
			winnings = 2 * amountBet;
		}
		else {
			winnings = 0;
		}
	}
}
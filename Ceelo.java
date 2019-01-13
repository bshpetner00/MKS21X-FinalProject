import java.util.*;

public class Ceelo extends Game {
	public ArrayList<Integer> playerDice,dealerDice; //arrays of 3 dice each
	public int playerScore,dealerScore;
	public boolean setMatch; //true if win, false if loss
	public Ceelo(int x, int y) {
		super(x,y);
		playerDice = new ArrayList<Integer>();
		dealerDice = new ArrayList<Integer>();
	}
	public void roll(ArrayList<Integer> dice) {
		for (int i = 0; i < 3; i++) {
			dice.add((int) (Math.random() * 10) % 6 + 1); //number between 1 & 6
		}
		System.out.println(dice.toString());
	}
	public void calcScore(ArrayList<Integer> dice, int b) {
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
		if (b == 0) { //b is a binary that chooses whether to set the player's or dealer's score
			this.playerScore = score;
			System.out.println("Your score for this roll is " + score);
		}
		else if (b == 1) {
			this.dealerScore = score;
			System.out.println("The dealer's score for this roll is " + score);
		}
		if (score == 0 && b == 0) {
			(this.playerDice).clear();
			this.roll(this.playerDice);
			this.calcScore(this.playerDice,0);
		} 
		else if (score == 0 && b == 1) {
			(this.dealerDice).clear();
			this.roll(this.dealerDice);
			this.calcScore(this.dealerDice,1);
		}
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
				(this.playerDice).clear();
				(this.dealerDice).clear();
				this.roll(this.playerDice);
				this.calcScore(this.playerDice,0);
				this.roll(this.dealerDice);
				this.calcScore(this.dealerDice,1);
				this.setMatch(this.playerScore,this.dealerScore);
			}	
		}
		if (pscore != dscore && pscore != 0 && dscore != 0) {
			System.out.println("You win this round =" + this.setMatch);	
		}
	}
	public static void main(String[] args) {
		Ceelo game = new Ceelo();
		game.roll(game.playerDice);
		game.calcScore(game.playerDice,0);
		game.setMatch(game.playerScore,game.dealerScore);
		game.roll(game.dealerDice);
		game.calcScore(game.dealerDice,1);
		game.setMatch(game.playerScore,game.dealerScore);
	}
}
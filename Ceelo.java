import java.util.*;

public class Ceelo {
	public ArrayList<Integer> playerDice,dealerDice; //arrays of 3 dice each
	public int playerScore,dealderScore;
	public boolean setMatch; //true if win, false if loss
	public Ceelo() {
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
			score = dice.get(0) * 3;
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
			System.out.println("Your score for this round is " + score);
		}
		else if (b == 1) {
			this.dealderScore = score;
			System.out.println("The dealer's score for this round is " + score);
		}

	}
	public boolean setMatch(pscore,dscore) {
		
	}
	public static void main(String[] args) {
		Ceelo game = new Ceelo();
		game.roll(game.playerDice);
		game.calcScore(game.playerDice,0);
		game.roll(game.dealerDice);
		game.calcScore(game.dealerDice,1);
	}
}

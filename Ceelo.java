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
			dice.add((int) (Math.random() * 10) % 6 + 1);
		}
		System.out.println(dice.toString() + '\n');
	}
	public int calcScore(ArrayList<Integer> dice, int b) {
		int score = 0;
		if (dice.contains(4) && dice.contains(5) && dice.contains(6)) {
			score = 100;
		}
		else if (dice.get(0) == dice.get(1) && dice.get(1) == dice.get(2)) {
			score = dice.get(0) * 3;
		}
		else if (dice.get(0) == dice.get(1) && dice.get(1) != dice.get(2)) {
			score = dice.get(2);
		}
		else if (dice.get(0) == dice.get(2) && dice.get(1) != dice.get(2)) {
			score = dice.get(1);
		}
		else if (dice.get(0) != dice.get(1) && dice.get(1) == dice.get(2)) {
			score = dice.get(0);
		}
		else if (dice.contains(1) && dice.contains(2) && dice.contains(3)) {
			score = -100;
		}
		if (b == 0) {
			this.playerScore = score;
		}
		else if (b == 1) {
			this.dealderScore = score;
		}
	}
	public static void main(String[] args) {
		Ceelo game = new Ceelo();
		game.roll(game.playerDice);
		game.roll(game.dealerDice);
	}
}

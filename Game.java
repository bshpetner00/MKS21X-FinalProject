//Game scaffold class for all games: x + y positions, the bet value, and amount of winnings to be added to currency.
public class Game {
  public int amountBet;
  public int posX, posY;
  public int winnings = 0;

  public Game() {};
  public Game(int x, int y) {
    posX = x;
    posY = y;
  }
}

public class Game {
  public int amountBet;
  private int posX, posY;
  public int winnings = 0;
  
  public Game(int x, int y) {
    posX = x;
    posY = y;
  }
  public void bet(int v) {
    amountBet = v;
  }
}
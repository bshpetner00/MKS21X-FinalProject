public class Roulette extends Game {
  public Box[] board = new Box[38];
  public Box winner;
  public int totalBet = 0;
  
  public Roulette(int x, int y) {
    super(x, y);
    int xC = 0; int yC = 5; int v = -1;
    for (int i = 0; i < 38; i++) {
      xC = 3 * i + 1;
      board[i] = new Box(xC, yC, v);       
      v++;
    }
    winner = board[0];
  }
  
  public Box roll() {
    winner = board[(int) (Math.random() * 100) % 38];
    return winner;
  }
  public void calculate() {
    winnings = winner.boxBet * 2;
  }
  public void betUp(Box b) {
    b.selectUp();
    totalBet += 5;
  }
  public void betDown(Box b) {
    b.selectDown();
    totalBet -= 5;
  }
}

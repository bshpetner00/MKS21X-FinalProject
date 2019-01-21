public class Roulette extends Game {
  public Box[] board = new Box[38];
  public Box winner;
  
  public Roulette(int x, int y) {
    super(x, y);
    int xC = 0; int yC = 0; int v = -1;
    for (int i = 0; i < 38; i++) {
      board[i] = new Box(xC, yC, v);       
      xC++; yC++; v++;
    }
    winner = board[0];
  }
  
  public Box roll() {
    winner = board[((int) Math.random() * 100) % 38];
    return winner;
  }
  public void calculate() {
    winnings = winner.boxBet * 2;
  }
}

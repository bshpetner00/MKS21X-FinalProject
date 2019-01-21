public class Roulette extends Game {
  public Box[] board = new Box[38];
  public Box winner;
  public int totalBet = 0;
  public int greenBet = 0;
  public int blackBet = 0;
  public int redBet = 0;
  
  public Roulette(int x, int y) {
    super(x, y);
    int xC = 0; int yC = 5; int v = -1;
    for (int i = 0; i < 38; i++) {
      xC = 4 * i + 1;
      board[i] = new Box(xC, yC, v);
      if ((v > 0 && v <= 10) || (v > 18 && v <= 28)) {
        if (v % 2 == 0) {
          board[i].setColor(1);
        }
        else {board[i].setColor(2);}
      }
      else if ((v > 10 && v <= 18) || (v > 28 && v <= 36)) {
        if (v % 2 == 0) {
          board[i].setColor(2);
        }
        else {board[i].setColor(1);}
      }     
      v++;
    }
    winner = board[0];
  }
  
  public Box roll() {
    winner = board[(int) (Math.random() * 100) % 38];
    return winner;
  }
  public void calculate() {
    int wink = winner.color;
    if (wink == 0) {
      winnings += greenBet * 10; 
    }
    else if (wink == 1) {
      winnings += blackBet * 2;
    }
    else {
      winnings += redBet * 2;
    }
    if (totalBet == winner.boxBet) {
      winnings += winner.boxBet * 35;
    }
    else {
      winnings += winner.boxBet * 2;
    }
  }
  public void colorBetUp(int c) {
    c += 5;
    totalBet += 5;
  }
  public void colorBetDown(int c) {
    c -= 5;
    totalBet -= 5;
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

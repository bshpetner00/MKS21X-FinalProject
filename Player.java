public class Player{
  public int currency;
  public Player(int v) {
    currency = v;
  }
  public int bet(int a, Game g) {
    if (a > currency || a <= 0) {return -1;}
    currency -= a;
    g.amountBet += a;
    return g.amountBet;
  }
  public void set(int n) {
    currency = n;
  }
}

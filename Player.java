public class Player{
  public int currency;
  public Player(int v) {
    currency = v;
  }
  //betting happens here - subtracts from currency and adds to target game
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

public class Box {
  public int xCoor;
  public int yCoor;
  public int value;
  public boolean current = false;
  public int boxBet = 0;
  
  public Box(int x, int y) {
    xCoor = x; yCoor = y;
  }
  
  public void set(int v) {
    value = v;
  }
}

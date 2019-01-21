public class Box {
  public int xCoor;
  public int yCoor;
  public int value;
  public boolean current = false;
  public int boxBet = 0;
  
  public Box(int x, int y, int v) {
    xCoor = x; yCoor = y;
    value = v;
  }
  
  public void set(int v) {
    value = v;
  }
  public String toString() {
    if (value == -1) {
      return "00";
    }
    return "" + value;
  }
}

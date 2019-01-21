public class Box {
  public int xCoor;
  public int yCoor;
  public int value;
  public int color;
  public int boxBet = 0;
  
  //constructor sets x- and y-coordinates, value, and color
  public Box(int x, int y, int v) {
    xCoor = x; yCoor = y;
    value = v;
    color = 0;
  }

  public void setColor(int cola) {
    color = cola;
  }
  
  //for betting individual boxes
  public void selectUp() {
    boxBet += 5;
  }
  public void selectDown() {
    if (boxBet > 0) {boxBet -= 5;}
  }
  
  //for display on the game screen
  public String toString() {
    String result = "";
    if (value == -1) {
      result = "00";
    }
    else {
      result = result + value;
    }
    if (color == 0) {
      result += "G";
    }
    else if (color == 1) {
      result += "B";
    }
    else {
      result += "R";
    }
    return result;
  }
}

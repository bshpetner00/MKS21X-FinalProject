public class Card {
  public int symbol;
  public int value;
  
  public Card(int s, int v) {
    symbol = s;
    value = v;
  }
  
  public String toString() {
    String s = "";
    if (symbol == 1) {s += "Diamonds ";}
    else if (symbol == 2) {s += "Clubs ";}
    else if (symbol == 3) {s += "Hearts ";}
    else if (symbol == 4) {s += "Spades ";}
    if (value == 1) {s += "A";}
    else if (value == 11) {s += "J";}
    else if (value == 12) {s += "Q";}
    else if (value == 13) {s += "K";}
    else {s += value;}
    return s;
  }
}

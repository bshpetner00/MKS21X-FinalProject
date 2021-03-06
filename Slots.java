public class Slots extends Game{
  public int slot1, slot2, slot3;

  public Slots() {};
  //constructor sets dummy values to be displayed
  public Slots(int x, int y) {
    super(x, y);
    slot1 = ((int) (Math.random() * 10)) % 5 + 1;
    slot2 = ((int) (Math.random() * 10)) % 5 + 1;
    slot3 = ((int) (Math.random() * 10)) % 5 + 1;
  }
  
  //calculates winnings: 3-of-a-kind is given a positive winning
  public void calculate() {
    if (slot1 == slot2 && slot2 == slot3) {
      winnings = amountBet * slot1;
    }
    else {
      winnings = 0;
    }
    amountBet = 0;
  }
  //"spins" the slots
  public void spin() {
    slot1 = ((int) (Math.random() * 10)) % 5 + 1;
    slot2 = ((int) (Math.random() * 10)) % 5 + 1;
    slot3 = ((int) (Math.random() * 10)) % 5 + 1;
  }
}

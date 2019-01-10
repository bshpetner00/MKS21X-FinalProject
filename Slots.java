public class Slots extends Game{
  public int slot1, slot2, slot3;

  public Slots() {};
  public Slots(int x, int y) {
    super(x, y);
    slot1 = ((int) (Math.random() * 10)) % 5 + 1;
    slot2 = ((int) (Math.random() * 10)) % 5 + 1;
    slot3 = ((int) (Math.random() * 10)) % 5 + 1;
  }
  public void calculate() {
    if (slot1 == slot2 && slot2 == slot3) {
      winnings = amountBet * slot1;
    }
    else {
      winnings = 0;
    }
  }
  public void spin() {
    slot1 = ((int) (Math.random() * 10)) % 5 + 1;
    slot2 = ((int) (Math.random() * 10)) % 5 + 1;
    slot3 = ((int) (Math.random() * 10)) % 5 + 1;
  }
}

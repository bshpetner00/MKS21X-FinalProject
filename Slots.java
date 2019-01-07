public class Slots extends Game{
  public int slot1, slot2, slot3;
  public Slots() {
    posX = 15;posY = 15;
    slot1 = (Math.random() * 10) % 5 + 1;
    slot2 = (Math.random() * 10) % 5 + 1;
    slot3 = (Math.random() * 10) % 5 + 1;
  }
  public void calculate() {
    if (slot1 == slot2 && slot2 == slot3) {
      winnings = amountBet * slot1;
    }
    else {
      winnings = 0;
    }
  }
  public void spin(int val) {
    slot1 = (Math.random() * 10) % 5 + 1;
    slot2 = (Math.random() * 10) % 5 + 1;
    slot3 = (Math.random() * 10) % 5 + 1;
  }
}

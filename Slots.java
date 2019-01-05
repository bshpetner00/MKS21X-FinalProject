public class Slots {
  public int slot1, slot2, slot3;
  public Slots() {
    slot1 = (Math.random() * 10) % 6;
    slot2 = (Math.random() * 10) % 6;
    slot3 = (Math.random() * 10) % 6;
  }
  public int calculate() {
    if (slot1 == slot2 && slot2 == slot3) {
      return slot1;
    }
    else {
      return 0;
    }
  }
  public void spin(int val) {
    slot1 = (Math.random() * 10) % 6;
    slot2 = (Math.random() * 10) % 6;
    slot3 = (Math.random() * 10) % 6;
  }
}

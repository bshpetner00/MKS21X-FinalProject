import com.googlecode.lanterna.*;
import com.googlecode.lanterna.input.*;
import com.googlecode.lanterna.terminal.*;
import com.googlecode.lanterna.screen.*;
import java.io.IOException;
import java.awt.Color;
import java.util.*;

public class Casino {
  public static void putString(int x, int y, Screen screen, String str) {
    for (int i = 0; i < str.length(); ++i) {
      screen.setCharacter(x+i, y, new TextCharacter(str.charAt(i)));
    }
  }
  public static void main(String[] args) throws IOException {
    int x = 10; int y = 10;
    Player p = new Player(1000);
    Screen screen = new DefaultTerminalFactory().createScreen();
		screen.startScreen();
    screen.clear(); screen.refresh();
    while (true) {
      KeyStroke key = screen.pollInput();
        screen.setCharacter(x, y, new TextCharacter('@', TextColor.ANSI.WHITE, TextColor.ANSI.BLACK));

      putString(1, 2, screen, "Money: $" + p.currency);

      Slots slots = new Slots(15, 15);
      screen.setCharacter(slots.posX, slots.posY, new TextCharacter('7', TextColor.ANSI.WHITE, TextColor.ANSI.BLACK));
      Blackjack bj = new Blackjack(10, 15);
      screen.setCharacter(bj.posX, bj.posY, new TextCharacter('B', TextColor.ANSI.WHITE, TextColor.ANSI.BLACK));

      if (key != null) {
        screen.setCharacter(x, y, new TextCharacter(' '));
        if (key.getKeyType() == KeyType.Escape) {break;}
	else if (key.getKeyType() == KeyType.ArrowLeft) {x--;}
	else if (key.getKeyType() == KeyType.ArrowRight) {x++;}
	else if (key.getKeyType() == KeyType.ArrowUp) {y--;}
	else if (key.getKeyType() == KeyType.ArrowDown) {y++;}
	screen.clear();
        putString(1, 1, screen, key + "");
      }

      if (x == 15 && y == 15) {
        int bet = 5;
        screen.clear();
        while (true) {
          KeyStroke gKey = screen.pollInput();
          putString(1, 1, screen, "Slots: " + slots.slot1 + " " + slots.slot2 + " " + slots.slot3);
          putString(1, 2, screen, "Money Left: $" + p.currency);
          putString(1, 3, screen, "Current Bet: $" + bet);
          if (gKey != null) {
            if (gKey.getKeyType() == KeyType.Escape) {
              x++; screen.clear();
              break;}
            else if (gKey.getKeyType() == KeyType.Tab) {
              if (p.bet(bet, slots) == -1) {
                putString(1, 10, screen, "INVALID BET");
              }
              else {
                slots.spin(); slots.calculate();
                p.set(p.currency + slots.winnings);
              }
            }
            else if (gKey.getKeyType() == KeyType.ArrowUp) {bet = bet + 5;}
            else if (gKey.getKeyType() == KeyType.ArrowDown && bet - 5 > 0) {bet = bet - 5;}
            screen.clear();
            //putString(1, 4, screen, gKey + "");
          }
          screen.doResizeIfNecessary();
          screen.refresh();
        }
      }
      
      if (x == 10 & y == 15) {
        int bet = 5;
        screen.clear();
        while (true) {
          KeyStroke gKey = screen.pollInput();
          putString(1, 0, screen, "Money Left: $" + p.currency);
          putString(1, 1, screen, "Current Bet: $" + bet);
          putString(1, 3, screen, "Your Cards: " + bj.hand);
          putString(1, 5, screen, "Dealer's Cards: X, " + bj.dHand.subList(0, bj.dHand.size() - 1));
          putString(1, 7, screen, "Your Total: " + bj.total);
          if (gKey != null) {
            if (gKey.getKeyType() == KeyType.Escape) {
              x++; screen.clear();
              break;}
            else if (gKey.getKeyType() == KeyType.Tab) {
                bj.pDraw();
            }
            else if (gKey.getKeyType() == KeyType.Enter) {
              if (p.bet(bet, bj) == -1) {
                putString(1, 10, screen, "INVALID BET");
              }
              else {
                boolean play = true;
                while (play) {
                  if (bj.dTotal == 21) {play = false;}
                  if (bj.dTotal < 15) {bj.dDraw();}
                  else {
                    if (((int) (Math.random() * 10)) % 2 == 0) {bj.dDraw();}
                    else {play = false;}
                  }
                }
                bj.calculate();
                p.set(p.currency + bj.winnings);
                bj = new Blackjack(10, 15);
              }
            }
            else if (gKey.getKeyType() == KeyType.ArrowUp) {bet = bet + 5;}
            else if (gKey.getKeyType() == KeyType.ArrowDown && bet - 5 > 0) {bet = bet - 5;}
            screen.clear();
          }
          screen.doResizeIfNecessary();
          screen.refresh();
        }
      }

      screen.doResizeIfNecessary();
      screen.refresh();
    }
    screen.stopScreen();
  }
}

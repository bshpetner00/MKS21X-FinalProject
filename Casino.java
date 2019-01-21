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
      putString(1, 4, screen, "Money: $" + p.currency);

      Slots slots = new Slots(15, 15);
      screen.setCharacter(slots.posX, slots.posY, new TextCharacter('7', TextColor.ANSI.WHITE, TextColor.ANSI.BLACK));
      Blackjack bj = new Blackjack(10, 15);
      screen.setCharacter(bj.posX, bj.posY, new TextCharacter('B', TextColor.ANSI.WHITE, TextColor.ANSI.BLACK));
      Ceelo ceelo = new Ceelo(5,15);
      screen.setCharacter(ceelo.posX,ceelo.posY, new TextCharacter('C', TextColor.ANSI.WHITE, TextColor.ANSI.BLACK));
      Roulette rt = new Roulette(25, 15);
      screen.setCharacter(rt.posX, rt.posY, new TextCharacter('R', TextColor.ANSI.WHITE, TextColor.ANSI.BLACK));

      if (key != null) {
        screen.setCharacter(x, y, new TextCharacter(' '));
        if (key.getKeyType() == KeyType.Escape) {break;}
	       else if (key.getKeyType() == KeyType.ArrowLeft) {x--;}
	       else if (key.getKeyType() == KeyType.ArrowRight) {x++;}
	       else if (key.getKeyType() == KeyType.ArrowUp) {y--;}
	       else if (key.getKeyType() == KeyType.ArrowDown) {y++;}
	       screen.clear();
         //putString(1, 1, screen, key + "");
      }

      if (x == 15 && y == 15) {
        int bet = 5; boolean rule = false;
        screen.clear();
        while (true) {
          KeyStroke gKey = screen.pollInput();
          if (rule) {
            putString(1, 1, screen, "---RULES FOR SLOTS---");
    	    putString(1, 3, screen, "- Place your bet to put into the machine.");
    	    putString(1, 4, screen, "- Spin the slots!");
    	    putString(1, 5, screen, "- When you get a triple of any number, you get back your bet multiplied by the number you got.");
    	    putString(1, 10, screen, "Press Home to go back, or Escape if these rules are too complicated.");
    	    if (gKey != null) {
              if (gKey.getKeyType() == KeyType.Escape) {
                x++; screen.clear();
                break;}
              else if (gKey.getKeyType() == KeyType.Home) {
                rule = false; screen.clear();
              }
            }
          }
          else {
            putString(1, 1, screen, "Slots: " + slots.slot1 + " " + slots.slot2 + " " + slots.slot3);
            putString(1, 2, screen, "Money Left: $" + p.currency);
            putString(1, 3, screen, "Current Bet: $" + bet);
            putString(1, 15, screen, "Press Up or Down to bet higher or lower");
            putString(1, 16, screen, "Press Tab to spin");
            putString(1, 17, screen, "Press Home to view the rules");
            putString(1, 18, screen, "Press Escape to leave the game.");
            if (gKey != null) {
              if (gKey.getKeyType() == KeyType.Escape) {
                x++; screen.clear();
                break;}
              else if (gKey.getKeyType() == KeyType.Home) {
                rule = true;
              }
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
          }
          screen.doResizeIfNecessary();
          screen.refresh();
        }
      }

      if (x == 10 & y == 15) {
        int bet = 5; boolean endG = false; boolean rule = false;
        screen.clear();
        while (true) {
          KeyStroke gKey = screen.pollInput();
          if (rule) {
            putString(1, 1, screen, "---RULES FOR BLACKJACK---");
    	    putString(1, 3, screen, "- Place your bet to give to the dealer.");
    	    putString(1, 4, screen, "- Decide whether or not you want to 'hit' - draw a card.");
    	    putString(1, 5, screen, "- Your goal is to get higher than the dealer OR a total of 21.");
    	    putString(1, 6, screen, "- You draw all your cards first until you are satisfied with your hand.");
    	    putString(1, 7, screen, "- You get double your bet back when you win.");
          putString(1, 8, screen, "- Aces = 1, 2 = 2, 3 = 3, ...");
          putString(1, 9, screen, "- Jacks, Queens, and Kings are worth 10.");
    	    putString(1, 15, screen, "Press Home to go back.");
    	    if (gKey != null) {
              if (gKey.getKeyType() == KeyType.Escape) {
                x++; screen.clear();
                break;}
              else if (gKey.getKeyType() == KeyType.Home) {
                rule = false; screen.clear();
              }
            }
          }
          else {
            putString(1, 0, screen, "Money Left: $" + p.currency);
            putString(1, 1, screen, "Current Bet: $" + bet);
            putString(1, 3, screen, "Your Cards: " + bj.hand);
            if (endG) {
              putString(1, 8, screen, "Dealer's Total: " + bj.dTotal);
              putString(1, 5, screen, "Dealer's Cards: " + bj.dHand);
            }
            else {
              putString(1, 5, screen, "Dealer's Cards: X, " + bj.dHand.subList(0, bj.dHand.size() - 1));
            }
            putString(1, 7, screen, "Your Total: " + bj.total);
            putString(1, 15, screen, "Press Up or Down to bet higher or lower");
            putString(1, 16, screen, "Press Tab to hit, press Enter to play");
            putString(1, 17, screen, "Press Enter after a game to reset the game");
            putString(1, 18, screen, "Press Home to view rules");
            putString(1, 19, screen, "Press Escape to leave the game");
            if (gKey != null) {
              if (gKey.getKeyType() == KeyType.Escape) {
                x++; screen.clear();
                break;}
              else if (gKey.getKeyType() == KeyType.Home) {
                rule = true;
              }
              else if (gKey.getKeyType() == KeyType.Tab && bj.total < 21 && !endG) {
                  bj.pDraw();
              }
              else if (gKey.getKeyType() == KeyType.Enter) {
                if (!endG) {
                  if (p.bet(bet, bj) == -1) {
                    putString(1, 10, screen, "INVALID BET");
                  }
                  else {
                    boolean play = true;
                    while (play) {
                      if (bj.dTotal >= 21) {play = false;}
                      if (bj.dTotal < 17) {bj.dDraw();}
                      else {play = false;}
                    }
                    bj.calculate();
                    p.set(p.currency + bj.winnings);
                    endG = true;
                  }
              }
                else {
                  bj = new Blackjack(10, 15);
                  endG = false;
                }
              }
              else if (gKey.getKeyType() == KeyType.ArrowUp) {bet = bet + 5;}
              else if (gKey.getKeyType() == KeyType.ArrowDown && bet - 5 > 0) {bet = bet - 5;}
              screen.clear();
            }
          }
          screen.doResizeIfNecessary();
          screen.refresh();
        }
      }

    if (x == 5 && y == 15) {
      int bet = 5;
      screen.clear();
      while (true) {
          KeyStroke gKey = screen.pollInput();
          putString(1, 0, screen, "Money Left: $" + p.currency);
          putString(1, 1, screen, "Current Bet: $" + bet);
          putString(1, 3, screen, "Your Roll: " + ceelo.playerDice);
          putString(1, 4, screen, "Your Score For This Round: " + ceelo.playerScore);
          putString(1, 6, screen, "Dealer's Roll" + ceelo.dealerDice);
          putString(1, 7, screen, "Dealer's Score For This Round: " + ceelo.dealerScore);
          if (gKey != null) {
            if (gKey.getKeyType() == KeyType.Escape) {
              x++; screen.clear();
              break;}
            else if (gKey.getKeyType() == KeyType.Tab) {
              if (p.bet(bet, ceelo) == -1) {
                putString(1, 10, screen, "INVALID BET");
              }
              else {
                ceelo.roll(ceelo.playerDice);
                ceelo.roll(ceelo.dealerDice);
                ceelo.playerScore = ceelo.calcScore(ceelo.playerDice);
                ceelo.setMatch(ceelo.playerScore,ceelo.dealerScore);
                ceelo.dealerScore = ceelo.calcScore(ceelo.dealerDice);
                ceelo.setMatch(ceelo.playerScore,ceelo.dealerScore);
                p.set(p.currency + ceelo.winnings);
              }
            }
            else if (gKey.getKeyType() == KeyType.Enter) {
              ceelo = new Ceelo(5,15);
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

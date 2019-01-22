import com.googlecode.lanterna.*;
import com.googlecode.lanterna.input.*;
import com.googlecode.lanterna.terminal.*;
import com.googlecode.lanterna.screen.*;
import com.googlecode.lanterna.TextColor.*;
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
      putString(1, 1, screen, "WELCOME TO BIG GUAP CASINO --- LET'S GET THIS BREAD!");
      putString(1, 2, screen, "Money: $" + p.currency);
      putString(1, 20, screen, "Use the arrow keys to navigate towards different game tables!");

      Slots slots = new Slots(34, 9);
      screen.setCharacter(slots.posX, slots.posY, new TextCharacter('*', TextColor.ANSI.WHITE, TextColor.ANSI.BLACK));
      putString(32,10,screen,"SLOTS");
      Blackjack bj = new Blackjack(25, 15);
      screen.setCharacter(bj.posX, bj.posY, new TextCharacter('*', TextColor.ANSI.WHITE, TextColor.ANSI.BLACK));
      putString(21,16,screen,"BLACKJACK");
      Ceelo ceelo = new Ceelo(16,9);
      screen.setCharacter(ceelo.posX,ceelo.posY, new TextCharacter('*', TextColor.ANSI.WHITE, TextColor.ANSI.BLACK));
      putString(14,10,screen,"CEELO");
      Poker poker = new Poker(6,15);
      screen.setCharacter(poker.posX,poker.posY, new TextCharacter('*', TextColor.ANSI.WHITE, TextColor.ANSI.BLACK));
      putString(1,16,screen,"VIDEO POKER");
      Roulette rt = new Roulette(43, 15);
      screen.setCharacter(rt.posX, rt.posY, new TextCharacter('*', TextColor.ANSI.WHITE, TextColor.ANSI.BLACK));
      putString(39,16,screen,"ROULETTE");

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

      if (x == 34 && y == 9) {
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

      if (x == 25 & y == 15) {
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

    if (x == 16 && y == 9) {
      int bet = 5; boolean rule = false;
      screen.clear();
      while (true) {
          KeyStroke gKey = screen.pollInput();
          putString(1, 0, screen, "Money Left: $" + p.currency);
          putString(1, 1, screen, "Current Bet: $" + bet);
          putString(1, 3, screen, "Your Roll: " + ceelo.playerDice);
          putString(1, 4, screen, "Your Score For This Round: " + ceelo.playerScore);
          putString(1, 6, screen, "Dealer's Roll" + ceelo.dealerDice);
          putString(1, 7, screen, "Dealer's Score For This Round: " + ceelo.dealerScore);
          putString(1, 15, screen, "Press Tab to roll the dice");
          putString(1, 16, screen, "Press Enter to reset the table for another round.");
          putString(1, 17, screen, "Press Home to view the rules");
          putString(1, 18, screen, "Press Escape to leave the game.");
          if (rule) {
            putString(1, 1, screen, "---RULES FOR CEELO---");
            putString(1, 3, screen, "-You and the dealer each get 3 dice");
            putString(1, 4, screen, "-Press tab to roll and have the dealer roll");
            putString(1, 5, screen, "-If you roll a 4-5-6. you automatically win. 1-2-3 you automatically lose");
            putString(1, 6, screen, "-Otherwise, score is calculated by the roll having 2 of the same number and one different. The one different number is the score.");
            putString(1, 7, screen, "-If triples are rolled, they can only be beaten by 4-5-6 (obviously) or triples of a higher number.");
            putString(1, 8, screen, "-When you press tab, the game will keep rolling for you and the dealer until you both get a different score. Highest score wins! ");
            putString(1, 10, screen, "-Press Home to go back, or Escape to leave ceelo.");
            if (gKey != null) {
              if (gKey.getKeyType() == KeyType.Escape) {
                x++; screen.clear();
                break;}
              else if (gKey.getKeyType() == KeyType.Home) {
                rule = false; screen.clear();
                break;}
            }
          }
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
                ceelo.winnings = 0;
              }
            }
            else if (gKey.getKeyType() == KeyType.Enter) {
              ceelo = new Ceelo(5,15);
            }
            else if (gKey.getKeyType() ==  KeyType.Home) {
              rule = true;
            }
            else if (gKey.getKeyType() == KeyType.ArrowUp) {bet = bet + 5;}
            else if (gKey.getKeyType() == KeyType.ArrowDown && bet - 5 > 0) {bet = bet - 5;}
            screen.clear();
          }
          screen.doResizeIfNecessary();
          screen.refresh();
        }
    }

    if (x == 6 && y == 15) {
        int bet = 5; boolean rule = false; boolean dealt = false;
        screen.clear();
        while (true) {
          KeyStroke gKey = screen.pollInput();
          if (rule) {
            putString(1, 1, screen, "---RULES FOR POKER---");
          putString(1, 3, screen, "-You initially get 5 cards, and you may select which you want to discard and have replaced by pressing F1-F5");
          putString(1, 4, screen, "-You will then recieve your new cards and the value of your hand/potential winnings will be calculated and disbursed respectively");
          putString(1, 10, screen, "Press Home to go back to the game, or Escape to go back to the Casino");
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
            putString(1, 2, screen, "Your Cards: " + poker.hand);
            putString(1, 15, screen, "Press Up or Down to bet higher or lower");
            putString(1, 16, screen, "Press Tab to place a bet and recieve your starting hand");
            putString(1, 17, screen, "Press F1-F5 to select cards which you'd like to discard");
            putString(1, 18, screen, "Press Enter to conclude the round, and Tab again to begin the next.");
            putString(1, 19, screen, "Press Home to view rules & Press Escape to leave the game");
            if (gKey != null) {
              if (gKey.getKeyType() == KeyType.Escape) {
                x++; screen.clear();
                break;}
              else if (gKey.getKeyType() == KeyType.Home) {
                rule = true;
              }
              else if (gKey.getKeyType() == KeyType.Tab) {
                if (p.bet(bet, poker) == -1) {
                  putString(1, 10, screen, "INVALID BET");
                }
                else {
                  poker.hand.clear();
                  
                  poker.deal1();
                }
              }
              else if (gKey.getKeyType() == KeyType.F1 && (poker.hand).size() > 0) {
                poker.one = true;
              }
              else if (gKey.getKeyType() == KeyType.F2 && (poker.hand).size() > 0) {
                poker.two = true;
              }
              else if (gKey.getKeyType() == KeyType.F3 && (poker.hand).size() > 0) {
                poker.three = true;
              }
              else if (gKey.getKeyType() == KeyType.F4 && (poker.hand).size() > 0) {
                poker.four = true;
              }
              else if (gKey.getKeyType() == KeyType.F5 && (poker.hand).size() > 0) {
                poker.five = true;  
              }
              else if (gKey.getKeyType() == KeyType.Enter && (poker.hand).size() > 0) {
                poker.deal2(poker.one,poker.two,poker.three,poker.four,poker.five);
                poker.calculate(poker.hand);
                p.set(p.currency + poker.winnings);
                poker.winnings = 0;
                poker.one = false;
                poker.two = false;
                poker.three = false;
                poker.four = false;
                poker.five = false;

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
      
      if (x == 43 && y == 15) {
        screen.clear();
        int xG = 0; boolean rule = false; boolean endG = false;
        while (true) {
          KeyStroke gKey = screen.pollInput();
          if (rule) {
            putString(1, 1, screen, "---RULES FOR ROULETTE---");
          putString(1, 2, screen, "-You may bet on as many numbers as you like, as well as specific colors.");
          putString(1, 3, screen, "-Press enter to spin the wheel. Successful black/red bets pay out at 1:1, green bets at 10:1");
          putString(1, 1, screen, "-Successful number bets pay out at 2:1, unless the winner is your sole number choice in which case you recieve 35x your bet. ");
          putString(1, 10, screen, "Press Home to go back to the game, or Escape to go back to the Casino");
          if (gKey != null) {
              if (gKey.getKeyType() == KeyType.Escape) {
                x++; screen.clear();
                break;}
              else if (gKey.getKeyType() == KeyType.Home) {
                rule = false; screen.clear();
              }
            }
          }
          putString(1, 0, screen, "Money Left: $" + p.currency);
          putString(1, 1, screen, "(F1/F2)Green Bet: $" + rt.greenBet);
          putString(1, 2, screen, "(F3/F4)Black Bet: $" + rt.blackBet);
          putString(1, 3, screen, "(F5/F6)Red Bet: $" + rt.redBet);
          putString(1, 4, screen, "Total Bet: $" + rt.totalBet);
          putString(1, 5, screen, "Press HOME for rules, or enter to spin the wheel.");
          for (int i = 0; i < rt.board.length; i++) {
            putString(rt.board[i].xCoor, 7, screen, "" + rt.board[i]);
            putString(rt.board[i].xCoor, 9, screen, "" + rt.board[i].boxBet);
          }
          putString(4 * xG + 1, 8, screen, "^");
          if (endG) {
            putString(1, 9, screen, "Wheel rolled a: " + rt.winner);
          }
          if (gKey != null) {
            if (gKey.getKeyType() == KeyType.Escape) {
              x++; screen.clear();
              break;}
            else if (gKey.getKeyType() == KeyType.Home) {
              rule = true;
              screen.clear();
            }
            else if (gKey.getKeyType() == KeyType.ArrowLeft && xG != 0) {xG--;}
            else if (gKey.getKeyType() == KeyType.ArrowRight && xG != 37) {xG++;}
            else if (gKey.getKeyType() == KeyType.ArrowUp && !endG) {
              if (rt.totalBet + 5 <= p.currency) {
                rt.betUp(rt.board[xG]);
              }
            }
            else if (gKey.getKeyType() == KeyType.ArrowDown && rt.totalBet > 0 && !endG) {
              rt.betDown(rt.board[xG]);
            }
            else if (gKey.getKeyType() == KeyType.F1 && !endG) {
              if (rt.totalBet + 5 <= p.currency) {
                rt.greenBet += 5;
                rt.totalBet += 5;
              }
            }
            else if (gKey.getKeyType() == KeyType.F2 && !endG) {
              if (rt.totalBet > 0) {
                rt.greenBet -= 5;
                rt.totalBet -= 5;
              }
            }
            else if (gKey.getKeyType() == KeyType.F3 && !endG) {
              if (rt.totalBet + 5 <= p.currency) {
                rt.blackBet += 5;
                rt.totalBet += 5;
              }
            }
            else if (gKey.getKeyType() == KeyType.F4 && !endG) {
              if (rt.totalBet > 0) {
                rt.blackBet -= 5;
                rt.totalBet -= 5;
              }
            }
            else if (gKey.getKeyType() == KeyType.F5 && !endG) {
              if (rt.totalBet + 5 <= p.currency) {
                rt.redBet += 5;
                rt.totalBet += 5;
              }
            }
            else if (gKey.getKeyType() == KeyType.F6 && !endG) {
              if (rt.totalBet > 0) {
                rt.redBet -= 5;
                rt.totalBet -= 5;
              }
            }
            else if (gKey.getKeyType() == KeyType.Enter) {
              if (!endG) {
                p.bet(rt.totalBet, rt);
                rt.roll(); rt.calculate();
                p.set(p.currency + rt.winnings); endG = true;
              }
              else {
                rt = new Roulette(20, 15);
                endG = false;
              }
            }
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

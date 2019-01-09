import com.googlecode.lanterna.*;
import com.googlecode.lanterna.input.*;
import com.googlecode.lanterna.terminal.*;
import com.googlecode.lanterna.screen.*;
import java.io.IOException;
import java.awt.Color;

public class Casino {
  public static void putString(int x, int y, Screen screen, String str) {
		for (int i = 0; i < str.length(); ++i) {
			screen.setCharacter(x+i, y, new TextCharacter(str.charAt(i)));
		}
	}
  public static void main(String[] args) throws IOException {
    int x = 10; int y = 10;
    Screen screen = new DefaultTerminalFactory().createScreen();
		screen.startScreen();
    screen.clear(); screen.refresh();
    while (true) {
      KeyStroke key = screen.pollInput();
        screen.setCharacter(x, y, new TextCharacter('@', TextColor.ANSI.WHITE, TextColor.ANSI.BLACK));

      Slots slots = new Slots(15, 15);
      screen.setCharacter(slots.posX, slots.posY, new TextCharacter('7', TextColor.ANSI.WHITE, TextColor.ANSI.BLACK));
        
      if (key != null) {
        screen.setCharacter(x, y, new TextCharacter(' '));
        if (key.getKeyType() == KeyType.Escape) {break;}
				else if (key.getKeyType() == KeyType.ArrowLeft) {x--;}
				else if (key.getKeyType() == KeyType.ArrowRight) {x++;}
				else if (key.getKeyType() == KeyType.ArrowUp) {y--;}
				else if (key.getKeyType() == KeyType.ArrowDown) {y++;}
        putString(1, 1, screen, key + "");
      }
      screen.doResizeIfNecessary();
      screen.refresh();
    }
    screen.stopScreen();
  }
}

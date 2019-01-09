import com.googlecode.lanterna.*;
import com.googlecode.lanterna.input.*;
import com.googlecode.lanterna.terminal.*;
import com.googlecode.lanterna.screen.*;
import java.io.IOException;
import java.awt.Color;

/*  Lantera 3 adapted for Windows compatibility (will test on Linux system)
	https://jar-download.com/artifacts/com.googlecode.lanterna/lanterna/3.0.1/documentation?fbclid=IwAR3B2W4tmjJZgUs3iUjvXhkYRClpIW3Su2ItN9aHzcM21tDibwQs9sx6yXc
*/

public class TerminalDemo {

	public static void putString(int x, int y, Screen screen, String str) {
		for (int i = 0; i < str.length(); ++i) {
			screen.setCharacter(x+i, y, new TextCharacter(str.charAt(i)));
		}
	}

	public static void main(String[] args) throws IOException {

		int x = 10;
		int y = 10;

		Screen screen = new DefaultTerminalFactory().createScreen();
		screen.startScreen();

		/*long tStart = System.currentTimeMillis();
		long lastSecond = 0;*/

		while (true) {

			TextCharacter chr = new TextCharacter(
				'\u0024',
				new TextColor.RGB((int)(255*Math.random()), (int)(255*Math.random()), (int)(255*Math.random())),
				TextColor.ANSI.DEFAULT
			);
			screen.setCharacter(x, y, chr);

			Game slots = new Slots(15, 15);
			TextCharacter slot = new TextCharacter(
			  '\u0001',
				new TextColor.RGB(255, 255, 255),
				TextColor.ANSI.DEFAULT
			);
			screen.setCharacter(slots.posX, slots.posY, slot);

			KeyStroke key = screen.pollInput();

			if (key != null) {
				screen.setCharacter(x, y, new TextCharacter(' '));

				if      (key.getKeyType() == KeyType.Escape)     break;
				else if (key.getKeyType() == KeyType.ArrowLeft)  x--;
				else if (key.getKeyType() == KeyType.ArrowRight) x++;
				else if (key.getKeyType() == KeyType.ArrowUp)    y--;
				else if (key.getKeyType() == KeyType.ArrowDown)  y++;

				putString(1, 1, screen, key+"                 ");
			}
			/*long tEnd = System.currentTimeMillis();
			long millis = tEnd - tStart;
			putString(1, 2, screen, "Milliseconds since start of program: "+millis);
			if (millis / 1000 > lastSecond) {
				lastSecond = millis / 1000;
				putString(1, 3, screen, "Seconds since start of program: "+millis/1000);
			}*/
			screen.doResizeIfNecessary();
			screen.refresh();
		}
		screen.stopScreen();
	}
}

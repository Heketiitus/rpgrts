package rpgrts.rendering;

import java.awt.Font;

public class TextRenderInfo {

	public void renderText(String text, int x, int y, Font f) {
		char c = text.charAt(0);
		if(f.canDisplay(c)) {
			//TODO continue after break
		}
		else System.out.println(c + ": no such character in font :/");
		
	}
	
}

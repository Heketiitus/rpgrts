package rpgrts.main.gamestates;

import java.util.ArrayList;

import rpgrts.gui.MenuElement;
import rpgrts.renderiing.MenuRenderinfo;

public abstract class Menu extends GameState {

	ArrayList<MenuElement> menuelements;
	MenuRenderinfo renderer;
	public Menu(MenuRenderinfo renderer) {
		this.renderer = renderer;
		menuelements = new ArrayList<MenuElement>();
	}
	
}

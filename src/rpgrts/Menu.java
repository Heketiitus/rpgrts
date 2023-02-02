package rpgrts;

import java.util.ArrayList;

import rpgrts.gui.MenuElement;

public abstract class Menu extends GameState {

	ArrayList<MenuElement> menuelements;
	
	public Menu(Renderer renderer) {
		super(renderer);
		menuelements = new ArrayList<MenuElement>();
	}
	
}

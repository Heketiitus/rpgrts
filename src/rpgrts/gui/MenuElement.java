package rpgrts.gui;

import rpgrts.Renderer;

public abstract class MenuElement {
	
	abstract void update();
	abstract void render(Renderer r);
	abstract void init();
	abstract void cleanUp();
	
	public MenuElement() {
		init();
	}
	
}

package rpgrts.gui;

public abstract class MenuElement {
	
	abstract void update();
	abstract void render();
	abstract void init();
	abstract void cleanUp();
	
	public MenuElement() {
		init();
	}
	
}

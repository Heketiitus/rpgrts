package rpgrts.gui;

import rpgrts.rendering.MenuRenderinfo;

public abstract class MenuElement {
	
	public abstract void update();
	public abstract void render(MenuRenderinfo r);
	public abstract void init();
	public abstract void cleanUp();
	
	public MenuElement() {
		init();
	}
	
}

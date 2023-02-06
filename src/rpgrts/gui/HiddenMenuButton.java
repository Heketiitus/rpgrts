package rpgrts.gui;

import rpgrts.MenuRenderinfo;

public class HiddenMenuButton extends Button {

	boolean open;
	HiddenMenuThing hmt;
	
	public HiddenMenuButton(int texture, float x, float y, float width, float height) {
		super(texture, x, y,width,height);
		hmt = new HiddenMenuThing();
	}
	
	public void add(MenuElement e) {
		hmt.add(e);
	}
	
	@Override
	public void update() {
		super.update();
		if(pressed) {
			open = !open;
			hmt.toggleShown();
		}
		if(open) {
			highlighted = true;
			hmt.update();
		}
		
	}
	
	@Override
	public void render(MenuRenderinfo r) {
		if(open)
		hmt.render(r);		
		super.render(r);
	}
	
}

package rpgrts.gui;

import java.util.ArrayList;

import rpgrts.MenuRenderinfo;

public class HiddenMenuThing extends MenuElement {

	boolean shown;
	
	public ArrayList<MenuElement> elements;
	
	public HiddenMenuThing() {
		elements = new ArrayList<MenuElement>();
	}
	
	public void setShown(boolean shown) {
		this.shown = shown;
	}
	
	public void toggleShown() {
		shown = !shown;
	}
	
	public void hide() {
		shown = false;
	}
	
	public void show() {
		shown = true;
	}
	
	public void randomize() {
		 shown = Math.random()>0.5;
	}
	
	public void randomize(double chance) {
		 shown = Math.random()>chance;
	}
	
	public void add(MenuElement e) {
		elements.add(e);
	}
	
	@Override
	public void update() {
		if(shown) {
			for(MenuElement i:elements) i.update();
		}
	}

	@Override
	public void render(MenuRenderinfo r) {
		if(shown) {
			for(MenuElement i:elements) i.render(r);
		}
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cleanUp() {
		// TODO Auto-generated method stub
		
	}

}

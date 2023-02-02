package rpgrts.gui;

public abstract class Button extends MenuElement {

	int texture;
	boolean pressed;
	boolean hover;
	int x,y;
	
	public Button(int texture, int x, int y) {
		this.x=x;
		this.y=y;
		this.texture=texture;
		this.hover = false;
		this.pressed= false;
	}
	
	public void render() {
		
	}
	public void update() {
		
	}
}

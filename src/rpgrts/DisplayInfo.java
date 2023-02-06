package rpgrts;

public class DisplayInfo {
	float widthm;
	float heightm;
	float width;
	float height;
	float size;
	float ratio;
	
	public float getWidthm() {return widthm;}
	public float getHeightm() {return heightm;}
	public float getHeight() {return height;}
	public float getWidth() {return width;}
	
	public float getSize() {return size;}
	public float getRatio() {return ratio;}
	
	public void updateScreen(float width, float height) {
		this.width = width;
		this.height = height;
		widthm = 1.0f/width;
		heightm = 1.0f/height;
		ratio  = (float) Math.sqrt(width*width+height*height);
		size = ratio*0.1f;
	}
	
}

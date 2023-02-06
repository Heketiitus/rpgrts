package rpgrts;

public class Camera {

	public float x;
	public float y;
	public int renderwidth;
	public int renderheight;
	public float zoom;
	
	public Camera() {
		x = 0;
		y = 0;
		renderwidth = 6;
		renderheight =6;
		zoom = 1f/32f;
	}
	
}

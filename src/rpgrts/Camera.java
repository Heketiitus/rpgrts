package rpgrts;

public class Camera {

	public float x;
	public float y;
	public int renderwidth;
	public int renderheight;
	//the coordinates of a corner of loaded chunks to assist in picking a block with your mouse
	public int loadedChunkTop;
	public int loadedChunkSide;
	
	public float zoom;
	
	
	public Camera() {
		x = 0;
		y = 0;
		renderwidth = 2;
		renderheight = 2;
		zoom = 50f;
	}
	
}

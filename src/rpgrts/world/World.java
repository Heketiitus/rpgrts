package rpgrts.world;

import java.io.File;

import rpgrts.Camera;
import rpgrts.GameInfo;
import rpgrts.main.Main;

//if you want bigger/infinite worlds just remame this and create one that's even higher level, or do something better
public class World {

	
	Chunk[][] chunks;
	
	public int width, height, cwidth, cheight;
	
	public World(int width, int height, int cwidth, int cheight) {
		this.width = width;
		this.height = height;
		this.cwidth = cwidth;
		this.cheight = cheight;
		chunks = new Chunk[width][height];
	}
	
	int framebuffer;
	
	public void render(Camera camera, GameInfo gi) {

		for(int x = 0; x < camera.renderwidth; x++) {
			for(int y = 0; y < camera.renderheight; y++) {
				chunks[x][y].render(gi, camera);
			}
		}

	}
	
	public static World loadWorld(String src) {
		
		
		return null;
	}
	
	public void save(String dst) {
		
		File file = new File(dst);
		String res = "";
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				res += getChunk(x,y).save(dst);
			}
		}
		
	}
	
	//just in case its necessary for some reason to make chunks a one dimensional array.
	public Chunk getChunk(int x,int y) {
		return chunks[x][y];
	}
	
	public int[] ScreenToWorldPosition(Camera cam, double screenX, double screenY) {
		
		int[] ret = new int[6];
		
		int x = (int)(cam.x*cwidth);
		int y = (int) (cam.y*cheight);
		ret[0] = x;
		ret[1] = y;
		
		ret[2] = (int)Math.round((-Main.displayinfo.getWidth()/2)/(cam.zoom/2)+screenX/(cam.zoom/2)-cam.x*cwidth+(float)(cwidth-1)/2);
		ret[3] = (int)Math.round((Main.displayinfo.getHeight()/2)/(cam.zoom/2)-screenY/(cam.zoom/2)-cam.y*cheight+(float)(cheight-1)/2);
		
		ret[4] = (int)(cam.zoom/screenX);
		ret[5] = (int)(cam.zoom/screenY);
		return ret;
	}
	
	public void replaceChunk(Chunk chunk, int x, int y) {
		chunks[x][y] = chunk;
	}
	
	public Chunk createChunk(int x, int y) {
		Chunk chunk = new Chunk(cwidth,cheight,x,y);
		replaceChunk(chunk, x,y);
		return chunk;
	}

	public void setTile(int i, int x, int y) {
		int cx = x/cwidth;
		int cy = y/cheight;
		chunks[cx][cy].setTile(i, x-cx*cwidth, y-cy*cheight);
		
	}
	
	public void cleanUp() {
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				chunks[x][y].cleanUp();
			}
		}
	}
	
}

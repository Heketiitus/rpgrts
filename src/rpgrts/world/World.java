package rpgrts.world;

import java.io.File;

import rpgrts.Camera;
import rpgrts.GameInfo;

//if you want bigger/infinite worlds just remame this and create one that's even higher level, or do something better idk
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
	
}

package rpgrts.world;

import java.io.File;

import org.lwjgl.opengl.GL20;

import rpgrts.Camera;
import rpgrts.Game;
import rpgrts.GameInfo;
import rpgrts.main.Main;

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
	
	public int[] ScreenToWorldPosition(Camera cam, double mouseX, double mouseY) {
		//x y chunkx chunky in chunk coordinates x and  y
		int[] ret = new int[6];
		//size: 	camera.zoom/Main.displayinfo.getWidth()
		//x: 		camera.zoom*x*2-1+camera.x*camera.zoom)/Main.displayinfo.getWidth()
		//y:		camera.zoom*y*2-1+camera.y*camera.zoom)/Main.displayinfo.getHeight()
		//System.out.println("cam.x " + cam.x + " " + (cam.x/cam.zoom+mouseX/cam.zoom));
		int x = (int)(cam.x+mouseX/cam.zoom);
		int y = (int) (cam.y+mouseY/cam.zoom);
		ret[0] = x;
		ret[1] = y;
		ret[2] = x/cwidth;
		ret[3] = y/cheight;
		ret[4] = ret[0]-ret[2]*cwidth;
		ret[5] = ret[1]-Math.abs(ret[3])*cheight;
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
	
}

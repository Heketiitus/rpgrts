package rpgrts.world;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import rpgrts.Camera;
import rpgrts.Game;
import rpgrts.GameInfo;
import rpgrts.Loader;
import rpgrts.Texture;
import rpgrts.main.Main;

public class Chunk {
	int[][] tiles;
	int width, height;
	int x, y;
	
	public Chunk(int width, int height, int x, int y) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		tiles = new int[width][height];
		for(int x2 = 0; x2 < width; x2++) {
			for(int y2 = 0; y2 < height; y2++) {
				tiles[x2][y2] = 56;
			}
		}
	}

	public void updateChunk() {
		
	}
	
	public String save(String dst) {
		return "";
	}
	
	public void setTile(int tile, int x, int y) {
		tiles[x][y] = tile;
		System.out.println("set tile " + getTile(x,y));
	}
	
	public int getTile(int x, int y) {
		return tiles[x][y];
	}

	public void render(GameInfo gi, Camera camera) {
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {

				int x = this.x*width+i;
				int y = this.y*width+j;
				
				GL30.glBindVertexArray(Game.tilerenderinfo.tileVAO);
				GL20.glUseProgram(Game.tilerenderinfo.shader.program);
				GL11.glEnable(GL11.GL_TEXTURE_2D);
				Texture texture;
				if(x==Game.testx&&y==Game.testy)
				texture = gi.tiles[getTile(i,j)].texture;
				else texture = new Texture(Loader.tileset, 1, 1, 0);
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTexture());
				GL20.glUniform4fv(Game.tilerenderinfo.sizeuniform, new float[] {camera.zoom/Main.displayinfo.getWidth()/2,camera.zoom/Main.displayinfo.getHeight()/2,1.0f,1.0f});
				GL20.glUniform4fv(Game.tilerenderinfo.positionuniform, new float[] {(camera.zoom*x+camera.x*camera.zoom)/Main.displayinfo.getWidth(),(camera.zoom*y+camera.y*camera.zoom)/Main.displayinfo.getHeight(),0,0});
				GL20.glUniform2fv(Game.tilerenderinfo.texturesizeuniform, new float[] {8,8});
				GL20.glUniform2fv(Game.tilerenderinfo.textureindexuniform, new float[] {texture.getTextureindex()%texture.getTexturewidth(),texture.getTextureindex()/texture.getTextureheight()});
				GL11.glDrawElements(GL11.GL_TRIANGLES, 6,GL11.GL_UNSIGNED_INT,0);
		       
			}
		}
	}
	
}

package rpgrts.world;

import java.nio.ByteBuffer;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import rpgrts.Camera;
import rpgrts.GameInfo;
import rpgrts.main.Main;
import rpgrts.main.gamestates.Game;
import rpgrts.rendering.Texture;

public class Chunk {
	int[][] tiles;
	int width, height;
	int x, y;
	
	int texture;
	int framebuffer;
	
	boolean outdated;
	
	public Chunk(int width, int height, int x, int y) {
		this.width = width;
		this.height = height;
		this.x = x;
		this.y = y;
		tiles = new int[width][height];
		for(int x2 = 0; x2 < width; x2++) {
			for(int y2 = 0; y2 < height; y2++) {
				//tiles[x2][y2] = 56;
				tiles[x2][y2] = 13;
			}
		}
		outdated = true;
		init();
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
		if(outdated) update(gi,camera);
		GL30.glBindVertexArray(Main.rendererinfo.squareVAO);
		GL20.glUseProgram(Main.rendererinfo.shader.program);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
		GL20.glUniform4fv(Main.rendererinfo.sizeuniform, new float[] {(camera.zoom*width)/Main.displayinfo.getWidth()/2,(camera.zoom*height)/Main.displayinfo.getHeight()/2,1.0f,1.0f});
		GL20.glUniform4fv(Main.rendererinfo.positionuniform, new float[] {(camera.zoom*width*x+camera.x*width*camera.zoom)/Main.displayinfo.getWidth(),(camera.zoom*height*y+camera.y*height*camera.zoom)/Main.displayinfo.getHeight(),0.0f,0.0f});
		GL11.glDrawElements(GL11.GL_TRIANGLES, 6,GL11.GL_UNSIGNED_INT,0);
	}
	
	public void update() {
		outdated = true;
	}
	
	void update(GameInfo gi, Camera camera) {

		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, framebuffer);
		GL11.glViewport(0, 0, width*Game.tilerenderinfo.tilesize, width*Game.tilerenderinfo.tilesize);
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {

				
				int x = this.x*width+i;
				int y = this.y*width+j;
				GL30.glBindVertexArray(Game.tilerenderinfo.tileVAO);
				GL20.glUseProgram(Game.tilerenderinfo.shader.program);
				GL11.glEnable(GL11.GL_TEXTURE_2D);
				Texture texture = gi.tiles[getTile(i,j)].texture;
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getTexture());
				GL20.glUniform4fv(Game.tilerenderinfo.sizeuniform, new float[] {1.0f/(float)(width),1.0f/(float)(height),1.0f,1.0f});//{1.0f/(float)width,1.0f/(float)height,1.0f,1.0f});
				//GL20.glUniform4fv(Game.tilerenderinfo.positionuniform, new float[] {(float)(i*2.0f-width)/(float)(width),(float)(j*2.0f-height)/(float)(height),0,0});
				GL20.glUniform4fv(Game.tilerenderinfo.positionuniform, new float[] {(float)(i*2-width+1)/(float)(width),(float)(j*2.0f-height+1)/(float)(height),0,0});
				GL20.glUniform2fv(Game.tilerenderinfo.texturesizeuniform, new float[] {texture.getTexturewidth(),texture.getTextureheight()});
				GL20.glUniform2fv(Game.tilerenderinfo.textureindexuniform, new float[] {texture.getTextureindex()%texture.getTexturewidth(),texture.getTextureindex()/texture.getTextureheight()});
				
				GL11.glDrawElements(GL11.GL_TRIANGLES, 6,GL11.GL_UNSIGNED_INT,0);
				
			}
		}

		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
		GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, 0);
		GL11.glViewport(0, 0, (int)Main.displayinfo.getWidth(), (int)Main.displayinfo.getHeight());
		outdated = false;
	}
	
	public void init() {
		
		framebuffer = GL30.glGenFramebuffers();
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, framebuffer);

		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		texture = GL11.glGenTextures();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST_MIPMAP_LINEAR);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
		
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA, width*Game.tilerenderinfo.tilesize, height*Game.tilerenderinfo.tilesize, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, (ByteBuffer)null);

		GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
		GL30.glFramebufferTexture2D(GL30.GL_FRAMEBUFFER,GL30.GL_COLOR_ATTACHMENT0, GL11.GL_TEXTURE_2D, texture, 0); 
		if(GL30.glCheckFramebufferStatus(GL30.GL_FRAMEBUFFER) == GL30.GL_FRAMEBUFFER_COMPLETE) System.out.println("framebuffer  complete");	
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, 0);
	
	}
	
	public void cleanUp() {
		GL30.glDeleteFramebuffers(framebuffer);
		GL30.glDeleteTextures(texture);
	}
	
	
	
}

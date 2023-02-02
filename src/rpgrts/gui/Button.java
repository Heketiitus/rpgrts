package rpgrts.gui;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import rpgrts.Loader;
import rpgrts.Renderer;
import rpgrts.main.main;

public class Button extends MenuElement {

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
	
	public void render(Renderer r) {
		GL11.glViewport(0, 0, main.rwidth, main.rheight);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);		
		GL30.glBindVertexArray(r.VAO);
		GL20.glUseProgram(r.s.program);
		//this math only has to be made when the resolution changes...
		float widthm = 1.0f/(float)main.rwidth;
		float heightm = 1.0f/(float)main.rheight;
		float ratio  = (float) Math.sqrt(main.rwidth*main.rwidth+main.rheight*main.rheight);
		float size = ratio*0.1f;
		float x = -20+(float)texture*0.1f;
		float y = 0;
		
		if(x*size*widthm>2)texture=0;
		GL20.glUniform4fv(r.sizeuniform, new float[] {10*size*widthm,10*size*heightm,1.0f,1.0f});
		GL20.glUniform4fv(r.positionuniform, new float[] {x*size*widthm,y*size*heightm,0.0f,0.0f});
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		if(main.everysecond) texture++;
		GL11.glBindTexture(GL11.GL_TEXTURE_2D,Loader.ukkeli);	 
		
        GL11.glDrawElements(GL11.GL_TRIANGLES, 6, GL11.GL_UNSIGNED_INT, 0);
        
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL20.glUseProgram(0);
	}
	public void update() {
		
	}

	@Override
	void init() {
		
	}

	@Override
	void cleanUp() {
		
	}
}

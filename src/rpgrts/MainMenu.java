package rpgrts;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL45;

import rpgrts.gui.Button;
import rpgrts.main.main;
import rpgrts.stupidexception.StupidException;

public class MainMenu extends Menu {
	float x = 0;
	float y = 0;
	public MainMenu(Renderer renderer) {
		super(renderer);
	}
	
	public void init() {


		
		menuelements.add(new Button(Loader.playbutton, 0, 0));
		
	}
	
	public void cleanUp() {
		
	}
	float widthm;
	float heightm;
	float size;
	float ratio;
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		//this math only has to be made when the resolution changes...
		widthm = 1.0f/(float)main.rwidth;
		heightm = 1.0f/(float)main.rheight;
		ratio  = (float) Math.sqrt(main.rwidth*main.rwidth+main.rheight*main.rheight);
		size = ratio*0.1f;
		x = -20+(float)texture*0.1f;
		y = 0;
		if(main.everysecond) texture++;
		if(x*size*widthm>2)texture=0;
	}
	int texture = 0;
	@Override
	public void render() {GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glViewport(0, 0, main.rwidth, main.rheight);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);		
		GL30.glBindVertexArray(renderer.VAO);
		GL20.glUseProgram(renderer.s.program);

		
		GL20.glUniform4fv(renderer.sizeuniform, new float[] {10*size*widthm,10*size*heightm,1.0f,1.0f});
		GL20.glUniform4fv(renderer.positionuniform, new float[] {x*size*widthm,y*size*heightm,0.0f,0.0f});
		GL11.glEnable(GL11.GL_TEXTURE_2D);

		GL11.glBindTexture(GL11.GL_TEXTURE_2D,Loader.ukkeli);	 
		
        GL11.glDrawElements(GL11.GL_TRIANGLES, 6, GL11.GL_UNSIGNED_INT, 0);
        
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL20.glUseProgram(0);
	}

}

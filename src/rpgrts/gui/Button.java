package rpgrts.gui;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import rpgrts.main.Main;
import rpgrts.renderiing.MenuRenderinfo;

public class Button extends MenuElement {

	int texture;
	public boolean pressed;
	public boolean highlighted;
	boolean hover;
	float x,y;
	float width;
	float height;
	
	public Button(int texture, float x, float y) {
		this.x=x;
		this.y=y;
		this.texture=texture;
		this.hover = false;
		this.pressed= false;
		width = 100;
		height = 100;
	}
	
	public Button(int texture, float x, float y, float width, float height) {
		this.x=x;
		this.y=y;
		this.texture=texture;
		this.hover = false;
		this.pressed= false;
		this.width = width;
		this.height = height;
	}
	
	public void render(MenuRenderinfo r) {
		if(highlighted) {
			GL30.glBindVertexArray(r.selectedsquareVAO);
		} else GL30.glBindVertexArray(r.squareVAO);
		GL20.glUseProgram(r.shader.program);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		//this comment is here to remind me of a thing, i think i may need it at some point so dont delete
		//GL20.glUniform4fv(r.sizeuniform, new float[] {width/r.getWidth(),height/r.getHeight(),1.0f,1.0f});
		
		//it is possible that it could also be good just to make a different mesh for each button rather than doing this
		GL20.glUniform4fv(r.sizeuniform, new float[] {width,height,1.0f,1.0f});
		GL20.glUniform4fv(r.positionuniform, new float[] {x*2-1+width,y*2-1+height,0.0f,0.0f});
		
		GL11.glBindTexture(GL11.GL_TEXTURE_2D,texture);	 
		
        GL11.glDrawElements(GL11.GL_TRIANGLES, 6, GL11.GL_UNSIGNED_INT, 0);
        
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL20.glUseProgram(0);
	}
	
	public void update() {
		pressed = false;
		///
		/*hover = 
		Main.inputinfo.mouseX >x*Main.displayinfo.getWidth()-width*Main.displayinfo.getWidth()/2&&
		Main.inputinfo.mouseX <x*Main.displayinfo.getWidth()+width*Main.displayinfo.getWidth()/2&&
		Main.inputinfo.mouseY >Main.displayinfo.getHeight()-y*Main.displayinfo.getHeight()-height*Main.displayinfo.getHeight()/2&&
		Main.inputinfo.mouseY <Main.displayinfo.getHeight()-y*Main.displayinfo.getHeight()+height*Main.displayinfo.getHeight()/2;*/
		hover = 
				Main.inputinfo.mouseX >x*Main.displayinfo.getWidth()&&
				Main.inputinfo.mouseX <x*Main.displayinfo.getWidth()+width*Main.displayinfo.getWidth()&&
				Main.inputinfo.mouseY >Main.displayinfo.getHeight()-y*Main.displayinfo.getHeight()-height*Main.displayinfo.getHeight()&&
				Main.inputinfo.mouseY <Main.displayinfo.getHeight()-y*Main.displayinfo.getHeight();
		highlighted = hover;
		if(hover&&Main.inputinfo.click[0]) {
			System.out.println("butt on");
			pressed = true;
		}
		
		
		
		
	}

	@Override
	public void init() {
		
	}

	@Override
	public void cleanUp() {
		
	}
}

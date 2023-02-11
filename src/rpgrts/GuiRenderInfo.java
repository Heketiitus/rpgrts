package rpgrts;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import rpgrts.main.Main;
import rpgrts.stupidexception.StupidException;

public class GuiRenderInfo {
	
	public Shader shader;
	//use tile for now
	//public int guiVBO;
	//public int guiVAO;
	
	public int sizeuniform;
	public int positionuniform;
	public int rotationuniform;
	public int textureindexuniform;
	public int texturesizeuniform;
	
	public void init() {
		shader = new Shader();
		shader.createProgram();
		shader.loadVertexShader("res/shaders/guiVertexShader.txt");
		shader.loadFragmentShader("res/shaders/guiFragmentShader.txt");
		shader.linkProgram();
		try{
			sizeuniform = getUniform(shader.program, "size");
			positionuniform = getUniform(shader.program,"location");
			rotationuniform = getUniform(shader.program, "rot");
			textureindexuniform = getUniform(shader.program, "TextureIndex");
			texturesizeuniform = getUniform(shader.program,"TextureSize");
		}catch(StupidException e) {
			e.printStackTrace();
		}

		GL20.glVertexAttribPointer(0, 2, GL11.GL_FLOAT, false,4*4, 0);
		GL20.glVertexAttribPointer(1, 2, GL11.GL_FLOAT, false, 4*4, 2*4);
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
	}
	
	int getUniform(int program, String name) throws StupidException{
		int uniform = GL20.glGetUniformLocation(shader.program, name);
		if(uniform==-1)throw new StupidException("uniform name probably wrong. \nName was: "  + name);
		return uniform;
	}
	
	public void renderGui(float x, float y, float width, float height, float size) {
		GL20.glUseProgram(shader.program);
		GL30.glBindVertexArray(Game.tilerenderinfo.tileVAO);
		
		
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, Loader.guibackground);
		for(int i = 0; i < width; i++) {
			for(int j = 0; j < height; j++) {
				GL30.glUniform4fv(sizeuniform, new float[] {size/Main.displayinfo.getWidth()/2,size/Main.displayinfo.getHeight()/2,1f,1f});
				GL30.glUniform4fv(positionuniform, new float[] {x/Main.displayinfo.getWidth()+i*size/Main.displayinfo.getWidth(),y/Main.displayinfo.getHeight()+j*size/Main.displayinfo.getHeight(),0.0f,0.0f});
				GL30.glUniform2fv(texturesizeuniform, new float[] {3.0f,1.0f});
				if(i==width-1)GL30.glUniform2fv(textureindexuniform, new float[] {0,0});
				else if(j==height-1)GL30.glUniform2fv(textureindexuniform, new float[] {1,0});
				else GL30.glUniform2fv(textureindexuniform, new float[] {2,0});
				GL30.glUniform1f(rotationuniform, (float)Math.toRadians(Game.angle));
				
				GL11.glDrawElements(GL11.GL_TRIANGLES, 6,GL11.GL_UNSIGNED_INT,0);
			}
		}
	}
	
}

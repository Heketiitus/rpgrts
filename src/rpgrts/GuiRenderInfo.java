package rpgrts;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

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
}

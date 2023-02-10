package rpgrts;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import rpgrts.stupidexception.StupidException;

public class TileRenderInfo {

	public int positionuniform;
	public int sizeuniform;
	public int texturesizeuniform;
	public int textureindexuniform;
	public Shader shader;
	public int tileVAO;
	public int tileVBO;
	public int VEO;
	public int tilesize;
	float[] square = {
			-1f,-1f,	0.0f,0.0f,
			-1f,1f,		0.0f,1.0f,
			1f,-1f,		1.0f,0.0f,
			1f,1f,		1.0f,1.0f
	};
	int[] indices = {
			2,0,1,2,3,1
	};
	
	public void cleanUp() {
		
	}
	
	public void init() {

		shader = new Shader();
		shader.createProgram();
		shader.loadVertexShader("res/shaders/tileVertexShader.txt");
		shader.loadFragmentShader("res/shaders/tileFragmentShader.txt");
		shader.linkProgram();
		
		try{
			sizeuniform = getUniform(shader.program, "size");
			positionuniform = getUniform(shader.program,"location");
			texturesizeuniform = getUniform(shader.program, "TextureSize");
			textureindexuniform = getUniform(shader.program,"TextureIndex");
		}catch(StupidException e) {
			e.printStackTrace();
		}
		
		tileVAO = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(tileVAO);
		tileVBO = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, tileVBO);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, square, GL15.GL_STATIC_DRAW);
		VEO = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, VEO);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indices, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(0, 2, GL11.GL_FLOAT, false,4*4, 0);
		GL20.glVertexAttribPointer(1, 2, GL11.GL_FLOAT, false, 4*4, 2*4);
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA); 
		GL11.glEnable(GL11.GL_BLEND);
		
		tilesize = 32;
		
	}
	//shared code between menurenderer and tilerenderer please movve into some other place
	int getUniform(int program, String name) throws StupidException{
		int uniform = GL20.glGetUniformLocation(shader.program, name);
		if(uniform==-1)throw new StupidException("uniform name probably wrong. \nName was: "  + name);
		return uniform;
	}
}

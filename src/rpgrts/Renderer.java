package rpgrts;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import rpgrts.stupidexception.StupidException;

public class Renderer {

	public int VAO;
	public int VBO;
	public Shader s;
	public int positionuniform;
	public int sizeuniform;
	int VEO;
	
	float[] square = {
			-1f,-1f,0.0f,0.0f,
			-1f,1f,0.0f,1.0f,
			1f,-1f,1.0f,0.0f,
			1f,1f,1.0f,1.0f
			};
	int[] indices = {
		2,0,1,2,3,1
			
	};
	
	public void init() {		
		s = new Shader();
		s.createProgram();
		s.loadVertexShader("res/shaders/menuVertexShader.txt");
		s.loadFragmentShader("res/shaders/menuFragmentShader.txt");
		s.linkProgram();									
		//uniform = GL20.glGetUniformLocation(s.vertexShader, "transformation");
		
		VAO = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(VAO);
		VBO = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBO);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, square, GL15.GL_STATIC_DRAW);
		VEO = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, VEO);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indices, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(0, 2, GL11.GL_FLOAT, false, 4*4, 0);
		GL20.glVertexAttribPointer(1, 2, GL11.GL_FLOAT, false, 4*4, 2*4);
		GL20.glEnableVertexAttribArray(0);  
		GL20.glEnableVertexAttribArray(1);
		try{
			sizeuniform = getUniform(s.program, "size");
			positionuniform = getUniform(s.program,"location");
		}catch(StupidException e) {
			e.printStackTrace();
		}
		
	}
	int getUniform(int program, String name) throws StupidException{
		int uniform = GL20.glGetUniformLocation(s.program, name);
		if(uniform==-1)throw new StupidException("uniform name probably wrong. \nName was: "  + name);
		return uniform;
	}
}

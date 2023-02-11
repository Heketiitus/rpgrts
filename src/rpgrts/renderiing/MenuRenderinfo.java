package rpgrts.renderiing;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import rpgrts.stupidexception.StupidException;

public class MenuRenderinfo {

	public int squareVAO;
	public int squareVBO;
	public int selectedsquareVAO;
	public int selectedsquareVBO;
	public Shader shader;
	public int positionuniform;
	public int sizeuniform;
	int VEO;
	
	

	
	
	float[] square = {
			-1f,-1f,	1.0f,1.0f,1.0f,1.0f,	0.0f,0.0f,
			-1f,1f,		1.0f,1.0f,1.0f,1.0f,	0.0f,1.0f,
			1f,-1f,		1.0f,1.0f,1.0f,1.0f,	1.0f,0.0f,
			1f,1f,		1.0f,1.0f,1.0f,1.0f,	1.0f,1.0f
	};
	float[] selectedsquare = {
			-1f,-1f,	2.0f,2.0f,2.0f,1.0f,	0.0f,0.0f,
			-1f,1f,		2.0f,2.0f,2.0f,1.0f,	0.0f,1.0f,
			1f,-1f,		2.0f,2.0f,2.0f,1.0f,	1.0f,0.0f,
			1f,1f,		2.0f,2.0f,2.0f,1.0f,	1.0f,1.0f
	};
	int[] indices = {
		2,0,1,2,3,1
			
	};

	public void init() {		
		shader = new Shader();
		shader.createProgram();
		shader.loadVertexShader("res/shaders/menuVertexShader.txt");
		shader.loadFragmentShader("res/shaders/menuFragmentShader.txt");
		shader.linkProgram();
		try{
			sizeuniform = getUniform(shader.program, "size");
			positionuniform = getUniform(shader.program,"location");
		}catch(StupidException e) {
			e.printStackTrace();
		}
		
		squareVAO = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(squareVAO);
		squareVBO = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, squareVBO);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, square, GL15.GL_STATIC_DRAW);
		VEO = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, VEO);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indices, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(0, 2, GL11.GL_FLOAT, false, 4*8, 0);
		GL20.glVertexAttribPointer(1, 4, GL11.GL_FLOAT, false, 4*8, 2*4);
		GL20.glVertexAttribPointer(2, 2, GL11.GL_FLOAT, false, 4*8, 6*4);
		
		GL20.glEnableVertexAttribArray(0);  
		GL20.glEnableVertexAttribArray(1); 
		GL20.glEnableVertexAttribArray(2);
		
		selectedsquareVAO = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(selectedsquareVAO);
		selectedsquareVBO = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, selectedsquareVBO);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, selectedsquare, GL15.GL_STATIC_DRAW);
		VEO = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, VEO);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indices, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(0, 2, GL11.GL_FLOAT, false, 4*8, 0);
		GL20.glVertexAttribPointer(1, 4, GL11.GL_FLOAT, false, 4*8, 2*4);
		GL20.glVertexAttribPointer(2, 2, GL11.GL_FLOAT, false, 4*8, 6*4);
		GL20.glEnableVertexAttribArray(0);  
		GL20.glEnableVertexAttribArray(1); 
		GL20.glEnableVertexAttribArray(2);
		
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA); 
		GL11.glEnable(GL11.GL_BLEND);
		
		
	}
	
	public void cleanUp() {
		shader.cleanUp();
	}
	
	int getUniform(int program, String name) throws StupidException{
		int uniform = GL20.glGetUniformLocation(shader.program, name);
		if(uniform==-1)throw new StupidException("uniform name probably wrong. \nName was: "  + name);
		return uniform;
	}
}

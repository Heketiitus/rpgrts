package rpgrts;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL45;

public class MainMenu extends Menu {

	
	
	float[] square = {
			-1f,-1f,0.0f,0.0f,
			-1f,1f,0.0f,1.0f,
			1f,-1f,1.0f,0.0f,
			1f,1f,1.0f,1.0f
			};
	int[] indices = {
		2,0,1,2,3,1
			
	};
	
	Shader s;
	int sizeuniform;
	int positionuniform;
	
	int VBO;
	int VAO;
	int VEO;
	
	public int getUniform(int program, String name) throws StupidException{
		int uniform = GL20.glGetUniformLocation(s.program, name);
		if(uniform==-1)throw new StupidException("shithi");
		return uniform;
	}
	
	public void init() {

		s = new Shader();
		s.createProgram();
		s.loadVertexShader("res/shaders/menuVertexShader.txt");
		s.loadFragmentShader("res/shaders/menuFragmentShader.txt");
		s.linkProgram();									
		//uniform = GL20.glGetUniformLocation(s.vertexShader, "transformation");
		try{
			sizeuniform = getUniform(s.program, "size");
			positionuniform = getUniform(s.program,"location");
		}catch(StupidException e) {
			e.printStackTrace();
		}
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
		
	}
	
	public void cleanUp() {
		s.cleanUp();
	}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}
	int texture = 0;
	@Override
	public void render() {GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glViewport(0, 0, main.rwidth, main.rheight);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);		
		
		//new float[] {1.0f,0.0f,0.0f,0.0f,0.0f,1.0f,0.0f,0.0f,0.0f,0.0f,1.0f,0.0f,1.0f,1.0f,1.0f,1.0f});
		GL30.glBindVertexArray(VAO);
		GL20.glUseProgram(s.program);
		float widthm = 1.0f/(float)main.rwidth;
		float heightm = 1.0f/(float)main.rheight;
		float ratio  = (float) Math.sqrt(main.rwidth*main.rwidth+main.rheight*main.rheight);
		float size = ratio*0.1f;
		//System.out.println(ratio + " "+((float)main.rwidth/(float)main.rheight));
		//GL20.glUniform4fv(uniform,new float[] {mw/(float)main.width,my/(float)main.height,1.0f,1.0f}); 
		//float aspect = (float)main.width/(float)main.height;
		float x = -20+(float)texture*0.1f;
		
		float y = 0;
		if(x*size*widthm>2)texture=0;
		GL20.glUniform4fv(sizeuniform, new float[] {10*size*widthm,10*size*heightm,1.0f,1.0f});
		GL20.glUniform4fv(positionuniform, new float[] {x*size*widthm,y*size*heightm,0.0f,0.0f});
		//GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE)
		
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		if(main.everysecond) texture++;
		GL11.glBindTexture(GL11.GL_TEXTURE_2D,main.ukkeli);	 
		
		/*GL11.glBegin(GL11.GL_TRIANGLES);
		
		//GL11.glVertex2f(-1f, -1f);GL11.glTexCoord2f(0.0f,0.0f);
		//GL11.glVertex2f(-1f,1f);GL11.glTexCoord2f(0.0f,1.0f);
		//GL11.glVertex2f(1f,-1f);GL11.glTexCoord2f(1.0f,0.0f);
		//GL11.glVertex2f(1f,1f);GL11.glTexCoord2f(1.0f,1.0f);
		

		GL11.glVertex2f(1.0f,-1.0f);GL11.glTexCoord2f(1.0f,0.0f);
		GL11.glVertex2f(-1.0f, -1.0f);GL11.glTexCoord2f(0.0f,0.0f);
		GL11.glVertex2f(-1.0f,1.0f);GL11.glTexCoord2f(0.0f,1.0f);
		GL11.glVertex2f(1.0f,-1.0f);GL11.glTexCoord2f(1.0f,0.0f);
		GL11.glVertex2f(1.0f,1.0f);GL11.glTexCoord2f(1.0f,1.0f);
		GL11.glVertex2f(-1.0f,1.0f);GL11.glTexCoord2f(0.0f,1.0f);
		
		GL11.glEnd();*/
		
        GL11.glDrawElements(GL11.GL_TRIANGLES, 6, GL11.GL_UNSIGNED_INT, 0);
        
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL20.glUseProgram(0);
	}

}

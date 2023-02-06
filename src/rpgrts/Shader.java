package rpgrts;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

public class Shader {
	
	public int program;
	int vertexShader;
	int fragmentShader;
	
	
	public void createProgram() {
		program = GL20.glCreateProgram();
	}
	
	public void linkProgram() {
		GL20.glLinkProgram(program);
		if(GL20.glGetProgrami(program, GL20.GL_LINK_STATUS) == GL20.GL_FALSE) throw new RuntimeException("Unable to link shader program: " + GL20.glGetProgramInfoLog(program,GL20.glGetProgrami(program, GL20.GL_INFO_LOG_LENGTH)));
	}
	
	public void loadFragmentShader(String file) {
		fragmentShader = loadShader(file, GL20.GL_FRAGMENT_SHADER, program);
	}
	public void loadVertexShader(String file) {
		vertexShader = loadShader(file,GL20.GL_VERTEX_SHADER,program);
	}
	
	public static int loadShader(String file,int shadertype, int program) {
		
		String src = "";
		File f = new File(file);
		if(f.exists()) {
			try {
				FileInputStream fis = new FileInputStream(f);
				byte[] barray = fis.readAllBytes();
				fis.close();
				for(byte i:barray) {
					src = src+(char)i;
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("file at " + file + " couldnt be found");
		}
		System.out.println("shader source of " + file + " loks like this \n" + src);
		int shader = GL20.glCreateShader(shadertype);
	    GL20.glShaderSource(shader, src);
	    GL20.glCompileShader(shader);
	    if (GL20.glGetShaderi(shader, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE)
	        throw new RuntimeException("shader error in " + file + "\n" + GL20.glGetShaderInfoLog(shader, GL20.glGetShaderi(shader, GL20.GL_INFO_LOG_LENGTH)));
	    GL20.glAttachShader(program, shader);
	    return shader;
	}
	
	public void cleanUp() {
		GL20.glDetachShader(program, vertexShader);
		GL20.glDetachShader(program, fragmentShader);
		GL20.glDeleteShader(vertexShader);
		GL20.glDeleteShader(fragmentShader);
		GL20.glDeleteProgram(program);
	}
	
}

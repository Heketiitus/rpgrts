package rpgrts.main;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWFramebufferSizeCallbackI;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import rpgrts.GameState;
import rpgrts.Loader;
import rpgrts.MainMenu;

public class main {
	
	public static void main(String[] args) {
		new main();
	}
	
	static final int targetfps = 60;

	static final long second = 1000000000;
	static final long frame = second/targetfps;
	double frames = 0;
	long lastframe;
	long start;
	long window;
	int k;
	public static  boolean everysecond = false;

	public static int width = 100;
	public static int height =500;
	
	public static int[] textureatlas1;
	public static int ukkeli;
	public static int ukkeli2;
	
	public static int rwidth = 1366;
	public static int rheight = 768;
	
	Loader loader;
	GameState current;
	
	void changestate(GameState newstate) {
		if(current!=null)current.cleanUp();
		if(newstate!=null)newstate.init();
		current = newstate;
	}
	
	public main() {
		init();
		
		changestate(new MainMenu());
		
		System.out.println(System.nanoTime()/second);
		long start = System.nanoTime();
		lastframe = start;
		k  = 0;	
		GL11.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
		while(!GLFW.glfwWindowShouldClose(window)) {
			long currentframe = System.nanoTime();
			frames += (currentframe-lastframe);
			lastframe = currentframe;
			if(frames>frame*10) {
				System.out.println("you are behind by " + frames/frame);
			}
			while(frames > frame) {
				frames -= frame;
				k++;
				everysecond = k%1==0;
				current.tick();
			}
			current.render();
			GLFW.glfwSwapBuffers(window);
			GLFW.glfwPollEvents();
		}
		cleanUp();
		System.out.println("program ran for " + (System.nanoTime()-start)/second + " seconds, probably, totaling " + k + " frames, " + (System.nanoTime()-start)/second*60);
	}
	
	public void init() {
		GLFW.glfwInit();
		window = GLFW.glfwCreateWindow(rwidth, rheight, "he", 0, 0);
		GLFW.glfwMakeContextCurrent(window);
		GLFW.glfwSetFramebufferSizeCallback(window, new GLFWFramebufferSizeCallbackI() {
			
			@Override
			public void invoke(long arg0, int arg1, int arg2) {
				width = arg1;
				height = arg2;
				
			}
		});
		GL.createCapabilities();
		loader = new Loader();	

		//ukkeli = loader.loadTexture("res/textures/rölli.png");
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA); 
		GL11.glEnable(GL11.GL_BLEND);
		textureatlas1 = loader.loadTextureAtlas("res/textures/tileset.png",32,32);
		ukkeli = loader.loadTexture("res/textures/rölli.png");
		//textureatlas1 = loader.loadTextureAtlas("res/textures/tileset.png", 32, 32);
		
	}
	
	public void cleanUp() {
		changestate(null);
		GLFW.glfwDestroyWindow(window);
		GLFW.glfwTerminate();
		GL.destroy();
	}
	
}

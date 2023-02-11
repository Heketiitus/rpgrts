package rpgrts.main;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallbackI;
import org.lwjgl.glfw.GLFWFramebufferSizeCallbackI;
import org.lwjgl.glfw.GLFWKeyCallbackI;
import org.lwjgl.glfw.GLFWMouseButtonCallbackI;
import org.lwjgl.glfw.GLFWScrollCallbackI;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import rpgrts.Config;
import rpgrts.DisplayInfo;
import rpgrts.InputInfo;
import rpgrts.Loader;
import rpgrts.main.gamestates.Game;
import rpgrts.main.gamestates.GameState;
import rpgrts.main.gamestates.MainMenu;
import rpgrts.renderiing.MenuRenderinfo;

public class Main {
	
	public static void main(String[] args) {
		new Main();
	}
	
	static int targetfps;
	
	static final long second = 1000000000;
	static long frame;
	double frames = 0;
	long lastframe;
	long start;
	long window;
	int k;
	public static  boolean everysecond = false;
	
	public int rwidth,rheight;
	
	public static int width = 100;
	public static int height =500;
	
	//all of these "info" classes variables should probably just be static in the first place...
	public static MenuRenderinfo rendererinfo;
	public static DisplayInfo displayinfo;
	public static InputInfo inputinfo;
	
	
	
	Config config;
	
	Loader loader;
	static GameState current;
	static GameState newstate;
	static boolean changestate = false;
	
	static void changestate(GameState newstate) {
		if(current!=null)current.cleanUp();
		if(newstate!=null)newstate.init();
		current = newstate;
		changestate = false;
	}
	
	public static void requeststatechange(GameState newstate) {
		Main.newstate = newstate;
		changestate = true;
	}
	
	public static void startSingleplayer() {
		requeststatechange(new Game());
	}
	
	public Main() {
		init();
		rendererinfo = new MenuRenderinfo();
		rendererinfo.init();
		displayinfo = new DisplayInfo();
		displayinfo.updateScreen(rwidth, rheight);
		changestate(new MainMenu(rendererinfo));
		
		System.out.println(System.nanoTime()/second);
		long start = System.nanoTime();
		lastframe = start;
		k  = 0;	
		GL11.glClearColor(1.0f, 1.0f, 1.0f, 0.0f);
		while(!GLFW.glfwWindowShouldClose(window)) {
			long currentframe = System.nanoTime();
			frames += (currentframe-lastframe);
			lastframe = currentframe;
			if(frames>frame*targetfps) {
				System.out.println("you are behind by " + frames/frame);
			}
			while(frames > frame) {
				frames -= frame;
				k++;
				everysecond = k%60==0;
				current.tick();
				inputinfo.tick();
				if(changestate) {
					changestate(newstate);
				}
			}
			current.render();
			GLFW.glfwSwapBuffers(window);
			//this might be better to do in another thread, i think its because of this line that moving the window stops the game for the duration of movage but im not sure
			GLFW.glfwPollEvents();
		}
		cleanUp();
		System.out.println("program ran for " + (System.nanoTime()-start)/second + " seconds, probably, totaling " + k + " ticks, " + (System.nanoTime()-start)/second*targetfps);
	}
	
	public void init() {
		config = Config.loadConfig("config.txt");
		rwidth = config.getWidth();
		rheight = config.getHeight();
		
		targetfps = config.getTps();
		frame = second/targetfps;
		GLFW.glfwInit();
		window = GLFW.glfwCreateWindow(rwidth, rheight, "he", 0, 0);
		GLFW.glfwMakeContextCurrent(window);
		GLFW.glfwSetKeyCallback(window, new GLFWKeyCallbackI() {
			
			@Override
			public void invoke(long arg0, int keycode, int aaction, int arg3, int arg4) {
				System.out.println(arg0 + " keycode " + keycode + " " + aaction  + " " + arg3 + " " + arg4 + " " );
				inputinfo.setKey(keycode,arg3!=0);
				
				
			}
		});
		GLFW.glfwSetFramebufferSizeCallback(window, new GLFWFramebufferSizeCallbackI() {
			
			@Override
			public void invoke(long arg0, int arg1, int arg2) {
				
				displayinfo.updateScreen(arg1, arg2);
				
				
			}
		});
		GLFW.glfwSetScrollCallback(window, new GLFWScrollCallbackI() {
			
			@Override
			public void invoke(long arg0, double arg1, double arg2) {
				System.out.println(arg0 + " " + arg1 + " " + arg2);
				Main.inputinfo.scroll += arg2;
			}
		});
		inputinfo = new InputInfo();
		inputinfo.init();
		GLFW.glfwSetCursorPosCallback(window, new GLFWCursorPosCallbackI() {
			
			@Override
			public void invoke(long arg0, double arg1, double arg2) {
				Main.inputinfo.mouseX = arg1;
				Main.inputinfo.mouseY = arg2;
				
			}
		});
		//if this ends up making some code miss some mouse button callbacks, make another inputinfo class 
		//and at the start of every frame copy the contents into that
		GLFW.glfwSetMouseButtonCallback(window, new GLFWMouseButtonCallbackI() {
			
			@Override
			public void invoke(long arg0, int mousebutton, int state, int arg3) {
				System.out.println(mousebutton + " " + state + " " + arg3);
				boolean stateb = state!=0;
				inputinfo.clickheld[mousebutton] = stateb;
				if(stateb)inputinfo.click[mousebutton] = true;
			}
		});
		GL.createCapabilities();
		loader = new Loader();
		loader.loadTextures(config.getTextureLocation());
		
	}
	
	public void cleanUp() {
		changestate(null);
		rendererinfo.cleanUp();
		GLFW.glfwDestroyWindow(window);
		GLFW.glfwTerminate();
		GL.destroy();
	}
	
}

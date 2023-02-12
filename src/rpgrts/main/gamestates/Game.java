package rpgrts.main.gamestates;

import org.lwjgl.opengl.GL11;

import rpgrts.Camera;
import rpgrts.GameInfo;
import rpgrts.main.Main;
import rpgrts.rendering.GuiRenderInfo;
import rpgrts.rendering.TextRenderInfo;
import rpgrts.rendering.TileRenderInfo;
import rpgrts.world.World;

public class Game extends GameState {

	public static TileRenderInfo tilerenderinfo;
	public static GuiRenderInfo guirenderinfo;
	public static TextRenderInfo textrenderinfo;
	
	World world;
	Camera camera;
	GameInfo gi;
	boolean wireframe;
	public Game() {

	
	}

	@Override
	public void tick() {
		if(Main.inputinfo.isKeyPressed(256)) {
			wireframe = !wireframe;
			System.out.println("esc presed");
		}
		float speed;
		if(Main.inputinfo.isKeyToggled(340)) {
			speed = 0.3f;
		}else speed = 0.03f;
		if(Main.inputinfo.isKeyToggled(65)) {
			camera.x+=speed;
		}
		if(Main.inputinfo.isKeyToggled(68)) {
			camera.x-=speed;
		}
		if(Main.inputinfo.isKeyToggled(87)) {
			camera.y-=speed;
		}
		if(Main.inputinfo.isKeyToggled(83)) {
			camera.y+=speed;
		} 
		if(Main.inputinfo.isKeyToggled(73)) {
			camera.zoom -= camera.zoom*0.05; 
		}
		if(Main.inputinfo.isKeyToggled(79)) {
			camera.zoom += camera.zoom*0.05;
		}
		if(Main.inputinfo.isKeyPressed(49)) {
			textrenderinfo.c1 = !textrenderinfo.c1;
			System.out.println(textrenderinfo.c1 + " " + textrenderinfo.c2 + " " + textrenderinfo.c3);
		}
		if(Main.inputinfo.isKeyPressed(50)) {
			textrenderinfo.c2 = !textrenderinfo.c2;
			System.out.println(textrenderinfo.c1 + " " + textrenderinfo.c2 + " " + textrenderinfo.c3);
		}
		if(Main.inputinfo.isKeyPressed(51)) {
			textrenderinfo.c3 = !textrenderinfo.c3;
			System.out.println(textrenderinfo.c1 + " " + textrenderinfo.c2 + " " + textrenderinfo.c3);
		}
		if(Main.inputinfo.isKeyPressed(72)) {
			System.out.println("x " + camera.x + " y " + camera.y);
		}
		if(Main.inputinfo.scroll>0) {
			for(int i = Main.inputinfo.scroll; i > 0; i--) {
				camera.zoom += camera.zoom*0.1; 
			}
		}
		if(Main.inputinfo.scroll<0) {
			for(int i = Main.inputinfo.scroll; i < 0; i++) {
				camera.zoom -= camera.zoom*0.1;
			}
		}
		
	}
	boolean firstrender = true;
	@Override
	public void render() {

		GL11.glViewport(0, 0, (int)Main.displayinfo.getWidth(), (int)Main.displayinfo.getHeight());
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
		if(wireframe)
		GL11.glPolygonMode( GL11.GL_FRONT_AND_BACK, GL11.GL_LINE );
		else GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
		//world.render(camera, gi);
		
		textrenderinfo.renderText(0, camera.x,camera.y, 1000f/Main.displayinfo.getWidth(),1000f/Main.displayinfo.getHeight(), textrenderinfo.testgv);
		textrenderinfo.renderText(1, camera.x,camera.y, 1000f/Main.displayinfo.getWidth(),1000f/Main.displayinfo.getHeight(), textrenderinfo.testgv);
		textrenderinfo.renderText(2, camera.x,camera.y, 1000f/Main.displayinfo.getWidth(),1000f/Main.displayinfo.getHeight(), textrenderinfo.testgv);
		textrenderinfo.renderText(3, camera.x,camera.y, 1000f/Main.displayinfo.getWidth(),1000f/Main.displayinfo.getHeight(), textrenderinfo.testgv);
		textrenderinfo.renderText(4, camera.x,camera.y, 1000f/Main.displayinfo.getWidth(),1000f/Main.displayinfo.getHeight(), textrenderinfo.testgv);
		textrenderinfo.renderText(5, camera.x,camera.y, 1000f/Main.displayinfo.getWidth(),1000f/Main.displayinfo.getHeight(), textrenderinfo.testgv);
		textrenderinfo.renderText(6, camera.x,camera.y, 1000f/Main.displayinfo.getWidth(),1000f/Main.displayinfo.getHeight(), textrenderinfo.testgv);
		textrenderinfo.renderText(7, camera.x,camera.y, 1000f/Main.displayinfo.getWidth(),1000f/Main.displayinfo.getHeight(), textrenderinfo.testgv);
		textrenderinfo.renderText(8, camera.x,camera.y, 1000f/Main.displayinfo.getWidth(),1000f/Main.displayinfo.getHeight(), textrenderinfo.testgv);
		textrenderinfo.renderText(9, camera.x,camera.y, 1000f/Main.displayinfo.getWidth(),1000f/Main.displayinfo.getHeight(), textrenderinfo.testgv);
		textrenderinfo.renderText(10, camera.x,camera.y, 1000f/Main.displayinfo.getWidth(),1000f/Main.displayinfo.getHeight(), textrenderinfo.testgv);
		textrenderinfo.renderText(11, camera.x,camera.y, 1000f/Main.displayinfo.getWidth(),1000f/Main.displayinfo.getHeight(), textrenderinfo.testgv);
		textrenderinfo.renderText(12, camera.x,camera.y, 1000f/Main.displayinfo.getWidth(),1000f/Main.displayinfo.getHeight(), textrenderinfo.testgv);
		textrenderinfo.renderText(13, camera.x,camera.y, 1000f/Main.displayinfo.getWidth(),1000f/Main.displayinfo.getHeight(), textrenderinfo.testgv);
		textrenderinfo.renderText(14, camera.x,camera.y, 1000f/Main.displayinfo.getWidth(),1000f/Main.displayinfo.getHeight(), textrenderinfo.testgv);
		textrenderinfo.renderText(15, camera.x,camera.y, 1000f/Main.displayinfo.getWidth(),1000f/Main.displayinfo.getHeight(), textrenderinfo.testgv);
		textrenderinfo.renderText(16, camera.x,camera.y, 1000f/Main.displayinfo.getWidth(),1000f/Main.displayinfo.getHeight(), textrenderinfo.testgv);
		textrenderinfo.renderText(17, camera.x,camera.y, 1000f/Main.displayinfo.getWidth(),1000f/Main.displayinfo.getHeight(), textrenderinfo.testgv);
		textrenderinfo.renderText(18, camera.x,camera.y, 1000f/Main.displayinfo.getWidth(),1000f/Main.displayinfo.getHeight(), textrenderinfo.testgv);
		textrenderinfo.renderText(19, camera.x,camera.y, 1000f/Main.displayinfo.getWidth(),1000f/Main.displayinfo.getHeight(), textrenderinfo.testgv);
		textrenderinfo.renderText(20, camera.x,camera.y, 1000f/Main.displayinfo.getWidth(),1000f/Main.displayinfo.getHeight(), textrenderinfo.testgv);
		textrenderinfo.renderText(21, camera.x,camera.y, 1000f/Main.displayinfo.getWidth(),1000f/Main.displayinfo.getHeight(), textrenderinfo.testgv);
		textrenderinfo.renderText(22, camera.x,camera.y, 1000f/Main.displayinfo.getWidth(),1000f/Main.displayinfo.getHeight(), textrenderinfo.testgv);
		textrenderinfo.renderText(23, camera.x,camera.y, 1000f/Main.displayinfo.getWidth(),1000f/Main.displayinfo.getHeight(), textrenderinfo.testgv);
		textrenderinfo.renderText(24, camera.x,camera.y, 1000f/Main.displayinfo.getWidth(),1000f/Main.displayinfo.getHeight(), textrenderinfo.testgv);
		textrenderinfo.renderText(25, camera.x,camera.y, 1000f/Main.displayinfo.getWidth(),1000f/Main.displayinfo.getHeight(), textrenderinfo.testgv);
		textrenderinfo.renderText(26, camera.x,camera.y, 1000f/Main.displayinfo.getWidth(),1000f/Main.displayinfo.getHeight(), textrenderinfo.testgv);
		textrenderinfo.renderText(27, camera.x,camera.y, 1000f/Main.displayinfo.getWidth(),1000f/Main.displayinfo.getHeight(), textrenderinfo.testgv);
		textrenderinfo.renderText(28, camera.x,camera.y, 1000f/Main.displayinfo.getWidth(),1000f/Main.displayinfo.getHeight(), textrenderinfo.testgv);
		textrenderinfo.renderText(29, camera.x,camera.y, 1000f/Main.displayinfo.getWidth(),1000f/Main.displayinfo.getHeight(), textrenderinfo.testgv);
		textrenderinfo.renderText(30, camera.x,camera.y, 1000f/Main.displayinfo.getWidth(),1000f/Main.displayinfo.getHeight(), textrenderinfo.testgv);
		textrenderinfo.renderText(31, camera.x,camera.y, 1000f/Main.displayinfo.getWidth(),1000f/Main.displayinfo.getHeight(), textrenderinfo.testgv);
		textrenderinfo.renderText(32, camera.x,camera.y, 1000f/Main.displayinfo.getWidth(),1000f/Main.displayinfo.getHeight(), textrenderinfo.testgv);
		textrenderinfo.renderText(33, camera.x,camera.y, 1000f/Main.displayinfo.getWidth(),1000f/Main.displayinfo.getHeight(), textrenderinfo.testgv);
		textrenderinfo.renderText(34, camera.x,camera.y, 1000f/Main.displayinfo.getWidth(),1000f/Main.displayinfo.getHeight(), textrenderinfo.testgv);
		firstrender = false;
	}
	
	
	@Override
	public void init() {

		GL11.glClearColor(0.85f, 0.85f, 0.85f, 0.0f);
		tilerenderinfo = new TileRenderInfo();
		tilerenderinfo.init();
		guirenderinfo = new GuiRenderInfo();
		guirenderinfo.init();
		textrenderinfo = new TextRenderInfo();
		textrenderinfo.init("res/textures/");
		gi = new GameInfo();
		gi.initTiles(); 
		world = new World(5,5,32,32);
		for(int x = 0; x < world.width; x++) {
			for(int y = 0; y < world.height; y++) {
				world.createChunk(x, y);
			}
		}
		camera = new Camera();
		
	}

	@Override
	public void cleanUp() {
		System.out.println("cleaning up game");
		world.cleanUp();
		
	}

}

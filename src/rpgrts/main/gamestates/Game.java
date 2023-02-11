package rpgrts.main.gamestates;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import rpgrts.Camera;
import rpgrts.GameInfo;
import rpgrts.main.Main;
import rpgrts.rendering.GuiRenderInfo;
import rpgrts.rendering.TileRenderInfo;
import rpgrts.world.World;

public class Game extends GameState {

	public static TileRenderInfo tilerenderinfo;
	public static GuiRenderInfo guirenderinfo;
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

	@Override
	public void render() {

		GL11.glViewport(0, 0, (int)Main.displayinfo.getWidth(), (int)Main.displayinfo.getHeight());
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
		if(wireframe)
		GL11.glPolygonMode( GL11.GL_FRONT_AND_BACK, GL11.GL_LINE );
		else GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
		world.render(camera, gi);

		guirenderinfo.renderGui(0, 0, 25, 20, 64);
	}
	
	
	@Override
	public void init() {

		GL11.glClearColor(0.85f, 0.85f, 0.85f, 0.0f);
		tilerenderinfo = new TileRenderInfo();
		tilerenderinfo.init();
		guirenderinfo = new GuiRenderInfo();
		guirenderinfo.init();
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

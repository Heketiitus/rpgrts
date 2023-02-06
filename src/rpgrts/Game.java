package rpgrts;

import org.lwjgl.opengl.GL11;

import rpgrts.main.Main;
import rpgrts.world.World;

public class Game extends GameState {

	public static TileRenderInfo tilerenderinfo;
	World world;
	Camera camera;
	GameInfo gi;
	boolean wireframe;
	
	public Game() {

	
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		if(Main.inputinfo.isKeyPressed(256)) {
			wireframe = !wireframe;
			System.out.println("esc presed");
		}
		float speed;// = 0.3f;
		if(Main.inputinfo.isKeyToggled(340)) {
			speed = 0.6f;
		}else speed = 0.3f;
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
		int[] stwp = world.ScreenToWorldPosition(camera, Main.inputinfo.mouseX, Main.inputinfo.mouseY);
		
		System.out.println(stwp[0] + " " + stwp[1] + " " + stwp[2] + " " + stwp[3] + " " + stwp[4] + " " + stwp[5]);
	}

	@Override
	public void render() {

		GL11.glViewport(0, 0, (int)Main.displayinfo.getWidth(), (int)Main.displayinfo.getHeight());
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
		if(wireframe)
		GL11.glPolygonMode( GL11.GL_FRONT_AND_BACK, GL11.GL_LINE );
		else GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
		world.render(camera, gi);
		
	}

	@Override
	public void init() {
		tilerenderinfo = new TileRenderInfo();
		tilerenderinfo.init();
		gi = new GameInfo();
		gi.initTiles(); 
		world = new World(8,8,16,16);
		for(int x = 0; x < world.width; x++) {
			for(int y = 0; y < world.height; y++) {
				world.createChunk(x, y);
			}
		}
		camera = new Camera();
		
	}

	@Override
	public void cleanUp() {
		// TODO Auto-generated method stub
		
	}

}

package rpgrts.main.gamestates;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import rpgrts.Loader;
import rpgrts.gui.Button;
import rpgrts.gui.MenuElement;
import rpgrts.gui.HiddenMenuButton;
import rpgrts.main.Main;
import rpgrts.rendering.MenuRenderinfo;

public class MainMenu extends Menu {
	float x = 0;
	float y = 0;
	public MainMenu(MenuRenderinfo renderer) {
		super(renderer);
	}
	
	HiddenMenuButton playbutton;
	Button settingsbutton;
	Button multiplayerbutton;
	Button singleplayerbutton;
	
	public void init() {
		
		playbutton = new HiddenMenuButton(Loader.playbutton, 24.0f/256.0f, 47.0f/144.0f,40.0f/256.0f,20.0f/144.0f);
		//the spider makes the hitbox bigger but the spider is going to be deleted when the settings button works
		settingsbutton = new Button(Loader.settingsbutton, 129.0f/256.0f, 91.0f/144.0f,94.0f/256.0f,40.0f/144.0f);//101,29
		multiplayerbutton = new Button(Loader.multiplayerbutton,4.0f/256.0f,80.0f/144.0f,74.0f/256.0f,9.0f/144.0f);
		singleplayerbutton = new Button(Loader.singleplayerbutton,22.0f/256.0f,107.0f/144.0f,83.0f/256.0f,10.0f/144.0f);
		playbutton.add(new MenuElement() {
			final float width = 43.0f/256.0f;
			final float height = 41.0f/144.0f;
			final float x = 30.0f/256.0f+width/2;
			final float y = 107.0f/144.0f-height/2;
			@Override
			public void update() {
				
			}
			
			@Override
			public void render(MenuRenderinfo r) {
				// TODO Auto-generated method stub
				//warning this i copied from button
				GL30.glBindVertexArray(r.squareVAO);
				GL20.glUseProgram(r.shader.program);
				GL11.glEnable(GL11.GL_TEXTURE_2D);
				GL20.glUniform4fv(r.sizeuniform, new float[] {width,height,1.0f,1.0f});
				GL20.glUniform4fv(r.positionuniform, new float[] {x*2-1,y*2-1,0.0f,0.0f});
				
				GL11.glBindTexture(GL11.GL_TEXTURE_2D,Loader.multiplayersingleplayerbranch);	 
				
		        GL11.glDrawElements(GL11.GL_TRIANGLES, 6, GL11.GL_UNSIGNED_INT, 0);
		        
				GL11.glDisable(GL11.GL_TEXTURE_2D);
				GL20.glUseProgram(0);
			}
			
			@Override
			public void init() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void cleanUp() {
				// TODO Auto-generated method stub
				
			}
		});
		playbutton.add(multiplayerbutton);
		playbutton.add(singleplayerbutton);
		menuelements.add(playbutton);
		menuelements.add(settingsbutton);
	}
	
	public void cleanUp() {
		
	}
	@Override
	public void tick() {
		//preserve this code for now
		/*size = ratio*0.1f;
		x = -20+(float)texture*0.1f;
		y = 0;
		if(main.everysecond) texture++;
		if(x*size*widthm>2)texture=0;*/
		for(MenuElement me:menuelements) {
			me.update();
		}
		if(multiplayerbutton.pressed) {
			
		}
		if(singleplayerbutton.pressed) {
			Main.startSingleplayer();
		}
		if(Main.everysecond) texture++;
		if(texture==2)texture=0;
	}
	int texture = 0;
	@Override
	public void render() {
		GL11.glViewport(0, 0, (int)Main.displayinfo.getWidth(), (int)Main.displayinfo.getHeight());
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);	
		
		GL30.glBindVertexArray(renderer.squareVAO);
		GL20.glUseProgram(renderer.shader.program);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL20.glUniform4fv(renderer.sizeuniform, new float[] {1.0f,1.0f,1.0f,1.0f});
		GL20.glUniform4fv(renderer.positionuniform, new float[] {0.0f,0.0f,0.0f,0.0f});
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, Loader.menubackground[texture]);
		GL11.glDrawElements(GL11.GL_TRIANGLES, 6, GL11.GL_UNSIGNED_INT, 0);
		for(MenuElement me:menuelements) {
			me.render(renderer);
		}


	}

}

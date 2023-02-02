package rpgrts;

public abstract class GameState {

	Renderer renderer;
	
	public GameState(Renderer renderer) {
		this.renderer = renderer;
	}
	
	public abstract void tick();
	public abstract void render();
	public abstract void init();
	public abstract void cleanUp();
}

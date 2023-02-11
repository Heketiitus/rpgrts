package rpgrts.main.gamestates;

public abstract class GameState {

	
	public GameState() {
		
	}
	
	public abstract void tick();
	public abstract void render();
	public abstract void init();
	public abstract void cleanUp();
}

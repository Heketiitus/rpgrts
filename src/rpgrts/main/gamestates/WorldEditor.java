package rpgrts.main.gamestates;



public class WorldEditor extends GameState{
	
	Game game;
	
	public WorldEditor(Game game) {
		
		this.game = game;
	}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		
		game.render();
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cleanUp() {
		// TODO Auto-generated method stub
		
	}

}

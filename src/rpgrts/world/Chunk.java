package rpgrts.world;

public class Chunk {
	Tile[][] tiles;
	
	public Chunk(int width, int height) {
		tiles = new Tile[width][height];
	}
}

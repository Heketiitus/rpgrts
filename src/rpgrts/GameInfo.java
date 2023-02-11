package rpgrts;

import rpgrts.renderiing.Texture;
import rpgrts.world.Tile;

public class GameInfo {

	public Tile[] tiles;
	public void initTiles() {
		tiles = new Tile[64];
		for(int i = 0; i < 64; i++) {
			
			tiles[i] = new Tile(new Texture(Loader.tileset,8,8,i), i, i);
		}
	}
}

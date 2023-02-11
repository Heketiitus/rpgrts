package rpgrts.world;

import rpgrts.rendering.Texture;

//tile specification
public class Tile {
	Texture texture;
	int VBO;
	int VAO;
	public Tile(Texture texture, int VBO, int VAO) {
		this.texture = texture;
		this.VBO = VBO;
		this.VAO = VAO;
	}
}

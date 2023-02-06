package rpgrts;

public class Texture {

	int texture;
	int texturewidth;
	int textureheight;
	int textureindex;
	
	public int getTexture() {
		return texture;
	}
	//auto generated getters & setters
	public void setTexture(int texture) {
		this.texture = texture;
	}

	public int getTexturewidth() {
		return texturewidth;
	}

	public void setTexturewidth(int texturewidth) {
		this.texturewidth = texturewidth;
	}

	public int getTextureheight() {
		return textureheight;
	}

	public void setTextureheight(int textureheight) {
		this.textureheight = textureheight;
	}

	public int getTextureindex() {
		return textureindex;
	}

	public void setTextureindex(int textureindex) {
		this.textureindex = textureindex;
	}

	public Texture(int texture, int width, int height, int index) {
		this.texture = texture;
		this.texturewidth = width;
		this.textureheight = height;
		this.textureindex = index;
		System.out.println("textureindex " + getTextureindex());
	}
	
}

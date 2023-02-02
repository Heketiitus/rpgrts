package rpgrts;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.TileObserver;
import java.awt.image.WritableRenderedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL30;

public class Loader {

	public int loadTexture(String src) {
		File file = new File(src);
		if(file.exists()) {
			try {
				BufferedImage img = ImageIO.read(file);
				ByteBuffer image = BufferUtils.createByteBuffer(img.getWidth() * img.getHeight() * 4);
				for(int y = img.getHeight()-1; y >= 0; y--) {
					for(int x = 0; x < img.getWidth(); x++) {
						int pixel = img.getRGB(x, y);
						image.put((byte) ((pixel >> 16) & 0xFF));     // Red component
						image.put((byte) ((pixel >> 8) & 0xFF));      // Green component
						image.put((byte) (pixel & 0xFF));               // Blue component
						image.put((byte) ((pixel >> 24) & 0xFF)); 

					}
				}
				image.flip();
				GL13.glActiveTexture(GL13.GL_TEXTURE0);
				int texture = GL11.glGenTextures();
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
				//Setup wrap mode copied code
				GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
		        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);

		        //Setup texture scaling filtering
		        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
				GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, img.getWidth(), img.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, image);
				System.out.println("texture " + texture + " created?");
				
				return texture;
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else {
			System.out.println("could not find file " + src);
		}
		return 0;
	}
	//TODO do this better
	public int[] loadTextureAtlas(String src, int iw,int ih) {
		File file = new File(src);
		if(file.exists()) {
			try {
				BufferedImage img = ImageIO.read(file);
				int[] textures = new int[(img.getWidth()/iw)*(img.getHeight()/ih)];
				for(int y = 0; y < img.getHeight()/ih;y++) {
					for(int x = 0; x < img.getWidth()/iw;x++) {
						ByteBuffer image = BufferUtils.createByteBuffer(iw * ih * 4);
						int startx = x*iw;
						int starty = y*ih;
						for(int y2 = ih-1; y2 >= 0; y2--) {
							for(int x2 = 0; x2 < iw; x2++) {
								int pixel = img.getRGB(startx+x2, starty+y2);
								image.put((byte) ((pixel >> 16) & 0xFF));     // Red component
								image.put((byte) ((pixel >> 8) & 0xFF));      // Green component
								image.put((byte) (pixel & 0xFF));               // Blue component
								image.put((byte) ((pixel >> 24) & 0xFF)); 

							}
						}
						image.flip();
						//if((x+y*(img.getWidth()/iw)<63)) {
						GL13.glActiveTexture(GL13.GL_TEXTURE0);
						int texture = GL11.glGenTextures();
						//Setup wrap mode copied code
						GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture);
						GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
				        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);

				        //Setup texture scaling filtering
				        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
				        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
						
						GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, iw, ih, 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, image);
						textures[x+y*(img.getWidth()/iw)] = texture;
						System.out.println("image at"+(x+y*(img.getWidth()/iw))+" set");
						//}
					}
				}return textures;
			}catch(IOException e) {
				e.printStackTrace();
			}
		}else {
			System.out.println("could not find file " + src);
		}return null;
	}

}

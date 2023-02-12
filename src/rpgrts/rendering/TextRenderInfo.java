package rpgrts.rendering;

import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.PathIterator;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import rpgrts.Loader;

public class TextRenderInfo {

	public Font test;
	public GlyphVector testgv;
	public boolean c1 = true;
	public boolean c2 = true;
	public boolean c3 = true;
	
	public void init(String sourcefolder) {

		test = Loader.loadFont(sourcefolder+"fonts/Paper-SnowFlakes.otf",Font.TRUETYPE_FONT);
		//test = Loader.loadFont(sourcefolder+"fonts/Wall Display Free 100%.ttf",Font.TRUETYPE_FONT);
		System.out.print("font glyph count " + test.getNumGlyphs());
		testgv = test.createGlyphVector(new FontRenderContext(test.getTransform(), true, true),"abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890.:,;'\"(!?)+-*/=");
		System.out.println(testgv.getGlyphCharIndex('a'));
		System.out.println("character path");
		PathIterator pi = testgv.getGlyphOutline(7).getPathIterator(test.getTransform());
		while(!pi.isDone()) {
			double[] coords = new double[6];
			
			pi.currentSegment(coords);
			for(int i = 0; i < coords.length; i++) {
				System.out.println(i + " " + coords[0]);
			}
			pi.next();
		}
		
	}
	
	public void renderText(int text, float x, float y, float width, float height ,GlyphVector f) {
		PathIterator pi = testgv.getGlyphOutline(text).getPathIterator(null);
		GL20.glUseProgram(0);
		GL11.glLineWidth(1);
		GL11.glBegin(GL11.GL_LINES);
		double lastx = 0;
		double lasty = 0;
		double movetox = 0;
		double movetoy = 0;
		while(!pi.isDone()) {
			double[] coords = new double[6];
			int segment = pi.currentSegment(coords);
			//System.out.println(segment + " " + (segment==PathIterator.SEG_CLOSE?"seg_close":segment==PathIterator.SEG_CUBICTO?"seg_cubicto":segment==PathIterator.SEG_LINETO?"seg_lineto":segment==PathIterator.SEG_MOVETO?"seg_moveto":segment==PathIterator.SEG_QUADTO?"seg_quaddto":"what"));
			
			switch(segment) {
		
			case PathIterator.SEG_CUBICTO:
				GL11.glColor4f(0.0f, 1.0f, 0.0f,1.0f);
				GL11.glVertex2d(lastx*width+x, lasty*height+y);
				GL11.glVertex2d(coords[0]*width+x, coords[1]*height+y);
				GL11.glVertex2d(coords[0]*width+x, coords[1]*height+y);
				GL11.glVertex2d(coords[2]*width+x, coords[3]*height+y);
				GL11.glVertex2d(coords[2]*width+x, coords[3]*height+y);
				GL11.glVertex2d(coords[4]*width+x, coords[5]*height+y);
				lastx = coords[4];
				lasty = coords[5];
				
				break;
			case PathIterator.SEG_LINETO:
				GL11.glColor4f(1.0f, 0.0f, 0.0f,1.0f);
				GL11.glVertex2d(lastx*width+x, lasty*height+y);
				GL11.glVertex2d(coords[0]*width+x, coords[1]*height+y);
				lastx = coords[0];
				lasty = coords[1];
				break;
			case PathIterator.SEG_MOVETO:
				GL11.glBegin(GL11.GL_LINES);
				movetox = coords[0];
				movetoy = coords[1];
				lastx = coords[0];
				lasty = coords[1];
				
				break;
			case PathIterator.SEG_QUADTO:

				GL11.glVertex2d(lastx*width+x, lasty*height+y);
				GL11.glVertex2d(coords[0]*width+x, coords[1]*height+y);

				GL11.glVertex2d(coords[0]*width+x, coords[1]*height+y);
				GL11.glVertex2d(coords[2]*width+x, coords[3]*height+y);

				lastx = coords[2];
				lasty = coords[3];
				break;
			case PathIterator.SEG_CLOSE:
				if(movetox!=0&&movetoy!=0) {
					GL11.glColor4f(1.0f, 1.0f, 1.0f,1.0f);
					GL11.glVertex2d(movetox*width+x, movetoy*height+y);
					GL11.glEnd();
				}
				break;
			}
			pi.next();
		}
		GL11.glEnd();
	}
	
}

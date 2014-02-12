package data;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.*;

public class Boot {
	
	public static void create(int width, int height){
		Display.setTitle("Ethan's Game");
		try {
			Display.setDisplayMode(new DisplayMode(width, height));
			Display.create();
			initGL();
			initInput();
			
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
	}//end boot constructor
	
	public static void initGL(){
		glClearColor(0, 0.7f, 0.1f, 1);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Display.getWidth(), 0, Display.getHeight(), -1, 1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glLoadIdentity();
		
	}
	
	private static void initInput(){
		
		try {
			Keyboard.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	public static void update(){
		
		Display.update();
		Display.sync(60);
	}//end update
	
	public static void destroy(){
		Keyboard.destroy();
		Display.destroy();
		System.exit(0);
		
	}
	
	public static boolean isCloseRequested(){
		return Display.isCloseRequested();
	}//end isCloseRequested
	
	
	public static void clear(){
		glClear(GL_COLOR_BUFFER_BIT);
		
	}
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	

}

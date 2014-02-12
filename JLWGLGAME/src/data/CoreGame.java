package data;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glColor3f;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2i;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.*;

public class CoreGame {

	public final static int TILE_SIZE = 64; 
	
	private static Player player;
	public static Image image;
	
	public CoreGame(){
		image = new Image("PlayerRightClosed");
		player = new Player();
		
		
		
		
	}
	

	
	public void input(){
		
		player.input();
	}
	
	public void logic(){

		player.logic();
	}
	
	public void render(){
		
		
		player.render();
		
	}
	
	public void dispose(){
		
		player.dispose();
		Image.dispose();
	}
	
	
}

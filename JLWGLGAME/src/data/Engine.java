package data;

import static org.lwjgl.opengl.GL11.GL_QUADS;

import org.lwjgl.opengl.*;
import org.lwjgl.*;
import org.newdawn.slick.opengl.Texture;
import static org.lwjgl.opengl.GL11.*;

public class Engine {
	
	private static CoreGame coreGame;
	
	
	public static void main(String[] args){
		createWindow();
		createGame();
		gameLoop();
		
		cleanUp();
	}
	
	public static void createWindow(){
		Boot.create(800,600);
	}
	
	private static void createGame(){
		coreGame = new CoreGame();
	}
	
	
	public static void gameLoop(){
		while(!Display.isCloseRequested()){
			Boot.clear();
			coreGame.input();
			coreGame.logic();
			coreGame.render();
			Boot.update();
		}
	}
	
	private static void cleanUp(){
		
		coreGame.dispose();
		
		Boot.destroy();
	}
	
	
	

}

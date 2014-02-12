package data;

import java.io.IOException;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;

public class Player {
	
	private final int NUM_VERTICES = 6;
	private final int DIMENSIONS = 3;
	private final int TEX_COORDS = 2;
	
	private int vboVertexHandle;
	private int vboTexCoordHandle;
	
	
	protected int x = 0;
	protected int y = 0;
	
	private boolean moveUp;
	private boolean moveDown;
	private boolean moveLeft;
	private boolean moveRight;
	
	private int count = 0;
	
	private String lastOpen= "PlayerRightOpen";
	private String lastClosed= "PlayerRightClosed";
	
	public Player(){
		
		
		
		FloatBuffer vertexData = BufferUtils.createFloatBuffer(NUM_VERTICES*DIMENSIONS);
		vertexData.put(new float[] {x, y, 0, x + CoreGame.TILE_SIZE, y, 0, x, y + CoreGame.TILE_SIZE, 0, x + CoreGame.TILE_SIZE, y + CoreGame.TILE_SIZE, 0, x, y + CoreGame.TILE_SIZE, 0, x + CoreGame.TILE_SIZE, y, 0});
		vertexData.flip();
		
		FloatBuffer textureCoordData = BufferUtils.createFloatBuffer(NUM_VERTICES * TEX_COORDS);
		textureCoordData.put(new float[] {0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 1} );
		textureCoordData.flip();
		
		vboVertexHandle = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle);
		glBufferData(GL_ARRAY_BUFFER, vertexData, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
		vboTexCoordHandle = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vboTexCoordHandle);
		glBufferData(GL_ARRAY_BUFFER, textureCoordData, GL_STATIC_DRAW);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
		
	}
	
	
	public void input(){
		
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			moveUp = true;
		}
		
		else if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			moveDown = true;
		}
		
		else if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			moveLeft = true;
		}
		
		else if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			moveRight = true;
		}
		else
		{
			moveUp = false;
			moveDown = false;
			moveLeft = false;
			moveRight = false;
		}
		
	}
	
	
	
	
	
	
	public void logic(){
		
		
		if (moveUp==false && moveDown==false && moveLeft == false && moveRight == false){
			CoreGame.image = new Image(lastClosed);
		}
		else if(moveUp && y<530){
			if(count%2==0){
			CoreGame.image = new Image(lastOpen);
			y += 2;
			count++;
			}
			else if(count%2!=0){
				CoreGame.image = new Image(lastClosed);
				y+=2;
				count++;
			}
		}
		else if(moveDown && y>0){
			if(count%2==0){
			CoreGame.image = new Image(lastOpen);
			y -= 2;
			count++;
			}
			else if (count%2!=0){
			CoreGame.image = new Image(lastClosed);
			y-=2;
			count++;
			}
		}
		else if(moveLeft && x>=0){
			lastClosed = "PlayerLeftClosed";
			lastOpen = "PlayerLeftOpen";
			if(count%2==0){
			CoreGame.image = new Image("PlayerLeftOpen");
			x -= 4;
			count++;
			}
			else if(count%2!=0){
				CoreGame.image = new Image("PlayerLeftClosed");
				x -= 4;
				count++;
			}
			
			
		}
			
		else if(moveRight && x<740){
			lastClosed = "PlayerRightClosed";
			lastOpen = "PlayerRightOpen";
			if(count%2==0){
			CoreGame.image = new Image("PlayerRightOpen");
			x += 4;
			count++;
			}
			else if(count%2!=0){
				CoreGame.image = new Image("PlayerRightClosed");
				x += 4;
				count++;
			}
			
			
		}
		
		
		
		
	}
	
	
	
	public void render(){
		
		
		
		
		glBindTexture(GL_TEXTURE_2D, CoreGame.image.playerID);
		glBindBuffer(GL_ARRAY_BUFFER, vboVertexHandle);
		glVertexPointer(DIMENSIONS, GL_FLOAT, 0, 0L);
		
		glBindBuffer(GL_ARRAY_BUFFER, vboTexCoordHandle);
		glTexCoordPointer(TEX_COORDS, GL_FLOAT, 0, 0L);
		
		glEnableClientState(GL_VERTEX_ARRAY);
		glEnableClientState(GL_TEXTURE_COORD_ARRAY);
		
		glPushMatrix();
		glTranslatef(x, y, 0);
		glDrawArrays(GL_TRIANGLES, 0, NUM_VERTICES);
		glPopMatrix();
		glDisableClientState(GL_VERTEX_ARRAY);
		glDisableClientState(GL_TEXTURE_COORD_ARRAY);
		
		
		
		
	}
	
	public void dispose(){
		
		
		glDeleteBuffers(vboVertexHandle);
		glDeleteBuffers(vboTexCoordHandle);
		
		
	}
	
	
}

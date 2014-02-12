import org.lwjgl.opengl.Display;
import org.lwjgl.input.Mouse;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


public class Menu extends BasicGameState {
	
	String mouse = "";
	
	Image menuAshe;
	Image playButton;
	Image title;
	Image author;
	
	
	public Menu(int state){
		
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		title = new Image("res/Title.png");
		menuAshe = new Image("res/menuAshe.png");
		playButton = new Image("res/Play-now-Button.png");
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		//g.drawString(mouse, 10,200);
		g.drawImage(title, Display.getWidth()/2-150, 20);
		g.drawImage(menuAshe, Display.getWidth()-350, 0);
		g.drawImage(playButton, Display.getWidth()/2-550, Display.getHeight()/2);
	
	
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		Input input = gc.getInput();
		int xpos = Mouse.getX();
		int ypos = Mouse.getY();
		mouse = "Mouse - X: "+xpos+"  Y: "+ypos;
		//debugging purposes ^^^
		
		
		if((ypos<348 && ypos>67) && (xpos< 1210 && xpos> 99 )){
			if(input.isMouseButtonDown(0)){
			sbg.enterState(1);
		}
		}
		
	}
	
	public int getID(){
		return 0;
	}
	
	
	

}

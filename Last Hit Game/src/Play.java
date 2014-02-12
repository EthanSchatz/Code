import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;


public class Play extends BasicGameState{
	
	
	Image BFSword;
	boolean BFSwordChosen = false;
	Image DoransBlade;
	boolean DoransBladeChosen = false;
	Image MeleeMinion;
	Image CasterMinion;
	Image CannonMinion;
	Image background;
	Image menu;
	boolean showMenu = false;
	
	int AD = 49;
	int level = 1;
	
	int minionsKilled = 0;
	
	String mouse = "";
	
	
	
	

	
	public Play(int state){
		
		
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException{
		background = new Image("res/background.jpg");
		MeleeMinion = new Image("res/meleeMinion.png");
		CasterMinion = new Image("res/casterMinion.png");
		CannonMinion = new Image("res/cannonMinion.png");
		menu = new Image("res/menu.png");
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException{
		g.drawImage(background, 0, 0);
		if(!BFSwordChosen){
		BFSword = new Image("res/BFSword.png");
		}else if(BFSwordChosen){
			BFSword = new Image("res/BFSwordChosen.png");}
		if(!DoransBladeChosen){
		DoransBlade = new Image("res/DoransBlade.png");
		}else if(DoransBladeChosen){
			DoransBlade = new Image("res/DoransBladeChosen.png");
		}
		g.drawString("Click an item to equip it. Click again to unequip it.", 20, 50);
		g.drawImage(DoransBlade, 20, 100);
		g.drawString("+8 Attack Damage.", 100, 130);
		g.drawImage(BFSword, 20, 200);
		g.drawString("+45 Attack Damage.", 100, 230);
		g.drawString("Your current Attack Damage: "+AD, 10, 320);
		g.drawString("Your current Level: "+level, 10, 340);
		
		
		g.drawString(mouse, 10,300);
		
		
		
		
		
		if(showMenu){
			g.drawImage(menu,0,0);
		}
		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException{
		Input input = gc.getInput();
		int xpos = Mouse.getX();
		int ypos = Mouse.getY();
		mouse = "Mouse - X: "+xpos+"  Y: "+ypos;
		//debugging purposes ^^^
		
		if((ypos<620 && ypos>557) && (xpos< 85 && xpos> 20 )){
			if(input.isMousePressed(0)){
				if(DoransBladeChosen){
					DoransBladeChosen = false;
					AD -= 8;
				}
				else if (!DoransBladeChosen){
					DoransBladeChosen = true;
					AD += 8;
				}
		}
		}
		
		if((ypos<520 && ypos>455) && (xpos< 85 && xpos> 20 )){
			if(input.isMousePressed(0)){
				if(BFSwordChosen){
					BFSwordChosen = false;
					AD -= 45;
				}
				else if (!BFSwordChosen){
					BFSwordChosen = true;
					AD += 45;
				}
		}
		}
		
		if(input.isKeyPressed(Keyboard.KEY_ESCAPE)){
			if(showMenu) showMenu = false;
			else if(!showMenu){
				showMenu = true;
				
			}
		}
		
		
		
		
		
		
	}
	
	public int getID(){
		return 1;
	}
	
	
	
}

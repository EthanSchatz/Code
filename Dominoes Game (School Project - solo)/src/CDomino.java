/*
 * CS350
 * Project #6
 * Ethan Schatzline
 * The object class for each domino used in DominoesServer.java and DominoesClient.java
 */


import java.awt.*;

import javax.swing.JPanel;

public class CDomino extends JPanel{
	private boolean show;
	private String list;
	private int index;
	private int position =1;
	private int num1;
	private int num2;
	private int x1;
	private int y1;
	private int[][] topcircles;
	private int[][] botcircles;
	private int topxpos;
	private int topypos;
	private int botxpos;
	private int botypos;
	private boolean moved;
	//used this matrix to place dots on the dominoes
	private int [][][] spot_matrix = {
			{{0,0,0},{0,0,0},{0,0,0}},  //blank
			{{0,0,0},{0,1,0},{0,0,0}},  //1 spot
			{{1,0,0},{0,0,0},{0,0,1}},  //2
			{{1,0,0},{0,1,0},{0,0,1}},  //3
			{{1,0,1},{0,0,0},{1,0,1}},  //4
			{{1,0,1},{0,1,0},{1,0,1}},  //5
			{{1,0,1},{1,0,1},{1,0,1}},  //6
	};
	
	
	public CDomino(int x, int y, int numSpots1, int numSpots2, String list, int index){
		position =1;
		this.index = index;
		this.list= list;
		num1 = numSpots1;
		num2= numSpots2;
		x1= x;
		y1= y;
		topcircles = spot_matrix[num1];
		botcircles = spot_matrix[num2];
		moved= false;
		
		}
	
	public int getTopxpos() {
		return topxpos;
	}

	public boolean getshow(){
		return show;
	}
	public void setshow(boolean bool){
		show = bool;
	}
	public void draw( Graphics g)
	   {
	      g.setColor(Color.MAGENTA);
	      g.drawLine(0,100, 900, 100);
	      g.drawLine(0, 500, 900, 500);
	      ///////////////////////////////
	     switch(position){
	     case 1:
	      botxpos=x1-15;
	      botypos=y1+5;
	      topxpos=x1-15;
	      topypos=y1-32;
	      Polygon p1 = new Polygon();
	      p1.addPoint(x1-17,y1-35);
	      p1.addPoint(x1+17,y1-35);
	      p1.addPoint(x1+17,y1+35);
	      p1.addPoint(x1-17,y1+35);
	      g.setColor(Color.lightGray);
	      g.fillPolygon(p1); 	     
	      g.setColor(Color.BLUE);
	      g.drawPolygon(p1);
	      g.setColor(Color.RED);
	      g.drawLine(x1-17, y1, x1+17, y1);
	      //g.drawString(""+num1+"", x1-3, y1-15);
	      //g.drawString(""+num2+"",x1-3, y1+15);
	      
	      
	      for(int i=0;i<3;i++){
	    	  topypos = y1-31 + (10*i); 
	    	  for(int j=0;j<3;j++){
	    		 topxpos = x1-14 + (10*j);
	    		 if(topcircles[i][j]==1){
	    			 if(show!= false){
	    				 g.fillOval(topxpos, topypos, 7, 7);
	    			 } 
	    			 	if(moved==true){
	    			 		g.fillOval(topxpos,  topypos,  7, 7);
	    			 	}
	    		 }
	    	  }
	      }
	      for(int i=0;i<3;i++){
	    	  botypos = y1+4 + (10*i); 
	    	  for(int j=0;j<3;j++){
	    		 botxpos = x1-14 + (10*j);
	    		 if(botcircles[i][j]==1){
	    			 if(show!= false){
	    				 g.fillOval(botxpos, botypos, 7, 7);
	    			 }
	    			 if(moved==true){
	    			 		g.fillOval(botxpos,  botypos,  7, 7);
	    			 	}}
	    	  }
	      }
  
	      
	      
	      
	      break;
	     case 2:
		      Polygon p2 = new Polygon();
		      p2.addPoint(x1-35,y1-17);
		      p2.addPoint(x1+35,y1-17);
		      p2.addPoint(x1+35,y1+17);
		      p2.addPoint(x1-35,y1+17);
		      g.setColor(Color.lightGray);
		      g.fillPolygon(p2); 	     
		      g.setColor(Color.BLUE);
		      g.drawPolygon(p2);
		      g.setColor(Color.RED);
		      g.drawLine(x1, y1+17, x1, y1-17);
		      
		      for(int i=0;i<3;i++){
		    	  topxpos = x1+4 + (10*i);
		    	  for(int j=0;j<3;j++){
		    		  topypos = y1-14 + (10*j); 
		    		  if(topcircles[i][j]==1){
		    		 g.fillOval(topxpos, topypos, 7, 7);
		    		 }
		    	  }
		      }
		      for(int i=0;i<3;i++){
		    	  botxpos = x1-31 + (10*i);
		    	  for(int j=0;j<3;j++){
		    		 botypos = y1-14 + (10*j);
		    		 if(botcircles[i][j]==1){
		    		 g.fillOval(botxpos, botypos, 7, 7);
		    		 }
		    	  }
		      }
		      break;  
	     case 3:
	    	  botxpos=x1-15;
		      botypos=y1-32;
		      topxpos=x1-15;
		      topypos=y1+5; 
	    	  Polygon p3 = new Polygon();
		      p3.addPoint(x1+17,y1+35);
		      p3.addPoint(x1-17,y1+35);
		      p3.addPoint(x1-17,y1-35);
		      p3.addPoint(x1+17,y1-35);
		      g.setColor(Color.lightGray);
		      g.fillPolygon(p3); 	     
		      g.setColor(Color.BLUE);
		      g.drawPolygon(p3);
		      g.setColor(Color.RED);
		      g.drawLine(x1-17, y1, x1+17, y1);
		      
		      for(int i=0;i<3;i++){
		    	  topypos = y1+25 - (10*i); 
		    	  for(int j=0;j<3;j++){
		    		 topxpos = x1+6 - (10*j);
		    		 if(topcircles[i][j]==1){
		    		 g.fillOval(topxpos, topypos, 7, 7);
		    		 }
		    	  }
		      }
		      for(int i=0;i<3;i++){
		    	  botypos = y1-13 - (10*i); 
		    	  for(int j=0;j<3;j++){
		    		 botxpos = x1+6 - (10*j);
		    		 if(botcircles[i][j]==1){
		    		 g.fillOval(botxpos, botypos, 7, 7);
		    		 }
		    	  }
		      }
		      break;  
	     case 4:
		      Polygon p4 = new Polygon();
		      p4.addPoint(x1+35,y1+17);
		      p4.addPoint(x1-35,y1+17);
		      p4.addPoint(x1-35,y1-17);
		      p4.addPoint(x1+35,y1-17);
		      g.setColor(Color.lightGray);
		      g.fillPolygon(p4); 	     
		      g.setColor(Color.BLUE);
		      g.drawPolygon(p4);
		      g.setColor(Color.RED);
		      g.drawLine(x1, y1+17, x1, y1-17);
		      
		      
		      for(int i=0;i<3;i++){
		    	  topxpos = x1-11 - (10*i); 
		    	  for(int j=0;j<3;j++){
		    		 topypos = y1+7 - (10*j);
		    		 if(topcircles[i][j]==1){
		    		 g.fillOval(topxpos, topypos, 7, 7);
		    		 }
		    	  }
		      }
		      for(int i=0;i<3;i++){
		    	  botxpos = x1+24 - (10*i); 
		    	  for(int j=0;j<3;j++){
		    		 botypos = y1+7 - (10*j);
		    		 if(botcircles[i][j]==1){
		    		 g.fillOval(botxpos, botypos, 7, 7);
		    		 }
		    	  }
		      }
		      
		      
	     }
	   }
	     
	   
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public boolean isInside(int x, int y) {
		switch(position){
		case 1:
			if(x>x1-17 && x<x1+17 && y>y1-35 && y<y1+35){
				return true;}
		case 3:
			if(x>x1-17 && x<x1+17 && y>y1-35 && y<y1+35){
				return true;}
		case 2:
			if(x>x1-35 && x<x1+35 && y>y1-17 && y<y1+17){
				return true;}
		case 4:
			if(x>x1-35 && x<x1+35 && y>y1-17 && y<y1+17){
				return true;}
		
		}
		return false;
	}
	
	public int getX1() {
		return x1;
	}
	public void setX1(int x1) {
		this.x1 = x1;
	}
	public int getY1() {
		return y1;
	}
	public void setY1(int y1) {
		this.y1 = y1;
	}

	public int getNum1() {
		return num1;
	}

	public String getList() {
		// TODO Auto-generated method stub
		return this.list;
	}
	
	public int getIndex(){
		return this.index;
	}

	public void setmoved(boolean bool){
		moved= bool;
	}
	      
	      
	   
}

/*
 * CS350
 * Project #6
 * Ethan Schatzline
 * The client class for dominoes
 * Click+Drag to move, Right Click to rotate 90 degrees
 * Your dominoes are the top dominoes
 */

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.JApplet;
import javax.swing.JPanel;



public class DominoesClient extends JApplet
	implements KeyListener, MouseListener, MouseMotionListener{
	private ArrayList<CDomino> serverPieces;
	private ArrayList<CDomino> clientPieces;
	private ArrayList<ArrayList<CDomino>> alldoms;
	private CDomino BlockToBeMoved;
	private ObjectOutputStream output; // output stream to client
	private ObjectInputStream input; // input stream from client
	private Socket client; // client socket
	//private Socket connection; // connection to server
	private int counter = 1;
	public DominoesClient dominoes;
	
	 private void getStreams() throws IOException
	   {
	      // set up output stream for objects
	      output = new ObjectOutputStream( client.getOutputStream() );
	      output.flush(); // flush output buffer to send header information

	      // set up input stream for objects
	      input = new ObjectInputStream( client.getInputStream() );
	      

	      System.out.println( "\nClient - Got I/O streams\n" );
	      
	     
		   
	      
	   } // end method getStreams

	   // process connection with client
	   private void processConnection() throws IOException
	   {
		   System.out.println( "Client - Waiting for connection\n" );
		 
	      String message = "Connection successful";
	      
	      do // process messages sent from client
	      { 
	    	 
	    	  System.out.println("Client - Trying to get server and client doms");
			 if(!alldoms.equals(null)){
				  
				  ArrayList<ArrayList<CDomino>> blah;
					try {
						blah = (ArrayList<ArrayList<CDomino>>) input.readObject();
						serverPieces = blah.get(0);
						clientPieces = blah.get(1);
						repaint();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
					
					
						
			 
			  repaint();
			
         
       }
			
	    	  repaint();
	      } while ( true);
	      
	      
	   } // end method processConnection

	   // close streams and socket
	   private void closeConnection() 
	   {
		   System.out.println( "\nClient - Terminating connection\n" );

	      try 
	      {
	         output.close(); // close output stream
	         input.close(); // close input stream
	         client.close(); // close socket
	      } // end try
	      catch ( IOException ioException ) 
	      {
	         ioException.printStackTrace();
	      } // end catch
	   } // end method closeConnection

	   // send message to client
	   private void sendData() {
	      
		
		   try {
			   
	    	  // send object to client
	    	  if (serverPieces!=null && clientPieces!=null){
	    		  alldoms.clear();
	    		  for (int i=0; i<serverPieces.size(); i++) {
						serverPieces.get(i).setshow(true);
					}
					for (int i=0; i<clientPieces.size(); i++) {
						clientPieces.get(i).setshow(false);
					
				}
	    		  alldoms.add(serverPieces);
	    		  alldoms.add(clientPieces);
	    		  output.reset();
	    		  output.writeObject(alldoms);
	    		  output.flush();
	    		  for (int i=0; i<serverPieces.size(); i++) {
						serverPieces.get(i).setshow(false);
					}
					for (int i=0; i<clientPieces.size(); i++) {
						clientPieces.get(i).setshow(true);
					
				}
	    	  }  // end try
	      } catch ( IOException ioException ) {
	    	  ioException.printStackTrace();
	      } // end catch
	   } // end method sendData

	   
	   
	   
	   
	   


	  private void waitForConnection() throws IOException
	   {
		  System.out.println( "Client - Waiting for connection\n" );           
	      System.out.println( "Client - Connection " + counter + " received from: " +
	         client.getInetAddress().getHostName() );

	   } // end method waitForConnection
	
	public void runClient()
	   {
		alldoms = new ArrayList<ArrayList<CDomino>>();
	      try // set up server to receive connections; process connections
	      {
	         client = new Socket( "169.254.47.72", 12345 ); // create ServerSocket
	         
	         while ( true ) 
	         {
	            try 
	            {
	               waitForConnection(); // wait for a connection
	               
	               getStreams(); // get input & output streams
	               
	               processConnection();
	               // process connection
	            } // end try
	            catch ( EOFException eofException ) 
	            {
	            	System.out.println( "\nClient - Server terminated connection" );
	            } // end catch
	            finally 
	            {
	            	
	               closeConnection();
	               System.out.println("OK WE DONE HERE");//  close connection
	               ++counter;
	            } // end finally
	         } // end while
	      } // end try
	      catch ( IOException ioException ) 
	      {
	         ioException.printStackTrace();
	      } // end catch
	   } // end method runServer	
	

public DominoesClient(){
	//sets up everything as null until the server sends over some data
	BlockToBeMoved=null;
	serverPieces = new ArrayList<CDomino>();
	clientPieces = new ArrayList<CDomino>();
	
	this.setFocusable(true);
	this.addKeyListener(this);
    this.addMouseListener(this);
    this.addMouseMotionListener(this);
}	

	
	
	
	public static void main( String[] args )
	   {
		JFrame application = new JFrame("DominoesClient"); // creates a new JFrame
		DominoesClient dominoes = new DominoesClient(); 
		
		application.add(dominoes);
		
		 application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	     application.setSize( 650, 640 ); // set the desired size
	     application.setVisible( true ); // show the frame
	     dominoes.runClient();
	     
	    }
	
	public void paint( Graphics g )
    {
		// State Presentation, using double buffers
    	// create the back buffer
		
		Image backBuffer=createImage(getSize().width, getSize().height);
    	Graphics gBackBuffer=backBuffer.getGraphics();
		// clear the back buffer
    	gBackBuffer.setColor(Color.MAGENTA);
	    gBackBuffer.drawLine(0,100, 900, 100);
	    gBackBuffer.drawLine(0, 500, 900, 500);
    	gBackBuffer.setColor(Color.white);
		gBackBuffer.clearRect(0, 0, getSize().width, getSize().height);
		// draw the pieces to back buffer
		for (int i=0; i<serverPieces.size(); i++) {
			serverPieces.get(i).draw(gBackBuffer);
			clientPieces.get(i).draw(gBackBuffer);
		}
		// copy from back buffer to front
		g.drawImage(backBuffer, 0, 0, null);
		
    } // end method paintComponent
    
    // KeyListener event handlers

	@Override
	public void mouseDragged(MouseEvent e) {
		 if (e.isMetaDown()) return;	// ignore right button
	    	
			if (BlockToBeMoved!=null) {
				BlockToBeMoved.setX1(e.getX()); 
				BlockToBeMoved.setY1(e.getY());
				BlockToBeMoved.setshow(true);
				BlockToBeMoved.setmoved(true);
				
							}
				
			repaint();
			sendData();
			repaint();
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.isMetaDown()) {
		for (int i=clientPieces.size()-1; i>=0; i--) {
			CDomino p=clientPieces.get(i);
			if (p.isInside(e.getX(), e.getY())) { 
				if(p.getPosition()==4){
					p.setPosition(1);
				}
				else if(p.getPosition()==1){
					p.setPosition(2);}
				else if(p.getPosition()==2){
					p.setPosition(3);}
				else if(p.getPosition()==3){
					p.setPosition(4);}
				p.setmoved(true);
				sendData();
				repaint();
			} } }
		repaint();
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	 public void mousePressed( MouseEvent e ) {
    	if (e.isMetaDown()) return;	// ignore right button
    	
		// search from top down (i.e. back to front)
		for (int i=clientPieces.size()-1; i>=0; i--) {
			//CDomino p=clientPieces.get(i);
			if (clientPieces.get(i).isInside(e.getX(), e.getY())) {
				// move to the end, i.e. the top
				BlockToBeMoved=clientPieces.get(i);
				repaint();
				sendData();
				break;
			}
		}
		repaint();
    }

	@Override
	public void mouseReleased(MouseEvent e) {
		BlockToBeMoved=null;
		repaint();
			}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
	}}
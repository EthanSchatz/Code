
import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Painter
{
   public static void main( String[] args )
   { 
      // create JFrame
      JFrame application = new JFrame( "Ethan's Colorful Paint Pad" );
      
      PaintPanel paintPanel = new PaintPanel();
      application.add(paintPanel);
  
      application.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      application.setSize( 800, 600 ); // set frame size
      application.setVisible( true ); // display frame
   } // end main
} // end class Painter


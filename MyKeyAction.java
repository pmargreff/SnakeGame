import javax.swing.*;
import javax.swing.event.*;
import java.awt.event.*;


class MyKeyAction implements KeyListener{
	
	public void keyTyped(KeyEvent e){
		// char ch = e.getKeyChar();
		// System.out.println(ch);
		// não será usada
	}

	public void keyPressed(KeyEvent e){
		// System.out.println("Key is pressed");
		// não será usada
	}

	public void keyReleased(KeyEvent e){
		switch(e.getKeyCode()){  
		        case KeyEvent.VK_DOWN:  
		            //...  
		            System.out.println("DOWN");  
		            break;  
		              
		        case KeyEvent.VK_UP:  
		            //...  
		            System.out.println("UP");  
		            break;  
		              
		        case KeyEvent.VK_RIGHT:  
		            //...  
		            System.out.println("RIGHT");  
		            break;  
		              
		        case KeyEvent.VK_LEFT:  
		            //...  
		            System.out.println("LEFT");  
		            break;  
		              
		        case KeyEvent.VK_SPACE:  
		            //...  
		            System.out.println("SPACE");  
		            break;  
		        }  
		//System.out.println("Key is Released");
	}
}